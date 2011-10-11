package com.ahbap.webservice;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import android.content.Context;
import com.ahbap.User;

public class JobHandler extends Handler {

	public JobHandler(Context context) {
		super(context);
		URL = "http://10.0.2.2:8000/api/meslek/";
	}

	@Override
	protected String getBuild(User user) {
		String key = dbHandler.getKey();
		String params = "email=" + user.getEmail() + "&" + "parola="
				+ user.getPassword() + "&" + "key=" + key;
		return params;

	}

	protected String getJobsBuild(User user) {
		String key = dbHandler.getKey();
		String params = "email=" + user.getEmail() + "&" + "parola="
				+ user.getPassword() + "&" + "key=" + key + "&" + "bes=" + 5;
		return params;

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

		try {
			get = new HttpGet(URL + "?param=" + params);
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {

			e.printStackTrace();
		}
		return sonuc;
	}
	
	public String getAllJobs(User user){
		String sonuc = "";
		String params = getBuild(user);

		try {
			get = new HttpGet(URL + "?param=" + params);
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {

			e.printStackTrace();
		}
		return sonuc;
		
	}

	@Override
	public String post(User user) {

		return null;
	}

	@Override
	public String put(User user) {

		return null;
	}

}
