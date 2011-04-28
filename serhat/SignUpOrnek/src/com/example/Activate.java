package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

public class Activate extends Activity {
	private WebView webView;
	private Button returnBack;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		Bundle extras = getIntent().getExtras();
		webView = (WebView)findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(extras.getString("mail"));
	}
}
