package com.location;




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
import android.widget.Toast;

public class Gps extends Activity {
    /** Called when the activity is first created. */
    private Coordinates coordinate;
    private myAddress address;
	private Geocoderapi geocoder;
    private LocationManager locationManager;
	private TextView latitude;
	private TextView longitude;
	private TextView country;
	private Location currentLocation;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        latitude=(TextView)findViewById(R.id.lat);
        longitude=(TextView)findViewById(R.id.longi);
        country=(TextView)findViewById(R.id.country);
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
        	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            		10 * 1000, (float) 10.0,new myLocationListener());
        	Toast.makeText(getApplicationContext(),LocationManager.GPS_PROVIDER,Toast.LENGTH_SHORT).show();
        	country.setText(LocationManager.GPS_PROVIDER);
        }
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
        	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            		10 * 1000, (float) 10.0,new myLocationListener());
        	Toast.makeText(getApplicationContext(),LocationManager.NETWORK_PROVIDER,Toast.LENGTH_SHORT).show();
        	country.setText(LocationManager.NETWORK_PROVIDER);
        }
        
        
        
       
    }
	
	private void uptadeLocationText(){
		latitude.setText(Double.toString(currentLocation.getLatitude()));
		longitude.setText(Double.toString(currentLocation.getLongitude()));
		geocoder=new Geocoderapi();
		coordinate=new Coordinates(Double.toString(currentLocation.getLatitude())
				,Double.toString(currentLocation.getLongitude()));
		address=geocoder.geocode(coordinate);
		if(address==null)
		     country.setText("null");
		else
			country.setText(address.getCountry());
		
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