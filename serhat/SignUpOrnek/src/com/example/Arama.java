package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class Arama extends Activity {
	private String array_spinner[];
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.arama);
	  Spinner edu_combo = (Spinner) findViewById(R.id.edu_spinner);
	    ArrayAdapter adapter0 = ArrayAdapter.createFromResource(
	            this, R.array.education, android.R.layout.simple_spinner_item);
	    adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    edu_combo.setAdapter(adapter0);
	    
	    Spinner gender_combo = (Spinner) findViewById(R.id.gender_spinner);
	    ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
	            this, R.array.gender, android.R.layout.simple_spinner_item);
	    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    gender_combo.setAdapter(adapter1);
	    
	    Spinner country_combo = (Spinner) findViewById(R.id.country_spinner);
	    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
	            this, R.array.country, android.R.layout.simple_spinner_item);
	    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    country_combo.setAdapter(adapter2);
	
	    
	    Spinner city_combo = (Spinner) findViewById(R.id.city_spinner);
	    ArrayAdapter adapter3 = ArrayAdapter.createFromResource(
	            this, R.array.city, android.R.layout.simple_spinner_item);
	    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    city_combo.setAdapter(adapter3);
	}
}