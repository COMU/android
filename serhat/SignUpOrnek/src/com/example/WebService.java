package com.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class WebService {
	private final String PUT_KULLANICI_URL="http://10.0.2.2:8000/api/kullanici/";
	private String GET_EMAIL_KONTROL="http://10.0.2.2:8000/api/email/";
	private HttpClient client;
	private HttpPost post;
	private HttpPut put;
	private HttpGet get;
	private HttpEntity results;
	private HttpResponse response;
	public WebService(){
		client = new DefaultHttpClient();	
	}
	public String emailControl(String email){
		String sonuc = "yanlis";
		if(email.equals(""))
			return sonuc;
		GET_EMAIL_KONTROL += email;
		get = new HttpGet(GET_EMAIL_KONTROL);
		try {
			response = client.execute(get);
			sonuc=EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sonuc;
	}
	
	public String addUser(Kullanici user){
		post= new HttpPost(PUT_KULLANICI_URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("ad", user.getAdi()));
		nvps.add(new BasicNameValuePair("soyad", user.getSoyad()));
		nvps.add(new BasicNameValuePair("email", user.getEmail()));
		nvps.add(new BasicNameValuePair("parola", user.getParola()));
		nvps.add(new BasicNameValuePair("dogrulama_id", user.getKey()));
        try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		String sonuc = "";
		results = null;
		try {
			response=client.execute(post);
			sonuc=EntityUtils.toString(response.getEntity());
//			Toast.makeText(getApplicationContext(), sonuc, Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			
		} finally {
			if (results!=null)
				try {
					results.consumeContent();
				} catch (IOException e) {
					// empty, Checked exception but don't care));
			        nvps.add(new BasicNameValuePair("ulke_adi", "Turkiye"));
				}
		}
		return sonuc;
	}
}
