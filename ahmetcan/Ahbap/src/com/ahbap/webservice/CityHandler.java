package com.ahbap.webservice;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.ahbap.User;

public class CityHandler extends Handler {
	

	public CityHandler(Context context) {
		super(context);
		URL = "http://10.0.0.2:8000/api/sehir/";
	}

	@Override
	protected String getBuild(User user) {
		String key = dbHandler.getKey();
		String params = "email=" + user.getEmail() + "&" + "parola="
				+ user.getPassword() + "&" + "key=" + key;
		return base64Encoder(params);
	}
	
	protected String getCitiesBuild(User user){
		String key = dbHandler.getKey();
		String params = "email=" + user.getEmail() + "&" + "parola="
				+ user.getPassword() + "&" + "key=" + key + "&" + "bes=" + 5;
		return base64Encoder(params);
		
	}

	@Override
	protected String postBuild(User user) {

		return null;
	}

	@Override
	protected String putBuild(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(User user) {
		String sonuc = "";
		String params = getBuild(user);
		try {
			get = new HttpGet(URL + "?param=" + params);
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return sonuc;
	}
	
	public String getAllCities(User user){
		String sonuc = "";
		String params = getCitiesBuild(user);
		try {
			get = new HttpGet(URL + "?param=" + params);
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return sonuc;
	}

	@Override
	public String post(User params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String put(User params) {
		// TODO Auto-generated method stub
		return null;
	}

}
