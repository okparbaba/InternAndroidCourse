package com.ynl.typeofwidgets

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.webkit.WebSettings
import android.webkit.WebView


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView = findViewById<WebView>(R.id.webview)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        val webViewClient = WebViewClientImpl(this)

        webView.webViewClient = webViewClient

        webView.loadUrl("https://www.facebook.com")

    }
}
