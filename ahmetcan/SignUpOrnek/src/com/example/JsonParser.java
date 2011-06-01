package com.example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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
			kullanici.setParola(kullaniciJson.getString("parola"));
			kullanici.setDogumTarihi(kullaniciJson.getString("dogum_tarihi"));
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
	public static void  returnUlkeler(String ulkeler,List<String> ulke){
		try{
			JSONArray jsonUlke=new JSONArray(ulkeler);
			
			for(int i=0;i<jsonUlke.length();i++){
				ulke.add(jsonUlke.getJSONObject(i).getString("adi"));
			}
		}
		catch(Exception e) {
			
			
		}

	}
	public static void returnSehirler(String sehirler,List<String> sehir){
		
		try{
			JSONArray jsonSehir=new JSONArray(sehirler);
			
			for(int i=0;i<jsonSehir.length();i++){
				sehir.add(jsonSehir.getJSONObject(i).getString("adi"));
			}
		}
		catch(Exception e) {
			
			
		}
	}
	public static void returnMeslekler(String meslekler,List<String> meslek){
		try{
			JSONArray jsonMeslek=new JSONArray(meslekler);
			for(int i=0;i<jsonMeslek.length();i++){
				meslek.add(jsonMeslek.getJSONObject(i).getString("adi"));
			}
		}
		catch(Exception e) {
			
			
		}
		
	}
	public static void returnAranan(String arananlar,List<String>aranan){
		
		try{
			JSONArray jsonAranan=new JSONArray(arananlar);
			for(int i=0;i<jsonAranan.length();i++){
				aranan.add(jsonAranan.getJSONObject(i).getString("ad"));
				
			}
			}
			catch(Exception e){}
			
		
		
		
	}
	
	
}
