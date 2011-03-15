package com.location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class Gps extends Activity {
    /** Called when the activity is first created. */
    
	private LocationManager locationManager;
	private TextView latitude;
	private TextView longitude;
	private TextView country;
	private List<Address> addresses;
	private Location currentLocation;
	private Geocoder geocoder;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        latitude=(TextView)findViewById(R.id.lat);
        longitude=(TextView)findViewById(R.id.longi);
        country=(TextView)findViewById(R.id.country);
        
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        		10 * 1000, (float) 10.0,new myLocationListener());
        
        geocoder = new Geocoder(this);
       
    }
	Thread t;
	private void uptadeLocationText(){
		latitude.setText(Double.toString(currentLocation.getLatitude()));
		longitude.setText(Double.toString(currentLocation.getLongitude()));
		
		try {
			addresses=geocoder.getFromLocation(currentLocation.getLatitude(),
					currentLocation.getLongitude(),1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(addresses!=null){
			country.setText(addresses.get(0).getCountryName());
		}
	}
	
	private class myLocationListener  implements LocationListener{
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			if(location!=null)
				currentLocation=location;
				uptadeLocationText();
			
		}
	}; 
}