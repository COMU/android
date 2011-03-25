package com.example.evci;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KayitOl extends Activity {
	EditText name;
	EditText surname;
	EditText mail_edress;
	Mail send = new Mail();
	DbHelper db=new DbHelper(this);
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		Button signIn = (Button) findViewById(R.id.buttonSignIn);

		signIn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name = (EditText) findViewById(R.id.txtName);
				surname = (EditText) findViewById(R.id.txtSurname);
				mail_edress = (EditText) findViewById(R.id.txtEmail);
				Random random = new Random();
				int sayi = random.nextInt(10000) - 1000;
				try {
					send.setBody(Integer.toString(sayi));
					send.setSubject("Ahbap Onay Kodu");
					send.setTo(mail_edress.getText().toString());
				//	send.send();
					db.insert(Integer.toString(sayi),"10 nisan");
					Toast.makeText(KayitOl.this, Integer.toString(sayi),
							Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
	}

}
