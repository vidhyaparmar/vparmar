package com.ssi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.spec.EncodedKeySpec;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoticeBoardAndActivity extends Activity {
    EditText etunm,etpass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etunm=(EditText)findViewById(R.id.etunm);
        etpass=(EditText)findViewById(R.id.etpass);
    }
    public void login(View v) throws UnsupportedEncodingException{
    	String unm=etunm.getText().toString().trim();
    	String pass=etpass.getText().toString().trim();
    	//String url="http://10.0.2.2:8080/NoticeBoard/CheckUser?uid="+unm+"&pass="+pass;
    	String url="http://10.0.2.2:8080/NoticeBoard/CheckUser";
    	DefaultHttpClient client=new DefaultHttpClient();
    	String result="";
    //	HttpGet gets=new HttpGet(url);
    	HttpPost post=new HttpPost(url);
    	BasicNameValuePair ob1=new BasicNameValuePair("uid", unm);
    	BasicNameValuePair ob2=new BasicNameValuePair("pass",pass);
    	ArrayList<BasicNameValuePair> list=new ArrayList<BasicNameValuePair>();
    	list.add(ob1);
    	list.add(ob2);
        UrlEncodedFormEntity ucode=new UrlEncodedFormEntity(list); 
        post.setEntity(ucode);
    	try {
			HttpResponse resp=client.execute(post);
			HttpEntity ent=resp.getEntity();
			result=EntityUtils.toString(ent).trim();
			if(result.equalsIgnoreCase("valid student details")){
				Intent it=new Intent(this,StudentHome.class);
				startActivity(it);
			}
			else if(result.equalsIgnoreCase("valid faculty details")){
				Intent it1=new Intent(this,FacultyHome.class);
				startActivity(it1);
			}
			else if(result.equalsIgnoreCase("Invalid user details")){
				Toast.makeText(this, "Invalid user...", Toast.LENGTH_LONG).show();
			}
		} catch (ClientProtocolException e) {
			result=e.toString();
		} catch (IOException e) {
			result=e.getMessage();
		}
    	Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    	
    }
}