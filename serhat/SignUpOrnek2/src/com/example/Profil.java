package com.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profil extends Activity {
	private Kullanici kullanici;
	private WebService service;
	private TextView ad;
	private TextView soyad;
	private TextView email;
	private Button ad_button;
	private Button soyad_button;
	private Button email_button;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil);
		Bundle extras = getIntent().getExtras();
		service = new WebService(getApplicationContext());
		ad = (TextView) findViewById(R.id.kullaniciText2);
		soyad = (TextView) findViewById(R.id.kullaniciText4);
		email = (TextView) findViewById(R.id.kullaniciText6);

		ad_button = (Button)findViewById(R.id.degistir);
		ad_button.setOnClickListener(adListener);
		soyad_button = (Button)findViewById(R.id.degistir2);
		soyad_button.setOnClickListener(soyAdListener);
		email_button = (Button)findViewById(R.id.degistir3);
		email_button.setOnClickListener(emailListener);
		
		String kullaniciStr = service.getUser(extras.getString("email"),
				extras.getString("parola"));
		kullanici = JsonParser.returnKullanici(kullaniciStr);

		ad.setText(kullanici.getAdi());
		soyad.setText(kullanici.getSoyad());
		email.setText(kullanici.getEmail());

		Button button = (Button) findViewById(R.id.degistir0);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 0);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Uri targetUri = data.getData();
			Toast.makeText(getApplicationContext(), targetUri.toString(),
					Toast.LENGTH_LONG).show();
		}
	}

	private void inputDialog(final int i) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		// alert.setTitle("Title");
		// alert.setMessage("Message");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
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

	private OnClickListener adListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			inputDialog(1);

		}
	};
	private OnClickListener soyAdListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			inputDialog(2);

		}
	};
	private OnClickListener emailListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			inputDialog(3);

		}
	};
}
