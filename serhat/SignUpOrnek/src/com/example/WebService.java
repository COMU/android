package com.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class WebService {
	private final String PUT_KONUM_URL="http://10.0.2.2:8000/api/kullanici/";
	private HttpClient client;
	private HttpPost post;
	private HttpPut put;
	private HttpGet get;
	
	public WebService(){
		client = new DefaultHttpClient();
		post= new HttpPost(PUT_KONUM_URL);
		
		
	}
	
	public String addUser(Kullanici user){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("ad", user.getAdi()));
		nvps.add(new BasicNameValuePair("soyad", user.getSoyad()));
		nvps.add(new BasicNameValuePair("email", user.getEmail()));
		nvps.add(new BasicNameValuePair("parola", user.getParola()));
		nvps.add(new BasicNameValuePair("dogum_tarihi", user.getDogumTarihi()));
		nvps.add(new BasicNameValuePair("enlem", Double.toString(user.getKonum().getEnlem())));
        nvps.add(new BasicNameValuePair("boylam", Double.toString(user.getKonum().getBoylam())));
        nvps.add(new BasicNameValuePair("sehir_adi", user.getKonum().getSehir().getAdi()));
        nvps.add(new BasicNameValuePair("ulke_adi", user.getKonum().getUlke().getAdi()));
        try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		String sonuc = "";
		HttpEntity results = null;
		try {
			HttpResponse response=client.execute(post);
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
