package com.inokisheo.kotlinwebview.util.ads;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;

import com.inokisheo.kotlinwebview.util.PrefManager;
//import com.unity3d.ads.IUnityAdsInitializationListener;
//import com.unity3d.ads.IUnityAdsLoadListener;
//import com.unity3d.ads.IUnityAdsShowListener;
//import com.unity3d.ads.UnityAds;
//import com.unity3d.ads.UnityAdsShowOptions;
//import com.unity3d.services.banners.BannerErrorInfo;
//import com.unity3d.services.banners.BannerView;
//import com.unity3d.services.banners.UnityBannerSize;
//import com.unity3d.services.banners.UnityBanners;
//import com.unity3d.services.banners.view.BannerPosition;

public class UnityHelper {

    public static void showUnityBanners(Context context, final RelativeLayout adContainer, String adUnitId){

//        if(UnityAds.isInitialized() == false) UnityAds.initialize(context, new PrefManager(context).getString("k_is_ad_unity_id"), false);
//        //adContainer.removeAllViews();
//        BannerView bottomBanner = new BannerView((Activity) context, adUnitId, new UnityBannerSize(320, 50));
//        ///UnityBanners.setBannerPosition(BannerPosition.TOP_LEFT);
//        // Set the listener for banner lifecycle events:
//        bottomBanner.setListener(new BannerView.IListener() {
//            @Override
//            public void onBannerLoaded(BannerView bannerAdView) {
//                // Called when the banner is loaded.
//                adContainer.removeAllViews();
//                adContainer.addView(bannerAdView);
//                Log.v("UnityAdsExample", "onBannerLoaded: " + bannerAdView.getPlacementId());
//                // Enable the correct button to hide the ad
//                //(bannerAdView.getPlacementId().equals("topBanner") ? hideTopBannerButton : hideBottomBannerButton).setEnabled(true);
//            }
//
//            @Override
//            public void onBannerClick(BannerView bannerAdView) {
//                // Called when a banner is clicked.
//                Log.v("UnityAdsExample", "onBannerClick: " + bannerAdView.getPlacementId());
//            }
//
//            @Override
//            public void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo) {
//                try{
//                    bannerView.load();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                Log.v("UnityAdsBanExample", "onBannerClick: " + bannerErrorInfo.errorMessage);
//            }
//
//            @Override
//            public void onBannerLeftApplication(BannerView bannerAdView) {
//                // Called when the banner links out of the application.
//                Log.v("UnityAdsExample", "onBannerLeftApplication: " + bannerAdView.getPlacementId());
//            }
//        });
//        // Associate the banner view object with the banner view:
//        bottomBanner.load();
//        adContainer.addView(bottomBanner);

    }

    public static void showUnityIntersRews(Context context, String adUnitId){

//        UnityAds.initialize(context.getApplicationContext(), new PrefManager(context).getString("k_is_ad_unity_id"), new IUnityAdsInitializationListener() {
//            @Override
//            public void onInitializationComplete() {
//
//                UnityAds.load(adUnitId, new IUnityAdsLoadListener() {
//                    @Override
//                    public void onUnityAdsAdLoaded(String s) {
//                        UnityAds.show((Activity) context, adUnitId, new UnityAdsShowOptions(), new IUnityAdsShowListener() {
//                            @Override
//                            public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
//                                Log.e("UnityAdsExample", "Unity Ads failed to show ad for " + placementId + " with error: [" + error + "] " + message);
//
//                            }
//
//                            @Override
//                            public void onUnityAdsShowStart(String placementId) {
//                                Log.v("UnityAdsExample", "onUnityAdsShowStart: " + placementId);
//                            }
//
//                            @Override
//                            public void onUnityAdsShowClick(String placementId) {
//                                Log.v("UnityAdsExample", "onUnityAdsShowClick: " + placementId);
//                            }
//
//                            @Override
//                            public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
//                                Log.v("UnityAdsExample", "onUnityAdsShowComplete: " + placementId);
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onUnityAdsFailedToLoad(String s, UnityAds.UnityAdsLoadError unityAdsLoadError, String s1) {
//
//                    }
//                });
//
//            }
//
//            @Override
//            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {
//
//            }
//        });

    }


}
