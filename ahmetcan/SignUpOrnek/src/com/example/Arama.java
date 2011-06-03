package com.example;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

public class Arama extends Activity {
	private Bundle extras;
	private List<String> ulke;
	private List<String> sehir;
	private List<String> meslek;
	List<String> bulunanlar_list;
	private Button ara;
	private ListView bulunanlar;
	String sehirler;
	String ulke_adi;
	String meslekler;
	AutoCompleteTextView textView1;
	AutoCompleteTextView textView2;
	AutoCompleteTextView textView3;
	WebService webservice;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arama);
		bulunanlar = (ListView) findViewById(R.id.aranan);
		ulke = new ArrayList<String>();
		sehir = new ArrayList<String>();
		extras = getIntent().getExtras();
		webservice = new WebService(getApplicationContext());
		String ulkeler = webservice.getUlkeler(extras.getString("email"),
				extras.getString("parola"));
		JsonParser.returnUlkeler(ulkeler, ulke);
		
		textView1 = (AutoCompleteTextView) findViewById(R.id.autocomplete_country);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Arama.this,
				R.layout.list_item, ulke);
		textView1.setAdapter(adapter);

		textView2 = (AutoCompleteTextView) findViewById(R.id.autocomplete_city);
		textView2.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				ulke_adi = textView1.getText().toString();
				sehirler = webservice.getSehirler(extras.getString("email"),
						extras.getString("parola"), ulke_adi);
				JsonParser.returnSehirler(sehirler, sehir);
				ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
						Arama.this, R.layout.list_item, sehir);
				textView2.setAdapter(adapter2);

				;
				return false;
			}
		});
		textView3 = (AutoCompleteTextView) findViewById(R.id.autocomplete_job);
		textView3.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				meslek = new ArrayList<String>();
				meslekler = webservice.getMeslekler(extras.getString("email"),
						extras.getString("parola"));
				JsonParser.returnMeslekler(meslekler, meslek);
				ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
						Arama.this, R.layout.list_item, meslek);
				textView3.setAdapter(adapter3);
				return false;
			}
		});

		ara = (Button) findViewById(R.id.ara);
		ara.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String sehir_adi = textView2.getText().toString();
				bulunanlar_list = new ArrayList<String>();
				meslekler = textView3.getText().toString();
				String bulunan = webservice.getArananKisi(
						extras.getString("email"), extras.getString("parola"),
						ulke_adi, sehir_adi, meslekler);
				Intent intent = new Intent(getApplicationContext(), Map.class);
				intent.putExtra("bulunanlar", bulunan);
				startActivity(intent);
				/*JsonParser.returnAranan(bulunan, bulunanlar_list);		
				ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
						Arama.this, R.layout.list_item, bulunanlar_list);
				Arama.this.bulunanlar.setAdapter(adapter3);*/
			}
		});

	}
}