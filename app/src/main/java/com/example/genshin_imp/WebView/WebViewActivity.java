package com.example.genshin_imp.WebView;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.genshin_imp.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);

        // 获取 WebView 的设置
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用 JavaScript

        // 设置一个 WebViewClient，用于处理页面加载和各种通知
        webView.setWebViewClient(new WebViewClient());

        // 加载一个网页
        webView.loadUrl("https://ys.mihoyo.com/");
    }

    // 处理 WebView 返回键，如果 WebView 可以返回，则返回上一个网页；否则退出应用程序
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
