package com.example;

import java.io.FileNotFoundException;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Profil extends Activity {
	private Kullanici kullanici;
	private Konum konum;
	private WebService service;
	private TextView ad;
	private TextView soyad;
	private TextView email;
	private TextView enlem;
	private TextView boylam;
	private TextView sehir;
	private TextView ulke;
	private TextView tarih;
	private Button tarihButton;
	private Button ad_button;
	private Button soyad_button;
	private Button email_button;
	private Bundle extras;
	private Button konumGuncelle;
	private Location currentLocation;
	private MyLocationManager locationManager;
	private Button kaydet;
	private Geocoderapi geocoder;
	private myAddress address;
	private Coordinates coordinate;
	private Button parolaDegistir;
	private ImageView profil_photo;
	private static final int DATE_DIALOG_ID = 0;
	private int mDay;
	private int mMonth;
	private int mYear;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		extras = getIntent().getExtras();
		service = new WebService(getApplicationContext());
		ad = (TextView) findViewById(R.id.kullaniciText2);
		soyad = (TextView) findViewById(R.id.kullaniciText4);
		email = (TextView) findViewById(R.id.kullaniciText6);
		//enlem = (TextView) findViewById(R.id.kullaniciText8);
		//boylam = (TextView) findViewById(R.id.kullaniciText10);
		sehir = (TextView) findViewById(R.id.kullaniciText12);
		ulke = (TextView) findViewById(R.id.kullaniciText14);
		tarih = (TextView) findViewById(R.id.kullaniciTextd2);

		tarihButton = (Button) findViewById(R.id.tarihButton);
		ad_button = (Button) findViewById(R.id.degistir);
		ad_button.setOnClickListener(adListener);
		soyad_button = (Button) findViewById(R.id.degistir2);
		soyad_button.setOnClickListener(soyAdListener);
		email_button = (Button) findViewById(R.id.degistir3);
		email_button.setOnClickListener(emailListener);
		parolaDegistir = (Button) findViewById(R.id.parolaButton);
		parolaDegistir.setOnClickListener(parolaListener);
		konumGuncelle = (Button) findViewById(R.id.konum);
		konumGuncelle.setOnClickListener(konumListener);
		profil_photo=(ImageView)findViewById(R.id.profil_image);
		setUserText();
		setLocationText();
		kaydet = (Button) findViewById(R.id.kaydet);
		kaydet.setOnClickListener(new OnClickListener() {

		
			public void onClick(View v) {
				service.userUptade(kullanici,kullanici.getParola());
				if(!kullanici.getKonumId().equals("null"))
					service.konumGuncelle(kullanici.getEmail(), extras.getString("parola"), konum);
				else if(konum!=null){			
					service.addKonum(kullanici.getEmail(), extras.getString("parola"), konum);
				}
			}
		});
		Button button = (Button) findViewById(R.id.degistir0);
		button.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 0);
			}
		});

		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// display the current date (this method is below)
		

		tarihButton.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
	}

	private void updateDisplay() {
		tarih.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(mYear).append("-").append(mMonth + 1).append("-")
				.append(mDay));
		kullanici.setDogumTarihi(tarih.getText().toString());
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
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    mYear, mMonth, mDay);
	    }
	    return null;
	}

	private void setUserText() {
		String kullaniciStr = service.getUser(extras.getString("email"),
				extras.getString("parola"));
		kullanici = JsonParser.returnKullanici(kullaniciStr);
		ad.setText(kullanici.getAdi());
		soyad.setText(kullanici.getSoyad());
		email.setText(kullanici.getEmail());
		tarih.setText(kullanici.getDogumTarihi());
	}

	private void setLocationText() {
		if(kullanici.getKonumId().equals("null"))
			return;
		String konumStr = service.getKonum(extras.getString("email"),
				extras.getString("parola"));
		String ulkeStr = service.getUlke(extras.getString("email"),
				extras.getString("parola"));
		String sehirStr = service.getSehir(extras.getString("email"),
				extras.getString("parola"));

		if (konumStr.equals("-1")) {
			return;
		} else {
			konum = JsonParser.returnKonum(konumStr, ulkeStr, sehirStr);
			if (konum == null) {
				return;
			} else {
				uptadeLocationText();
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Uri targetUri = data.getData();
			Bitmap bitmap;
		    try {
		     bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
		     Bitmap bMapScaled = Bitmap.createScaledBitmap(bitmap, 50,50, true);

		      profil_photo.setImageBitmap(bMapScaled);
		
		    } catch (FileNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }

		}
	}

	private void inputDialog(final int i) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		// alert.setTitle("Title");
		// alert.setMessage("Message");

		// Set an EditText view to get user input
		
		final EditText input = new EditText(this);
		if(i==4)
			input.setTransformationMethod(new PasswordTransformationMethod());
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				switch (i) {
				case 1:
					kullanici.setAdi(input.getText().toString());
					ad.setText(kullanici.getAdi());
					break;
				case 2:
					kullanici.setSoyad(input.getText().toString());
					soyad.setText(kullanici.getSoyad());
					break;
				case 3:
					kullanici.setEmail(input.getText().toString());
					email.setText(kullanici.getEmail());
					break;
				case 4:
					kullanici.setParola(Mda5.getMD5(input.getText().toString()));
					break;
				}

			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();
	}
	private OnClickListener parolaListener = new OnClickListener() {
		
	
		public void onClick(View v) {
			inputDialog(4);
		}
	};
	private OnClickListener adListener = new OnClickListener() {

		public void onClick(View v) {
			inputDialog(1);

		}
	};
	private OnClickListener soyAdListener = new OnClickListener() {

		public void onClick(View v) {
			inputDialog(2);

		}
	};
	private OnClickListener emailListener = new OnClickListener() {

		
		public void onClick(View v) {
			inputDialog(3);

		}
	};
	private OnClickListener konumListener = new OnClickListener() {

		
		public void onClick(View v) {
			locationManager = new MyLocationManager();
			locationManager.getLocation(getApplicationContext(), result);
		}
	};
	private LocationResult result = new LocationResult() {

		@Override
		public void gotLocation(Location location) {
			uptadeLocation(location);

		}
	};

	private void uptadeLocation(Location location) {
		geocoder = new Geocoderapi();

		coordinate = new Coordinates(Double.toString(location.getLatitude()),
				Double.toString(location.getLongitude()));
		address = geocoder.geocode(coordinate);
		konum = new Konum();
		konum.setBoylam(location.getLongitude());
		konum.setEnlem(location.getLatitude());
		konum.setSehir(address.getCity());
		konum.setUlke(address.getCountry());
		uptadeLocationText();
	}

	private void uptadeLocationText() {
		//enlem.setText("" + konum.getEnlem());
		//boylam.setText("" + konum.getBoylam());
		sehir.setText(konum.getSehir());
		ulke.setText(konum.getUlke());
	}
}
