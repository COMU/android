package com.location;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Enumeration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class Gps extends Activity {
	/** Called when the activity is first created. */
	private Coordinates coordinate;
	private myAddress address;
	private Geocoderapi geocoder;

	private ProgressDialog dialog;
	private TextView latitude;
	private TextView longitude;
	private TextView country;
	private TextView dateText;
	private TextView city;
	private Location currentLocation;
	private Button datePicker;
	private Thread t;
	private int mYear;
	private int mMonth;
	private int mDay;
	private boolean locationFind = false;
	private static final int ID = 0;
	private static final String LOG_TAG = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		latitude = (TextView) findViewById(R.id.lat);
		longitude = (TextView) findViewById(R.id.longi);
		country = (TextView) findViewById(R.id.country);
		dateText = (TextView) findViewById(R.id.birthdate);
		city=(TextView)findViewById(R.id.city);
		
		datePicker = (Button) findViewById(R.id.date_picker);

		dialog = new ProgressDialog(this);
		dialog.setCancelable(true);
		dialog.setMessage("Getting location information");
		dialog.show();
		MyLocationManager myLocation = new MyLocationManager();
		myLocation.getLocation(this, locationResult);

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// display the current date (this method is below)
		updateDisplay();
		datePicker.setText(getLocalIpAddress());
		datePicker.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(ID);
			}
		});

	}

	private LocationResult locationResult = new LocationResult() {

		@Override
		public void gotLocation(Location location) {
			if (location == null) {
				dialog.dismiss();
/*				AlertDialog.Builder builder = new AlertDialog.Builder(Gps.this);
				builder.setMessage("Location couldn't find.\nApplication will close");
				builder.setNeutralButton("Ok",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {
								// the button was clicked
								android.os.Process.killProcess(android.os.Process.myPid());
							}
						});
				builder.show();*/
				android.os.Process.killProcess(android.os.Process.myPid());
			} else {
				currentLocation = location;
				uptadeLocationText();
			}

		}
	};

	private void uptadeLocationText() {
		latitude.setText(Double.toString(currentLocation.getLatitude()));
		longitude.setText(Double.toString(currentLocation.getLongitude()));
		geocoder = new Geocoderapi();

		coordinate = new Coordinates(Double.toString(currentLocation
				.getLatitude()),
				Double.toString(currentLocation.getLongitude()));
		address = geocoder.geocode(coordinate);
		if (address == null)
			country.setText("null");
		else
			country.setText(address.getCountry());
			city.setText(address.getCity());
		dialog.dismiss();
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}

	};

	private void updateDisplay() {
		dateText.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {

		}
		return null;
	}
}