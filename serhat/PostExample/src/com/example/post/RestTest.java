package com.example.post;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RestTest extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView text=(TextView)findViewById(R.id.text);
//		String LOGIN_URL = "http://10.0.2.2:8000/api/meslekler/";
		HttpClient client = new DefaultHttpClient();
	    HttpPost post = new HttpPost("http://10.0.2.2:8000/api/meslekler/");

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("meslek_adi", "Muslukcu"));
        try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		String sonuc = null;
		HttpEntity results = null;
		try {
			HttpResponse response=client.execute(post);
			sonuc=EntityUtils.toString(response.getEntity());
			text.setText(sonuc);
//			Toast.makeText(getApplicationContext(), sonuc, Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			
		} finally {
			if (results!=null)
				try {
					results.consumeContent();
				} catch (IOException e) {
					// empty, Checked exception but don't care
				}
		}
//		JSONArray object;
//		try {
//			object = new JSONArray(sonuc);
//			JSONObject or=object.getJSONObject(0);
//			
//			   	
//			   Toast.makeText(RestTest.this,or.toString(), Toast.LENGTH_LONG).show();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		


		
	}
}
