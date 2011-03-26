package com.example.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web extends Activity {
    /** Called when the activity is first created. */
    private WebView web;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        web=(WebView)findViewById(R.id.webview);
        web.setWebViewClient(new HelloWebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("http://gmail.com");

    }
	
	private class HelloWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	}

}