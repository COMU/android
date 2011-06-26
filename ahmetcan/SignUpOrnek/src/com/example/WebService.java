package com.example;

import java.io.File;
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
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

public class WebService {
	private final String KULLANICI_URL="http://10.0.2.2:8000/api/kullanici/";
	private String GET_EMAIL_KONTROL="http://10.0.2.2:8000/api/email/";
	private String KONUM_URL="http://10.0.2.2:8000/api/konum/";
	private String ULKE_URL="http://10.0.2.2:8000/api/ulke/";
	private String SEHIR_URL="http://10.0.2.2:8000/api/sehir/";
	private String ARAMA_URL="http://10.0.2.2:8000/api/arama/";
	private String MESLEK_URL="http://10.0.2.2:8000/api/meslekler/";
	private String DETAY_URL="http://10.0.2.2:8000/api/detay/";
	private HttpClient client;
	private HttpPost post;
	private HttpPut put;
	private HttpGet get;
	private HttpEntity results;
	private HttpResponse response;
	private DbHelper db;
	private Context context;
	public WebService(Context context){
		client = new DefaultHttpClient();
		this.context = context;
		db = new DbHelper(this.context);
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
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sonuc;
	}
	public String userUptade(Kullanici user, String parola){
		String sonuc = "";
		put = new HttpPut(KULLANICI_URL);
		String param = "dogrulama_id=" + db.getKey() + "&" + "ad=" + user.getAdi() + "&" + "soyad=" + user.getSoyad() + "&" 
		+ "email=" + user.getEmail() + "&dogum_tarihi="+user.getDogumTarihi() + "&" + "parola="+user.getParola();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("params", Base64.encodeToString(param.getBytes(), Base64.NO_WRAP)));
		try {
			put.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		results = null;
		try {
			
			response=client.execute(put);
			sonuc=EntityUtils.toString(response.getEntity());
//			Toast.makeText(getApplicationContext(), sonuc, Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			
		} finally {
			if (results!=null)
				try {
					results.consumeContent();
				} catch (IOException e) {
					// empty, Checked exception but don't care));
//			        nvps.add(new BasicNameValuePair("ulke_adi", "Turkiye"));
				}
		}
		return sonuc;
		
	}
	
	
	
	public String addUser(Kullanici user){
		post= new HttpPost(KULLANICI_URL);
		String param = "ad=" + user.getAdi() + "&" + "soyad=" + user.getSoyad() + "&" 
						+ "email=" + user.getEmail() + "&" + "parola=" + user.getParola()
					    + "&" + "dogrulama_id=" + user.getKey();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("params", Base64.encodeToString(param.getBytes(), Base64.NO_WRAP)));
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
//			        nvps.add(new BasicNameValuePair("ulke_adi", "Turkiye"));
				}
		}
		return sonuc;
	}
	
	public String getUser(String email, String parola){
		String sonuc = "";
		String key = db.getKey();
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key;
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(KULLANICI_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		
		
		return sonuc;
	}
	public String getKonum(String email,String parola){
		String sonuc = "";
		String key = db.getKey();
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key;
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(KONUM_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		
		
		return sonuc;
	}
	public String getKonum(String email,String parola,String key){
		String sonuc = "";
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key;
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(KONUM_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		
		
		return sonuc;
	}
	public String getUlke(String email,String parola){
		String sonuc = "";
		String key = db.getKey();
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key;
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(ULKE_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		
		
		return sonuc;
	}
	public String getUlke(String email,String parola,String key){
		String sonuc = "";
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key;
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(ULKE_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		
		
		return sonuc;
	}
	public String getSehir(String email,String parola){
		String sonuc = "";
		String key = db.getKey();
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key;
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(SEHIR_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		
		
		return sonuc;
	}
	public String getSehir(String email,String parola,String key){
		String sonuc = "";
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key;
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(SEHIR_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		
		
		return sonuc;
	}
	public String konumGuncelle(String email,String parola,Konum konum){
		String sonuc = "";
		put = new HttpPut(KONUM_URL);
		String param = "dogrulama_id=" + db.getKey() + "&" + "email=" + email  + "&" + "parola=" + parola 
						+ "&" + "enlem=" + konum.getEnlem() + "&" + "boylam=" + konum.getBoylam() + "&" 
						+ "sehir=" + konum.getSehir() + "&" + "ulke=" + konum.getUlke();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("params", Base64.encodeToString(param.getBytes(), Base64.NO_WRAP)));
		try {
			put.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		results = null;
		try {
			
			response=client.execute(put);
			sonuc=EntityUtils.toString(response.getEntity());
//			Toast.makeText(getApplicationContext(), sonuc, Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			
		} finally {
			if (results!=null)
				try {
					results.consumeContent();
				} catch (IOException e) {
					// empty, Checked exception but don't care));
//			        nvps.add(new BasicNameValuePair("ulke_adi", "Turkiye"));
				}
		}
		return sonuc;
	}
	public String addKonum(String email, String parola,Konum konum){
		String sonuc = "";
		post = new HttpPost(KONUM_URL);
		String param = "dogrulama_id=" + db.getKey() + "&" + "email=" + email  + "&" + "parola=" + parola 
						+ "&" + "enlem=" + konum.getEnlem() + "&" + "boylam=" + konum.getBoylam() + "&" 
						+ "sehir=" + konum.getSehir() + "&" + "ulke=" + konum.getUlke();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("params", Base64.encodeToString(param.getBytes(), Base64.NO_WRAP)));
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
//			        nvps.add(new BasicNameValuePair("ulke_adi", "Turkiye"));
				}
		}
		return sonuc;
	}
	public String getUlkeler(String email,String parola){
		String sonuc = "";
		String key = db.getKey();
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key+"&"+"bes=5";
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(ULKE_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		return sonuc;
		
	}

	
	public String getSehirler(String email,String parola,String ulke_adi){
		String sonuc = "";
		String key = db.getKey();
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key+"&"+"ulke="+ulke_adi;
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(SEHIR_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		return sonuc;
		
	}
	
	
	public String getMeslekler(String email,String parola){
		String sonuc = "";
		String key = db.getKey();
		String params = "email=" + email + "&" + "parola=" + parola + "&" +"key=" + key;
		try {
			String s = android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			
			Log.i("base64", s);
			get = new HttpGet(MESLEK_URL + "?param=" + s);
			
			response = client.execute(get);
			sonuc = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			Log.i("GET", e.getMessage());
			e.printStackTrace();
		}
		return sonuc;
		
	}
	
	
	public String getArananKisi(String email,String parola,String ulke_adi,String sehir_adi,String meslek_adi){
		String sonuc="";
		String key=db.getKey();
		String params="email="+email+"&"+"parola="+parola+"&"+"key="+key+"&"+"ulke="+ulke_adi+"&"+"sehir_adi="+sehir_adi+"&"+"meslek_adi="+meslek_adi;
		try{
			String s=android.util.Base64.encodeToString(params.getBytes("UTF-8"), Base64.NO_WRAP);
			get=new HttpGet(ARAMA_URL+"?param="+s);
			response=client.execute(get);
			sonuc=EntityUtils.toString(response.getEntity());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sonuc;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String addKullaniciDetaylari(String email, String parola,Detay detay){
		String sonuc = "";
		post = new HttpPost(DETAY_URL);
		String param = "dogrulama_id=" + db.getKey() + "&" + "email=" + email  + "&" + "parola=" + parola 
						+ "&" + "cinsiyet=" + "erkek" + "&" + "egitim=" + "universite" + "&" 
						+ "meslek=" + "muhendis";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("params", Base64.encodeToString(param.getBytes(), Base64.NO_WRAP)));
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
//			        nvps.add(new BasicNameValuePair("ulke_adi", "Turkiye"));
				}
		}
		return sonuc;
	}
	
	
	
	
	
	
	
	
	
}
