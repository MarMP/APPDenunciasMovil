package com.example.appdenuncias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Noticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://iesalandalus.org/joomla/index.php");
    }
}