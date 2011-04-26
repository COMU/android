package com.example.evci;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
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
	RestTest mailkontrol=new RestTest();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		Button signIn = (Button) findViewById(R.id.buttonSignIn);

		signIn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//kullanicinin bilgilerinin alinmasi
				name = (EditText) findViewById(R.id.txtName);
				surname = (EditText) findViewById(R.id.txtSurname);
				mail_edress = (EditText) findViewById(R.id.txtEmail);
				
				//onay kodu uretimi
				Random random = new Random();
				int sayi = random.nextInt(10000) - 1000;
				byte[] dizi=new byte[30];
				random.nextBytes(dizi);
				String str="";
				for(int i=0;i<30;i++){
				str+=Integer.toHexString(0xF & dizi[i]);
				}
		
			Toast.makeText(KayitOl.this,str, Toast.LENGTH_LONG).show();
				Calendar calender=Calendar.getInstance();
				Date date=calender.getTime();
				
				//Mail gonderme(onay kodu için)
				try {
					//send.setBody(Integer.toString(sayi));
					Toast.makeText(KayitOl.this," adress: "+mail_edress.toString(), Toast.LENGTH_LONG);
					if(mailkontrol.kontrolEt(mail_edress.getText().toString())){
					send.setSubject("Ahbap Onay Kodu");
					send.setTo(mail_edress.getText().toString());

					send.setBody("merhaba");
					if(send.send()){
						//Toast.makeText(KayitOl.this, db.selectAll().get(0), Toast.LENGTH_SHORT);
						Toast.makeText(KayitOl.this, Integer.toString(sayi),
								Toast.LENGTH_SHORT).show();}
					Toast.makeText(KayitOl.this," mail gonderildi", Toast.LENGTH_LONG);
				}
					else
						Toast.makeText(KayitOl.this," lutfen dogru mail adresi giriniz!"+mail_edress.toString(), Toast.LENGTH_LONG);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
