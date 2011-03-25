package com.example.evci;


import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GirisEkrani extends Activity {
	TextView forgetPasswd;
	EditText userName;
	EditText passwd;
	ProgressDialog progressDialog;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button signIn = (Button) findViewById(R.id.buttonSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), KayitOl.class);
                startActivityForResult(myIntent, 0);
            }

        });
      //  TelephonyManager telephonyManager;
     //   telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
       // final String s= telephonyManager.getDeviceId();
        
        Button LogIn = (Button) findViewById(R.id.buttonLogIn);
        LogIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            	//Toast.makeText(GirisEkrani.this,s ,Toast.LENGTH_SHORT).show();
            	userName=(EditText) findViewById(R.id.txtUserName);
            	passwd=(EditText)findViewById(R.id.txtPassword);
            
                Intent myIntent = new Intent(view.getContext(), KayitOl.class);
                startActivityForResult(myIntent, 0);
                
            }
        });
        forgetPasswd=(TextView) findViewById(R.id.forgetPasswd);
        forgetPasswd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(GirisEkrani.this,"parolaniz e-postaniza gonderilecek" ,Toast.LENGTH_SHORT).show();
			}
		});
        
    }
}