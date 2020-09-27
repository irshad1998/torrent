package com.gdr.mallutorrentz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.gdr.mallutorrentz.R;

public class CommonWeb extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web);

        Intent intent = getIntent();

        String url = intent.getStringExtra("url");

        webView = (WebView) findViewById(R.id.common_web);
                webView.setWebChromeClient(new WebChromeClient());
                webView.loadUrl(url);


    }
}