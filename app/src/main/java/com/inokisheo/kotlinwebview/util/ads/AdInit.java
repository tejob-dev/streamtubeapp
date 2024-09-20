package com.inokisheo.kotlinwebview.util.ads;

import android.content.Context;
import android.widget.RelativeLayout;

import com.inokisheo.kotlinwebview.util.PrefManager;

public class AdInit {

    private  PrefManager pref;
    private  boolean apolovM;
     boolean wortzM = false;
     boolean fanM = false;
     boolean unityM = false;
    private final String currId;

    public AdInit(String currPlace){
        currId = currPlace;
    }

    public  void showBanAndRewAd(Context context, final RelativeLayout banner_container){
        //mAdMobInterstitialHelper.checkAd(this);

        pref = new PrefManager(context.getApplicationContext());

        checkRandomStatement(context, pref);

        if(pref.getBoolean("k_is_ad_enable") == true){
            banner_container.removeAllViews();

            //FAN work or not
            if (pref.getBoolean("k_is_ad_fan_enable") == true && fanM) {
                //FANHelper.showFANRew(context, pref.getString("k_is_ad_rew_fan"), pref.getString("k_is_ad_inters_fan"));
//                FANHelper.showFANBan(context, banner_container, pref.getString("k_is_ad_ban_fan"));
            }

            if (pref.getBoolean("k_is_ad_unity_enable") == true && unityM) {
                UnityHelper.showUnityBanners(context, banner_container, pref.getString("k_is_ad_ban_unity"));
                //FANHelper.showFANRew(context, pref.getString("k_is_ad_rew_fan"), pref.getString("k_is_ad_inters_fan"));
            }
        }
    }

    private void checkRandomStatement(Context context, PrefManager pref) {

        if(currId.equals(pref.getString("CURR_CNAME_AD"))) return;

        boolean get_random_state = pref.getBoolean("random_state");
        int get_random_state_name = pref.getInt("random_state_name");

        if(get_random_state == true){

            boolean anAdSel = false;


            if(pref.getBoolean("k_is_ad_fan_enable") == true
                    && get_random_state_name < 2 && anAdSel == false) {
                fanM = true;
                anAdSel = true;
                pref.setInt("random_state_name", 2);
            }

            if(pref.getBoolean("k_is_ad_unity_enable") == true
                    && get_random_state_name < 4 && anAdSel == false) {
                unityM = true;
                anAdSel = true;
                pref.setInt("random_state_name", 4);
            }

            if(anAdSel == false) pref.setInt("random_state_name", 0);

        }else{
            wortzM = true;
            fanM = true;
            apolovM = true;
            unityM = true;
        }

        pref.setString("CURR_CNAME_AD", currId);
    }


    public void showRewOnlyAd(Context context){
        //mAdMobInterstitialHelper.checkAd(this);
        pref = new PrefManager(context.getApplicationContext());

        checkRandomStatement(context, pref);

        if(pref.getBoolean("k_is_ad_enable") == true){

            if( pref.getInt("countInt") == 0){

                if(pref.getBoolean("k_is_ad_unity_enable") == true && unityM) {
                    //AdInit.showBanAndRewAd(context, banner_container, pref.getString("k_is_ad_ban_wortz"));
                    UnityHelper.showUnityIntersRews(context, pref.getString("k_is_ad_rew_unity"));
                }

                //FAN work or not
                if (pref.getBoolean("k_is_ad_fan_enable") == true && fanM) {
                    //FANHelper.showFANBan(context, banner_container, pref.getString("k_is_ad_ban_fan"));
//                    FANHelper.showFANRew(context, pref.getString("k_is_ad_rew_fan"), pref.getString("k_is_ad_inters_fan"));
                }

                pref.setInt("countInt", pref.getInt("k_numb_repeat_pub_web_save") );;
            }

            if( pref.getInt("countInt") != 0){
                int countAdR = pref.getInt("countInt");
                countAdR--;
                pref.setInt("countInt", countAdR);
            }
        }
    }

}
