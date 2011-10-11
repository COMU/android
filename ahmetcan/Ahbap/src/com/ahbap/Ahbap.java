package com.ahbap;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Ahbap extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ahbap);
		Resources res = getResources(); 
	    TabHost tabHost = getTabHost();  
	    TabHost.TabSpec spec;  
	    Intent intent; 

	    
	    
	    intent = new Intent().setClass(this, Home.class);
	    spec = tabHost.newTabSpec("songs").setIndicator("Home")
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, Profile.class);

	    
	    spec = tabHost.newTabSpec("artists").setIndicator("Profile")
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    
	    intent = new Intent().setClass(this, Profile.class);
	    spec = tabHost.newTabSpec("albums").setIndicator("Search")
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, Profile.class);
	    spec = tabHost.newTabSpec("songs").setIndicator("Message")
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    


	    tabHost.setCurrentTab(0);

	}
}