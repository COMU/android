package com.exemple.mailkontrolu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MailKontrolu extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DNSControl dnskontrol=new DNSControl();
    	Boolean control = dnskontrol.validate("test@gmal.com");
		if (control)
		{
			Toast.makeText(MailKontrolu.this, "adres dogru", Toast.LENGTH_SHORT);
			
		}
    }
}