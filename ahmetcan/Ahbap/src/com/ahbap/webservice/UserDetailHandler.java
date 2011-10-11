package com.ahbap.webservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.util.Base64;

import com.ahbap.User;

public class UserDetailHandler extends Handler {
	

	public UserDetailHandler(Context context) {
		super(context);
		URL = "http://10.0.2.2:8000/api/detay/";
	}

	@Override
	protected String getBuild(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String postBuild(User user) {
		// TODO Auto-generated method stub
		String params = "dogrulama_id=" + dbHandler.getKey() + "&" + "email="
				+ user.getEmail() + "&" + "parola=" + user.getPassword() + "&"
				+ "cinsiyet=" + user.getUserdetail().getGender() + "&"
				+ "egitim=" + user.getUserdetail().getEducation() + "&"
				+ "meslek=" + user.getUserdetail().getJob();
		return base64Encoder(params);
	}

	@Override
	protected String putBuild(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String post(User user) {
		// TODO Auto-generated method stub
		String sonuc = "";
		post = new HttpPost(URL);
		String params = postBuild(user);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("params", params));

		
		
		entity = null;
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
		} 
		finally {
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
		// TODO Auto-generated method stub
		return null;
	}

}
