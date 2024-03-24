package com.example.njxfuture;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WebView1 extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Log.d("YourFragment", "running");

        try {
            String url = getIntent().getStringExtra("url");
            Log.d("YourFragment", url);

            webView = findViewById(R.id.webView);

            if (webView != null) {
                setupWebViewSettings();
                loadUrl(url);
            } else {
                Log.e("YourFragment", "WebView is null");
            }
        } catch (Exception e) {
            Log.e("YourFragment", "Exception in WebView1 onCreate: " + e.getMessage());
            e.printStackTrace();
        }
        // Inflate the custom ActionBar layout
        View actionBarView = LayoutInflater.from(this).inflate(R.layout.webview_action_bar, null);

        // Set custom ActionBar layout as ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(actionBarView);
        actionBar.setDisplayShowCustomEnabled(true);
        TextView actionBarTitle = actionBarView.findViewById(R.id.webView_action_bar);
        ImageView actionBack = actionBarView.findViewById(R.id.backButton);
        ImageView actionRefresh = actionBarView.findViewById(R.id.refreshWebView);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        actionBarTitle.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1));
        actionBarTitle.setGravity(android.view.Gravity.CENTER);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        actionBar.setCustomView(actionBarView, params);
        actionBack.setOnClickListener(view -> onBackPressed());
        actionRefresh.setOnClickListener(view -> webView.reload());
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebViewSettings() {
        try {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);
        } catch (Exception e) {
            Log.e("YourFragment", "Exception in setupWebViewSettings: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadUrl(String url) {
        try {
            webView.loadUrl(url);
            webView.setWebViewClient(new WebViewClient());
            webView.setWebChromeClient(new WebChromeClient());
        } catch (Exception e) {
            Log.e("YourFragment", "Exception in loadUrl: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        // Check if the WebView can go back
        if (webView != null && webView.canGoBack()) {
            webView.goBack();  // Navigate back in WebView
        } else {
            super.onBackPressed();  // Default behavior (close the activity)
        }
    }
}
