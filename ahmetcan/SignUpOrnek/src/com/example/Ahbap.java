package com.example;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Ahbap extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.ahbap);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab
	    Bundle e = getIntent().getExtras();
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    
	    intent = new Intent().setClass(Ahbap.this, Profil.class);
	    intent.putExtra("email", e.getString("email"));
	    intent.putExtra("parola", e.getString("parola"));
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("profil").setIndicator("Profil",res.getDrawable(R.drawable.profile))

	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(Ahbap.this, Arama.class);
	    intent.putExtra("email", e.getString("email"));
	    intent.putExtra("parola", e.getString("parola"));
	    spec = tabHost.newTabSpec("arama").setIndicator("Arama",res.getDrawable(R.drawable.arama))
	                  .setContent(intent);
	   
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(Ahbap.this, Mesaj.class);
	    spec = tabHost.newTabSpec("mesaj").setIndicator("Mesaj",res.getDrawable(R.drawable.mesaj))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	}
}
