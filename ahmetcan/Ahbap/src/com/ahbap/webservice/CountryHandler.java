package com.ahbap.webservice;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.ahbap.User;

public class CountryHandler extends Handler {
	

	public CountryHandler(Context context) {
		super(context);
		URL = "http://10.0.2.2:8000/api/ulke/";
	}

	@Override
	protected String getBuild(User user) {
		String key = dbHandler.getKey();
		String params = "email=" + user.getEmail() + "&" + "parola="
				+ user.getPassword() + "&" + "key=" + key;
		return base64Encoder(params);
	}

	protected String getCountriesBuild(User user) {
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

		return null;
	}

	@Override
	public String get(User user) {

		String sonuc = "";
		String params = getBuild(user);

		get = new HttpGet(URL + "?param=" + params);
		try {
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return sonuc;
	}

	public String getAllCountries(User user) {

		String sonuc = "";
		String params = getCountriesBuild(user);

		get = new HttpGet(URL + "?param=" + params);
		try {
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

		return null;
	}

	@Override
	public String put(User params) {

		return null;
	}
}
