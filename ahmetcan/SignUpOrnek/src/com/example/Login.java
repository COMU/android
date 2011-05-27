package com.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity{
	private ApplicationService service;
	private EditText email;
	private EditText parola;
	
	public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.login);
		
		service = new ApplicationService(getApplicationContext());
		email = (EditText)findViewById(R.id.email);
		parola = (EditText)findViewById(R.id.parola);
		
		Button signUp=(Button)findViewById(R.id.singButton);
		signUp.setOnClickListener(signUpListener);
		
		Button login =(Button)findViewById(R.id.loginButton);
		login.setOnClickListener(loginListener);
	}
	private OnClickListener loginListener = new OnClickListener() {
		
		
		public void onClick(View v) {
			String mail = email.getText().toString();
			String paro = parola.getText().toString();
			if(!mail.equals("") && !paro.equals("")){
				//if(service.login(mail,Mda5.getMD5(paro))){
				if(true){
					Intent intent =new Intent(Login.this, Ahbap.class);
					intent.putExtra("email", mail);
					intent.putExtra("parola", Mda5.getMD5(paro));
					startActivity(intent);
					finish();
				}else{
					showAlert("Olmadi be ahbap");
				}
			}
			
		}
	};
	private OnClickListener signUpListener = new OnClickListener() {
		
		
		public void onClick(View v) {
			Intent intent=new Intent(Login.this,SingUp.class);
			startActivity(intent);
			
		}
	};
	private void showAlert(String message) {
		AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
		alert.setMessage(message);
		alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		alert.show();
	}
}
