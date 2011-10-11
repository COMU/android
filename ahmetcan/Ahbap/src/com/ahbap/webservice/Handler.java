package com.ahbap.webservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import com.ahbap.User;
import com.ahbap.database.DbHandler;

import android.content.Context;
import android.util.Base64;

public abstract class Handler {

	protected HttpPost post;
	protected HttpGet get;
	protected HttpPut put;
	protected HttpClient client;
	protected Context context;
	protected HttpResponse response;
	protected String sonuc = "";
	protected HttpEntity entity;
	protected String URL;
	
	protected DbHandler dbHandler;

	public Handler(Context context) {
		dbHandler = new DbHandler(context);
		client = new DefaultHttpClient();
	}

	abstract protected String getBuild(User user);

	abstract protected String postBuild(User user);

	abstract protected String putBuild(User user);

	abstract public String get(User user);

	abstract public String post(User user);

	abstract public String put(User user);

	protected String base64Encoder(String params) {
		return Base64.encodeToString(params.getBytes(), Base64.NO_WRAP);
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
}
