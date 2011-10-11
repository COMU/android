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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.util.Base64;

import com.ahbap.User;

public class UserHandler extends Handler {

	public UserHandler(Context context) {
		super(context);
		URL = "http://10.0.2.2:8000/api/kullanici/";
	}

	@Override
	public String get(User user) {

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

	@Override
	public String post(User user) {
		String sonuc = "";
		entity = null;
		String params = postBuild(user);
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
		String params = postBuild(user);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("params", params));

		entity = null;
		try {
			put.setEntity(new UrlEncodedFormEntity(nvps));
			response = client.execute(put);
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

	public String get(String email, String password) {

		String params = getBuild(email, password);

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

	public String getBuild(User user) {
		String key = dbHandler.getKey();
		String params = "email=" + user.getEmail() + "&" + "parola="
				+ user.getPassword() + "&" + "key=" + key;
		return base64Encoder(params);
	}

	public String getBuild(String email, String password) {
		String key = dbHandler.getKey();
		String params = "email=" + email + "&" + "parola=" + password + "&"
				+ "key=" + key;
		return base64Encoder(params);
	}

	public String postBuild(User user) {
		String params = "ad=" + user.getName() + "&" + "soyad="
				+ user.getLastname() + "&" + "email=" + user.getEmail() + "&"
				+ "parola=" + user.getPassword() + "&" + "dogrulama_id="
				+ user.getKey();

		return base64Encoder(params);
	}

	public String putBuild(User user) {
		String params = "dogrulama_id=" + dbHandler.getKey() + "&" + "ad="
				+ user.getName() + "&" + "soyad=" + user.getLastname() + "&"
				+ "email=" + user.getEmail() + "&dogum_tarihi="
				+ user.getDate() + "&" + "parola=" + user.getPassword();
		return base64Encoder(params);
	}

	public boolean checkUser(String email, String password) {

		String response = get(email, password);
		if (response.equals("-1")) {
			return false;
		} else{
			return true;
		}
	}

}
