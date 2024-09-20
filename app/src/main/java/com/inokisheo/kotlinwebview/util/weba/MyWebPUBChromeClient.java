package com.inokisheo.kotlinwebview.util.weba;

import android.app.Activity;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import com.inokisheo.kotlinwebview.R;

public class MyWebPUBChromeClient extends WebChromeClient {

    private final String typ;

    ProgressBar progress;
    Activity myActivity;
    RelativeLayout childLayout;
    LinearLayout web_receiv;
    private TextView titleText;
    Button closers;
    private WebView newView;
    private boolean loadIsFinish = false;


    public MyWebPUBChromeClient(Activity activity, Button closer, RelativeLayout wrappingLayout, LinearLayout browserLayout, ProgressBar progressBar, TextView titleText, String type) {
        myActivity = activity;
        this.progress = progressBar;
        this.childLayout = wrappingLayout;
        this.web_receiv = browserLayout;
        this.titleText = titleText;
        this.typ = type;
        this.closers = closer;

    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        result.confirm();
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        result.confirm();
        return true;
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        result.confirm();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        // remove any current child views
        //new ToastMsg(myActivity).toastIconSuccess("popup show");

        web_receiv.removeAllViews();
        // make the child web view's layout visible
        if(typ.equals("exo")){
            childLayout.setVisibility(View.VISIBLE);
        }else childLayout.setVisibility(View.INVISIBLE);

        //closers.setVisibility(View.INVISIBLE);
        progress.setVisibility(ProgressBar.INVISIBLE);


        // now create a new web view
        newView = new WebView(myActivity);
        WebSettings settings = newView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUserAgentString(WebSettings.getDefaultUserAgent(myActivity));
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(true);
        settings.setUseWideViewPort(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setGeolocationEnabled(false);  // normally set true

        //These database/cache/storage calls might not be needed, but just in case
//        settings.setAppCacheEnabled(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
//        settings.setAppCachePath(myActivity.getDatabasePath("myAppCache").getAbsolutePath());
        settings.setDatabasePath(myActivity.getDatabasePath("myDatabase").getAbsolutePath()); //deprecated in Android 4.4 KitKat (API level 19)

        newView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                loadIsFinish = false;
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                loadIsFinish = true;
                //closers.setVisibility(View.VISIBLE);
                super.onPageCommitVisible(view, url);
            }



            /**
             * Need to grab the title of the web page
             * @param view - - the web view
             * @param url - the URL of the page
             */



            public void onPageFinished(WebView view, String url) {
                // once the view has loaded, display its title
                titleText.setText(view.getTitle());
                //loadIsFinish = true;


            }
        });

        newView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if(newProgress < 100 && progress.getVisibility() == ProgressBar.INVISIBLE){
                    progress.setVisibility(ProgressBar.VISIBLE);
                }
                progress.setProgress(newProgress);
                if(newProgress == 100) {
                    progress.setVisibility(ProgressBar.INVISIBLE);
                   // closers.setVisibility(View.VISIBLE);
                }
            }
        });
        // add the new web view to the layout
        newView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        web_receiv.addView(newView);
        // tell the transport about the new view
        WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
        transport.setWebView(newView);
        resultMsg.sendToTarget();

        // let's be cool and slide the new web view up into view
        if(typ.equals("exo")){
            Animation slideUp = AnimationUtils.loadAnimation(myActivity, R.anim.slide_up_pub);
            childLayout.startAnimation(slideUp);
        }

        return true;
    }

    public boolean isLoadEnd(){
        return loadIsFinish;
    }

    public String getUrlNewWeb(){
        return newView.getUrl();
    }

    /**
     * Lower the child web view down
     */
    public void closeChild() {
        //Log.v("tkfaart", "Closing Child WebView");
        //Animation slideDown = AnimationUtils.loadAnimation(myActivity, R.anim.slide_down);
//        childLayout.startAnimation(slideDown);
//        slideDown.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                titleText.setText("");
//                childLayout.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

        titleText.setText("");
        if(newView != null) newView.stopLoading();
        childLayout.setVisibility(View.INVISIBLE);
    }

    public boolean isChildOpen() {
        return childLayout.getVisibility() == View.VISIBLE;
    }
}
