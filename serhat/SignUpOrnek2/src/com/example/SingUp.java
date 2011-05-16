package com.example;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SingUp extends Activity {
	/** Called when the activity is first created. */
	private EditText email;
	private EditText ad;
	private EditText soyad;
	private Button singUp;
	private ApplicationService service;
	private DbHelper db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.main);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.blue_sofa);
		
		db = new DbHelper(getApplicationContext());
		email = (EditText) findViewById(R.id.email);
		ad = (EditText) findViewById(R.id.name);
		soyad = (EditText) findViewById(R.id.lastName);
		singUp = (Button) findViewById(R.id.ok_button);
		
		
		singUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				service = new ApplicationService(getApplicationContext());
				String adi = ad.getText().toString();
				String soyadi = soyad.getText().toString();
				String mail = email.getText().toString();
				if (!adi.equals("") && !soyadi.equals("") && !mail.equals("")) {

					if (service.emailControl(mail)) {
						Kullanici user = new Kullanici();
						user.setAdi(ad.getText().toString());
						user.setSoyad(soyad.getText().toString());
						user.setEmail(email.getText().toString());
						user.setKey(Generate.key(30));
						user.setParola(Generate.key(6));
						
						if (service.addUser(user)) {
							db.deleteAll();
							db.insertKey(user.getKey());
							
							Intent intent = new Intent(SingUp.this,
									Activate.class);
							intent.putExtra("mail", "http://www."
									+ user.getEmail().split("@")[1]);
							startActivity(intent);
							finish();
						} else {
							showAlert(getString(R.string.tryAgain));
						}
					} else {
						showAlert(getString(R.string.badEmail));
					}
				} else {
					showAlert(getString(R.string.emptyField));
				}
			}
		});

	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		db.close();
	}
	private void showAlert(String message) {
		AlertDialog.Builder alert = new AlertDialog.Builder(SingUp.this);
		alert.setMessage(message);
		alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		alert.show();
	}
	
}