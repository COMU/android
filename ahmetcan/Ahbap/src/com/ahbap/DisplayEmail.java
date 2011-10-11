package com.ahbap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class DisplayEmail extends Activity {
	private WebView webView;
	private Button returnBack;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		Bundle extras = getIntent().getExtras();
		webView=(WebView)findViewById(R.id.webview);
		webView.setWebViewClient(new HelloWebViewClient());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(extras.getString("mail"));
		returnBack = (Button) findViewById(R.id.button);
		returnBack.setOnClickListener(new OnClickListener() {
			
	
			public void onClick(View v){
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	private class HelloWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	}
}
