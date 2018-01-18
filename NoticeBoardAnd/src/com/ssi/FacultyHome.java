package com.ssi;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FacultyHome extends Activity {
		EditText et1;
		Spinner sptopic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faculty);
		sptopic=(Spinner)findViewById(R.id.sptopic);
		et1=(EditText)findViewById(R.id.et1);
	}
	
	public void postNotice(View e){
	String topic=sptopic.getSelectedItem().toString();
	String notice=et1.getText().toString().trim();
	Toast.makeText(this, topic+"-"+notice, 5000).show();
	notice=notice.replace(' ', '+');
	String url="http://10.0.2.2:8080/NoticeBoard/PostNotice?topic="+topic+"&notice="+notice;
	String result="";
	DefaultHttpClient client=new DefaultHttpClient();
	HttpGet gets=new HttpGet(url);
	try {
		HttpResponse resp=client.execute(gets);
		HttpEntity ent=resp.getEntity();
		result=EntityUtils.toString(ent).trim();
	} catch (ClientProtocolException e1) {
		result=e1.toString();
	} catch (IOException e1) {
		result=e1.toString();
	}
	
	Toast.makeText(this, result, Toast.LENGTH_LONG).show();	
	}

}
