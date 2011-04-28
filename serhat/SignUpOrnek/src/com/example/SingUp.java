package com.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.main);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.blue_sofa);
		email = (EditText) findViewById(R.id.email);
		ad = (EditText) findViewById(R.id.name);
		soyad = (EditText) findViewById(R.id.lastName);
		singUp = (Button) findViewById(R.id.ok_button);
		singUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				service = new ApplicationService(getApplicationContext());
				AlertDialog.Builder builder = new AlertDialog.Builder(
						SingUp.this);
				String adi = ad.getText().toString();
				String soyadi = soyad.getText().toString();
				String mail = email.getText().toString();
				if (service.emailControl(mail)) {
					Kullanici user = new Kullanici();

					if (!adi.equals("") && !soyadi.equals("")) {
						user.setAdi(ad.getText().toString());
						user.setSoyad(soyad.getText().toString());
						user.setEmail(email.getText().toString());
						user.setKey(Generate.key(30));
						user.setParola(Generate.key(6));
						if (service.addUser(user)) {
							Mail mailSender= new Mail("ahmetcan196@gmail.com","mesela12");
							String[] toArr = { mail };
							mailSender.setTo(toArr);
							mailSender.setSubject("Ahbap Activation");
							mailSender.setBody("Parola = " + user.getParola() + "\n" +
									"Aktivasyon Linki = "+"http://10.0.2.2:8000/api/aktiflestir/" + 
									 user.getKey());
							try {
								if(mailSender.send()){
									Intent intent =new Intent(SingUp.this,Activate.class);
									intent.putExtra("mail", "http://www." + user.getEmail().split("@")[1]);
									startActivity(intent);
									finish();
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							
						}
					} else {
						builder.setMessage(R.string.emptyField);

						builder.setNeutralButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

									}
								});
						builder.show();
					}
				} else {
					builder.setMessage(R.string.tryAgain);
					builder.setNeutralButton("Ok",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

								}
							});
					builder.show();
				}
			}
		});

	}
}