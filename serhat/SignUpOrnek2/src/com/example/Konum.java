package com.example;

public class Konum {
	private Ulke ulke;
	private Sehir sehir;
	private double enlem;
	private double boylam;
	
	public Konum(double enlem,double boylam,Ulke ulke,Sehir sehir){
		this.ulke=ulke;
		this.sehir=sehir;
		this.enlem=enlem;
		this.boylam=boylam;
	}

	public Ulke getUlke() {
		return ulke;
	}

	public void setUlke(Ulke ulke) {
		this.ulke = ulke;
	}

	public Sehir getSehir() {
		return sehir;
	}

	public void setSehir(Sehir sehir) {
		this.sehir = sehir;
	}

	public double getEnlem() {
		return enlem;
	}

	public void setEnlem(float enlem) {
		this.enlem = enlem;
	}

	public double getBoylam() {
		return boylam;
	}

	public void setBoylam(float boylam) {
		this.boylam = boylam;
	}
	
}
