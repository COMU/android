package com.location;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Geocoderapi {
	private String applicationId;
	private final static String YAHOOURL = "http://where.yahooapis.com/geocode";

	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public myAddress geocode(Coordinates coordinate) {
		myAddress geocoordinates = null;
		String web = YAHOOURL + "?q="+ createLocation(coordinate) + "&gflags=R&appid=[yourappidhere]";
				
		URL url;
		try {
			url = new URL(web);
			System.out.println("adres = " + web);
			// BufferedReader in = new BufferedReader(new );
			InputStream in = url.openStream();
			geocoordinates = YahooXmlReader.readConfig(in);
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return geocoordinates;
	}

	private String createLocation(Coordinates coordinate ){
		String s = "";
		
		if(coordinate.getLatitude() != null){
			s+=coordinate.getLatitude()+",+";
		}
		if(coordinate.getLongitude()!=null){
			s+=coordinate.getLongitude();
		}
		/*s += coordinate.getLatitude() != null ? coordinate.getLatitude() + "+" : "";
		s = coordinate.getLongitude() != null ? coordinate.getLongitude() + "+" : "";*/
	
		
		
		return s;
	}
}
