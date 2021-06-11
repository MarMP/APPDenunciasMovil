package com.example.appdenuncias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class NoticiasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_noticias, container, false);
        WebView myWebView = (WebView) v.findViewById(R.id.webview);
        myWebView.loadUrl("https://iesalandalus.org/joomla/index.php");
        return v;
    }
}