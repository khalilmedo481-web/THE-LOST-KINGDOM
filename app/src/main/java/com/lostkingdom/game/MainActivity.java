package com.lostkingdom.game;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        gameView = new WebView(this);
        gameView.setBackgroundColor(Color.rgb(7, 17, 30));
        gameView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        gameView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        WebSettings settings = gameView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setMediaPlaybackRequiresUserGesture(false);

        gameView.setWebChromeClient(new WebChromeClient());
        gameView.setWebViewClient(new WebViewClient());
        setContentView(gameView);
        gameView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if (gameView != null && gameView.canGoBack()) {
            gameView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (gameView != null) {
            gameView.loadUrl("about:blank");
            gameView.destroy();
            gameView = null;
        }
        super.onDestroy();
    }
}
