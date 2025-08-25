package com.example.pruebawebview

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)

        // Fă ca link-urile să se deschidă în WebView, nu în browser
        webView.webViewClient = object : WebViewClient() {

            override fun onReceivedError(
                view: WebView?,
                request: android.webkit.WebResourceRequest?,
                error: android.webkit.WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                // Logăm eroarea în Logcat
                android.util.Log.e("WebViewError", "URL: ${request?.url}, Error: ${error?.description}")
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: android.webkit.WebResourceRequest?,
                errorResponse: android.webkit.WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
                android.util.Log.e("WebViewHttpError", "URL: ${request?.url}, Status code: ${errorResponse?.statusCode}")
            }
        }


        // Activează JavaScript (majoritatea site-urilor moderne au nevoie)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE

        // Încarcă URL-ul dorit
        val htmlData = """
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>wbv</title>
    <style type="text/css">
        html { margin: 0; height: 100%; overflow: hidden; }
        iframe { position: absolute; left:0; right:0; bottom:0; top:0; border:0; }
    </style>
</head>
<body>
    <iframe id="helloflow-full" width="100%" height="100%" frameborder="0"
        allow="camera; microphone; autoplay; encrypted-media;"
        src="https://launch-workflow.trulioo.com/68a395db3b32485a66ce80a1">
    </iframe>
</body>
</html>
""".trimIndent()

        webView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null)

//        webView.loadUrl("https://launch-workflow.trulioo.com/68a395db3b32485a66ce80a1")
    }

//    override fun onBackPressed() {
//        if (webView.canGoBack()) {
//            webView.goBack()
//        } else {
//            super.onBackPressed()
//        }
//    }
}