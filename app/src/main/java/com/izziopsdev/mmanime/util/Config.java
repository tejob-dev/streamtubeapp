package com.izziopsdev.mmanime.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Config {


    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36";
//    public static String update_url = "https://raw.githubusercontent.com/tejob-dev/streamtubeapp/kotlin-mmstream/app/update-changelog.json";
    public static String update_url_enc = "aHR0cHM6Ly9yYXcuZ2l0aHVidXNlcmNvbnRlbnQuY29tL3Rlam9iLWRldi9zdHJlYW10dWJlYXBwL2tvdGxpbi1tbXN0cmVhbS9hcHAvdXBkYXRlLWNoYW5nZWxvZy5qc29u";
    ///tejob-dev/streamtubeapp/tree/kotlin-mmstream

    public static boolean isNetWorkOk(Activity context) {
        boolean status = false;
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;
    }
}
