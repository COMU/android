package com.ahbap.webservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.util.Base64;

import com.ahbap.Location;
import com.ahbap.User;

public class LocationHandler extends Handler {
	
	public LocationHandler(Context context) {
		super(context);
		URL = "http://10.0.2.2:8000/api/konum/";
	}

	@Override
	protected String getBuild(User user) {
		String key = dbHandler.getKey();
		String params = "email=" + user.getEmail() + "&" + "parola="
				+ user.getPassword() + "&" + "key=" + key;
		return base64Encoder(params);
	}

	@Override
	protected String postBuild(User user) {
		Location l = user.getLocation();
		String params = "dogrulama_id=" + dbHandler.getKey() + "&" + "email="
				+ user.getEmail() + "&" + "parola=" + user.getPassword() + "&"
				+ "enlem=" + l.getLatitude() + "&" + "boylam="
				+ l.getLongitude() + "&" + "sehir=" + l.getCity().getName()
				+ "&" + "ulke=" + l.getCountry().getName();
		return base64Encoder(params);

	}

	@Override
	protected String putBuild(User user) {
		Location l = user.getLocation();
		String params = "dogrulama_id=" + dbHandler.getKey() + "&" + "email="
				+ user.getEmail() + "&" + "parola=" + user.getPassword() + "&"
				+ "enlem=" + l.getLatitude() + "&" + "boylam="
				+ l.getLongitude() + "&" + "sehir=" + l.getCity().getName()
				+ "&" + "ulke=" + l.getCountry().getName();
		return base64Encoder(params);

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sonuc;
	}

	@Override
	public String post(User user) {
		String sonuc = "";
		String params = postBuild(user);
		entity = null;
		post = new HttpPost(URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("params", params));

		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			response = client.execute(post);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (entity != null)
				try {
					entity.consumeContent();
				} catch (IOException e) {

				}
		}
		return sonuc;
	}

	@Override
	public String put(User user) {
		String sonuc = "";
		String params = putBuild(user);
		put = new HttpPut(URL);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("params", params));

		entity = null;
		try {
			put.setEntity(new UrlEncodedFormEntity(nvps));
			response = client.execute(put);
			sonuc = EntityUtils.toString(response.getEntity());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (entity != null)
				try {
					entity.consumeContent();
				} catch (IOException e) {

				}
		}
		return sonuc;
	}

}
