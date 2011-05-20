package com.example;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
	private static JSONObject kullaniciJson;
	private static Kullanici kullanici;
	

	
	public static Kullanici returnKullanici(String kullaniciStr){
			kullanici =  new Kullanici();
		try {
			
			kullaniciJson = new JSONObject(kullaniciStr);
			kullanici.setAdi(kullaniciJson.getString("ad"));
			kullanici.setSoyad(kullaniciJson.getString("soyad"));
			kullanici.setEmail(kullaniciJson.getString("email"));
			kullanici.setResimId(kullaniciJson.getString("resim_id"));
			kullanici.setDetayId(kullaniciJson.getString("kullanici_detaylari_id"));
			kullanici.setKonumId(kullaniciJson.getString("konum_id"));
		} catch (JSONException e) {
			return null;
			
		}
		return kullanici;
	}
	public static Konum returnKonum(String konumStr,String ulkeStr,String sehirStr){
		Konum konum = new Konum();
		try {
			JSONObject konumJson =  new JSONObject(konumStr);
			JSONObject sehirJson = new JSONObject(sehirStr);
			JSONObject ulkeJson = new JSONObject(ulkeStr);
			konum.setBoylam(Double.parseDouble(konumJson.getString("boylam")));
			konum.setEnlem(Double.parseDouble(konumJson.getString("enlem")));
			konum.setSehir(sehirJson.getString("adi"));
			konum.setUlke(ulkeJson.getString("adi"));
		} catch (JSONException e) {
			return null;
		}
		return konum;
	}
	
}
