package com.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class KullaniciDetaylari extends Activity {
private Detay detay;
private Button kaydet;
private Button genderedit;
private AlertDialog.Builder alt_bld;
private TextView gender;
private Button egitimedit;
private TextView egitim;
private TextView meslek;
private WebService webservice;
private Bundle extras;

@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detay);
		webservice = new WebService(getApplicationContext());
		kaydet=(Button)findViewById(R.id.saveDetail);
		genderedit=(Button)findViewById(R.id.genderEdit);
		gender=(TextView)findViewById(R.id.genderShow);
		egitim=(TextView)findViewById(R.id.EduStatus);
		egitimedit=(Button)findViewById(R.id.edStatusEdit);
		meslek=(TextView)findViewById(R.id.Profession);
		extras = getIntent().getExtras();
		detay=new Detay();
		kaydet.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				webservice.addKullaniciDetaylari(extras.getString("email"),extras.getString("parola"),detay);
				finish();
			}
		});
		
		
}



public void openMeslekDuzenle(View v){
	
	final CharSequence[] items = getResources().getStringArray(R.array.profession);

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    builder.setTitle("Pick an item");

    builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

    public void onClick(DialogInterface dialog, int item) {

           Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();

           //If the Cheese item is chosen close the dialog box

           meslek.setText(items[item].toString());
           detay.setMeslek(items[item].toString());
            dialog.dismiss();
       }
    });

    AlertDialog alert = builder.create();


    alert.show();
	
	
}


public void openEgitimDuzenle(View v){
	
	
	final CharSequence[] items = getResources().getStringArray(R.array.eduStatus);

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    builder.setTitle("Pick an item");

    builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

    public void onClick(DialogInterface dialog, int item) {

           Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
           String s=items[item].toString();
           egitim.setText(items[item].toString());
           detay.setEgitimDurumu(s);
           dialog.dismiss();
       }

    });

    AlertDialog alert = builder.create();

    alert.show();
	
}

public void openCinsiyetDuzenle(View v){
	
	Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Lutfen Cinsiyetinizi Seciniz?")
            .setCancelable(true)
            .setPositiveButton("Erkek",
                    new DialogInterface.OnClickListener() {
                     
                        public void onClick(DialogInterface dialog,
                                int which) {
                            gender.setText("Erkek");
                            detay.setCinsiyet("Erkek");
                        }
                    })
            .setNegativeButton("Kadin",
                    new DialogInterface.OnClickListener() {

                    
                        public void onClick(DialogInterface dialog,
                                int which) {
                        	gender.setText("Kadin");
                        	detay.setCinsiyet("Kadin");
                        }
                    });
    AlertDialog dialog = builder.create();
    dialog.show();
}
}