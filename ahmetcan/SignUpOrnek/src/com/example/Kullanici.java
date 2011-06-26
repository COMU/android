package com.example;

import java.net.URI;

import android.net.Uri;

public class Kullanici {
	private String key;
	private String adi;
	private String soyad;
	private String dogumTarihi;
	private String konumId;
	private String email;
	private String parola;
	private String resimId;
	private String detayId;

	
	public String getDetayId() {
		return detayId;
	}
	public void setDetayId(String detayId) {
		this.detayId = detayId;
	}
	public String getResimId() {
		return resimId;
	}
	public void setResimId(String resim) {
		this.resimId = resim;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getDogumTarihi() {
		return dogumTarihi;
	}
	public void setDogumTarihi(String dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
	public String getKonumId() {
		return konumId;
	}
	public void setKonumId(String konum) {
		this.konumId = konum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	
	
}
