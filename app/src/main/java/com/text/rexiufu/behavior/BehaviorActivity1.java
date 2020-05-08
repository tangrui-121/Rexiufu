package com.text.rexiufu.behavior;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.text.rexiufu.R;

public class BehaviorActivity1 extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior1);
        bindView();
    }

    private void bindView() {
        webview = findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        //支持https  留着本界面 而非打开浏览器
        webview.setWebViewClient(new WebViewClient() {
                                     @Override
                                     public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                                         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                             webview.getSettings()
                                                     .setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                                         }
                                     }
                                 }
        );
        webview.loadUrl("https://www.baidu.com");
    }
}