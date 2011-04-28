package com.example;

public class Coordinates {
	private String latitude = "52.516074";
	private String longitude = "13.376987";
	
	public Coordinates(String latitue,String longitude){
		this.latitude=latitue;
		this.longitude=longitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param langitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}

		