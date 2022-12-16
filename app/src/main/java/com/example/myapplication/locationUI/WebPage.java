package com.example.myapplication.locationUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.myapplication.R;


public class WebPage extends AppCompatActivity {
    WebView webView;
    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        link = getIntent().getStringExtra("LINK");
        webView = findViewById(R.id.webview1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
    }



    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
            Toast.makeText(this, "Going back", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Exitting a WebView", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }
}