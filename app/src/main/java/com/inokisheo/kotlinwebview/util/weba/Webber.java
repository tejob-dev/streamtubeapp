package com.inokisheo.kotlinwebview.util.weba;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;
import com.inokisheo.kotlinwebview.R;
import com.inokisheo.kotlinwebview.util.PrefManager;

import java.util.Random;

public class Webber {

    private Runnable runnable = null;
    private Activity ctx;
    private Handler handler;
    private LinearLayout webview_receivep, redirectAdsLayp;
    private RelativeLayout childLayoutp;
    private TextView titleTextp;
    private Button mainCloseButtonp;
    private ProgressBar progress_webbarLayp;
    private WebView myWebViewp;
    private MyWebPUBChromeClient myWebChrp;
    private PrefManager prefManager;

    public void initWeb(Activity context, Handler handler){
        this.ctx = context;
        this.handler = handler;
        this.prefManager = new PrefManager(context);
        this.webview_receivep = (LinearLayout) context.findViewById(R.id.webview_receivein);
        this.childLayoutp = (RelativeLayout) context.findViewById(R.id.childLayoutin);
        this.titleTextp = (TextView) context.findViewById(R.id.mainTitleTextin);
        this.mainCloseButtonp = (Button) context.findViewById(R.id.mainCloseButtonin);
        this.progress_webbarLayp = (ProgressBar) context.findViewById(R.id.progress_webbarin);
        this.redirectAdsLayp = (LinearLayout) context.findViewById(R.id.redirectAdsin);
        this.myWebViewp = (WebView) context.findViewById(R.id.webviewin);
    }

//    public Runnable runnWeb(){
//
//        System.out.println("Web Ad run");
//        runnable = new Runnable() {
//
//            public void run() {
//                System.out.println("Web Ad run run");
//                int num_pub = prefManager.getInt("k_number_web_pub");
//                if(num_pub != 0){
//                    float time = 30/num_pub;
//                    int timer = (int) time;
//                    String links = prefManager.getString("web_ad_link");
//                    String[] listLink = links.split(";;");
//                    //System.out.println("Web Ad list "+listLink.toString());
//                    if(listLink != null){
//                        if(listLink.length > 0){
//                            int pos = new Random().nextInt(listLink.length);
//                            if (pos > -1) {
//                                setUppWeb(listLink[pos]);
//                                handler.postDelayed(runnable, timer * 60000);
//                            }
//                            //System.out.println("Web Ad run url "+listLink[pos]);
//                        }
//                    }
//                }
//
//            }
//        };
//        int pwebc = prefManager.getInt("pub_web_count");
//        if(pwebc > 0){
//            prefManager.setInt("pub_web_count", pwebc-1);
//        }else {
//            if(prefManager.getBoolByName("k_is_ad_web_enable") == true) handler.postDelayed(runnable, 5000);
//            prefManager.setInt("pub_web_count", prefManager.getNumbValueByName("k_numb_repeat_pub_web_save"));
//        }
//
//        return runnable;
//    }

    private void setUppWeb(String url){

        //popup proplabs

        mainCloseButtonp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myWebChrp.isChildOpen()) myWebChrp.closeChild();
            }
        });

        new WebPUBSetter(ctx, myWebViewp);
        myWebViewp.getSettings().setLoadWithOverviewMode(true);
        myWebViewp.getSettings().setUseWideViewPort(true);
        myWebViewp.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWebViewp.getSettings().setGeolocationEnabled(false);  // normally set true
        myWebViewp.getSettings().setSupportMultipleWindows(true);
        myWebChrp = new MyWebPUBChromeClient(ctx, mainCloseButtonp, childLayoutp, webview_receivep,progress_webbarLayp, titleTextp,"no");
        myWebViewp.setWebChromeClient(myWebChrp);
        myWebViewp.loadUrl(url);
    }

    public void initView(LinearLayout webview_receivep, RelativeLayout childLayoutp,
                         TextView titleTextp, Button mainCloseButtonp,
                         ProgressBar progress_webbarLayp, LinearLayout redirectAdsLayp,
                         WebView myWebViewp) {
        this.webview_receivep = webview_receivep;
        this.childLayoutp = childLayoutp;
        this.titleTextp = titleTextp;
        this.mainCloseButtonp = mainCloseButtonp;
        this.progress_webbarLayp = progress_webbarLayp;
        this.redirectAdsLayp = redirectAdsLayp;
        this.myWebViewp = myWebViewp;
    }
}
