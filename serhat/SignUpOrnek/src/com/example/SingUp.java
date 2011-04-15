package com.example;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class SingUp extends Activity {
	/** Called when the activity is first created. */
	private Coordinates coordinate;
	private myAddress address;
	private Geocoderapi geocoder;
	private ApplicationService myApp;
	private Context context;
	private EditText nameBox;
	private EditText lastNameBox;
	private Button setDate;
	private Button save;
	private EditText date;
	private EditText email;
	private EditText password;
	private TextView latitude;
	private TextView longitude;
	private TextView country;
	private TextView city;
	private Location currentLocation;
	private int mYear;
	private int mMonth;
	private int mDay;
	private Kullanici user = new Kullanici();

	private final int ID = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		MyLocationManager myLocation = new MyLocationManager();
		myLocation.getLocation(SingUp.this, locationResult);
		nameBox = (EditText) findViewById(R.id.nameBox);
		lastNameBox = (EditText) findViewById(R.id.lastNameBox);
		setDate = (Button) findViewById(R.id.setBirthDate);
		date = (EditText) findViewById(R.id.dateBox);
		email = (EditText) findViewById(R.id.emailBox);
		password = (EditText) findViewById(R.id.passwordBox);
		latitude = (TextView) findViewById(R.id.latitude);
		longitude = (TextView) findViewById(R.id.longitude);
		country = (TextView) findViewById(R.id.country);
		city = (TextView) findViewById(R.id.city);
		save = (Button) findViewById(R.id.save);

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		setDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(ID);

			}
		});
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (check()) {
					save();
				} else {
					// An Alertbox must pop here
				}

			}
		});

	}

	private boolean check() {
		user.setAdi(nameBox.getText().toString());
		user.setSoyad(lastNameBox.getText().toString());
		user.setEmail(email.getText().toString());
		user.setDogumTarihi(date.getText().toString());
		user.setParola(password.getText().toString());
		Konum konum = new Konum(currentLocation.getLatitude(),
				currentLocation.getLongitude(), new Ulke(country.getText().toString()),
				new Sehir("Balikesir"));
		user.setKonum(konum);
		return true;
	}

	private boolean save() {
	    myApp=new ApplicationService(SingUp.this,user);
		myApp.save();
		return true;
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
		date.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);

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

	private LocationResult locationResult = new LocationResult() {

		@Override
		public void gotLocation(Location location) {
			currentLocation = location;
			uptadeLocation();
		}
	};

	private void uptadeLocation() {
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
	}
}