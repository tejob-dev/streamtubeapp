package com.izziopsdev.mmanime.util.weba;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPUBSetter {

    private final WebView viewW;
    private final Context ctx;

    public WebPUBSetter(Context context, WebView view){
        this.viewW = view;
        this.ctx = context;
        setter();
    }

    @SuppressLint("NewApi")
    private void setter() {

        WebSettings settings = viewW.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setGeolocationEnabled(false);  // normally set true

        settings.setSupportMultipleWindows(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setUserAgentString(WebSettings.getDefaultUserAgent(ctx));
        settings.setSupportZoom(true);
        //These database/cache/storage calls might not be needed, but just in case
//        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
//        settings.setAppCachePath(ctx.getDatabasePath("myAppCache").getAbsolutePath());
        settings.setDatabasePath(ctx.getDatabasePath("myDatabase").getAbsolutePath()); //deprecated in Android 4.4 KitKat (API level 19)
        viewW.clearCache(true);
        viewW.clearFormData();
        viewW.clearHistory();
        viewW.clearMatches();
        viewW.clearSslPreferences();

        viewW.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    viewW.setVisibility(View.INVISIBLE);
                }
                return false;
            }

        });

        viewW.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            //If no internet, redirect to error page
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }

            public void onPageFinished(WebView view, String url) {


//                view.loadUrl(String.format("javascript:console.log($('a').hide());"));
//                view.loadUrl(String.format("javascript:console.log($('span').hide());"));
//                view.loadUrl(String.format("javascript:console.log($('input').hide());"));
//                view.loadUrl(String.format("javascript:console.log($('p').hide());"));
//                view.loadUrl(String.format("javascript:console.log($('li').hide());"));
                //view.loadUrl(String.format("javascript:console.log($('.row').hide());"));

                view.loadUrl(String.format("javascript:console.log($('video').prop('muted', true));"));
                //view.loadUrl(String.format("javascript:console.log($('iframe').remove());"));
                view.loadUrl(String.format("javascript:console.log($('audio').prop('muted', true));"));

                view.setVisibility(View.VISIBLE);

                view.getSettings().setSupportZoom(true);
                view.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);

                int orient = ctx.getResources().getConfiguration().orientation;
                if(orient == Configuration.ORIENTATION_PORTRAIT){
                    view.zoomIn();
                    view.zoomIn();
                    view.scrollTo(0,0);
                }else {
                    view.zoomIn();
                    view.scrollTo(0,0);
                }
                //new ToastMsg(ctx).toastIconSuccess("Web End Loaded");
            }
        });

    }

}
