package com.ahbap.webservice;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.ahbap.User;

public class ImageHandler extends Handler {

	public ImageHandler(Context context) {
		super(context);

	}

	@Override
	protected String getBuild(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String postBuild(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String putBuild(User user) {
		String key = dbHandler.getKey();
		String params = "email=" + user.getEmail() + "&" + "parola="
				+ user.getPassword() + "&" + "key=" + key;
		return base64Encoder(params);
	}

	@Override
	public String get(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String post(User user) {
		return URL;

	}

	public String post(String email, String password, String imagePath) {
		String params = postBuild(email, password);
		HttpContext localContext = new BasicHttpContext();
		post = new HttpPost(URL);

		try {
			MultipartEntity entity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			entity.addPart("image", new FileBody(new File(imagePath)));
			entity.addPart("params", new StringBody(params));
			post.setEntity(entity);
			HttpResponse response = client.execute(post, localContext);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sonuc;
	}

	public String postBuild(String email, String password) {
		String key = dbHandler.getKey();
		String params = "email=" + email + "&" + "parola=" + password + "&"
				+ "key=" + key;
		return base64Encoder(params);
	}

	@Override
	public String put(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
