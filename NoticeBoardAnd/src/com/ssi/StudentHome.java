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

public class StudentHome extends Activity {
	Spinner spt;
	EditText et2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student);
		spt=(Spinner)findViewById(R.id.spt);
		et2=(EditText)findViewById(R.id.et2);
	}
		public void showNotice(View c) throws ClientProtocolException, IOException{
	String topic=spt.getSelectedItem().toString().trim();
    String url="http://10.0.2.2:8080/NoticeBoard/ShowNotice?topic="+topic;
    DefaultHttpClient client =new DefaultHttpClient();
    HttpGet get=new HttpGet(url);
    HttpResponse resp=client.execute(get);
    HttpEntity ent=resp.getEntity();
    String result=EntityUtils.toString(ent).trim();
			String data[]=result.split(",");
			String s="";
			for(int i=0;i<data.length;i++)
				s=s+data[i]+"\n";
			et2.setText(s);
		}
	
}
