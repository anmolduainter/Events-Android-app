package com.example.anmol.events.OnSiteWeb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.anmol.events.R;

public class onsite extends AppCompatActivity {


    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onsiteweb);
        webView= (WebView) findViewById(R.id.WebView);
        String url=getIntent().getExtras().getString("link");
        WebViewClient webViewClient=new WebViewClient();
        webView.setWebViewClient(webViewClient);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }
}

