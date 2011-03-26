package com.example.evci;

import java.security.acl.LastOwnerException;
import java.util.Date;
import java.util.Calendar;
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
	Mail send = new Mail("ahmetcan196@gmail.com","mesela12");
	DbHelper db;
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
				Calendar calender=Calendar.getInstance();
				Date date=calender.getTime();
				try {
					//send.setBody(Integer.toString(sayi));
					send.setSubject("Ahbap Onay Kodu");
					send.setTo(mail_edress.getText().toString());
					db=new DbHelper(getApplicationContext());
					db.deleteAll();
					db.insert(Integer.toString(sayi)," tarih: "+" "+date.getMonth()+" "+date.getDate()+" "+date.getHours()+" "+date.getMinutes());
					Toast.makeText(KayitOl.this, db.selectAll().get(0), Toast.LENGTH_SHORT);
					Toast.makeText(KayitOl.this, Integer.toString(sayi),
							Toast.LENGTH_SHORT).show();
					send.setBody(db.selectAll().get(0));
					 send.send();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
	}

}
