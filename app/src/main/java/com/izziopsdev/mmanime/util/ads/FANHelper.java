package com.izziopsdev.mmanime.util.ads;

//import com.facebook.ads.Ad;
//import com.facebook.ads.AdError;
//import com.facebook.ads.AdListener;
//import com.facebook.ads.AdSettings;
//import com.facebook.ads.AdSize;
//import com.facebook.ads.AdView;
//import com.facebook.ads.AudienceNetworkAds;
//import com.facebook.ads.InterstitialAd;
//import com.facebook.ads.InterstitialAdListener;
//import com.facebook.ads.RewardedVideoAd;
//import com.facebook.ads.RewardedVideoAdListener;

//import static com.facebook.ads.AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;

public class FANHelper {

//    private static AdListener adListener;
//
//    public static void showFANBan(Context context, final RelativeLayout adContainer, String fanBanId){
//        if(AudienceNetworkAds.isInitialized(context) == false) AudienceNetworkAds.initialize(context);
//
//        AdSettings.setIntegrationErrorMode(INTEGRATION_ERROR_CRASH_DEBUG_MODE);
////        AdSettings.setTestMode(true);
////        AdSettings.addTestDevice("50b55398-bcf7-4fe3-adbc-879677b0bf4b");
//
//        AdView adView = new AdView(context, fanBanId, AdSize.BANNER_HEIGHT_50);
//        adContainer.addView(adView);
//
//        adListener = new AdListener() {
//            @Override
//            public void onError(Ad ad, AdError adError) {
//                adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//// Ad clicked callback
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//// Ad impression logged callback
//            }
//        };
//
//        if(adView.isAdInvalidated() == false) adView.destroy();
//
//        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());
////        adView.loadAd();
//
//    }
//
//    public static void showFANRew(Context context, String fanRewId, String fanIntersId){
//
//        if(AudienceNetworkAds.isInitialized(context) == false) AudienceNetworkAds.initialize(context);
//
//        AdSettings.setIntegrationErrorMode(INTEGRATION_ERROR_CRASH_DEBUG_MODE);
////        AdSettings.setTestMode(true);
////        AdSettings.addTestDevice("50b55398-bcf7-4fe3-adbc-879677b0bf4b");
//
//        RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(context, fanRewId);
//        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
//            @Override
//            public void onError(Ad ad, AdError error) {
//                showFANInters(context, fanIntersId);
//                // Rewarded video ad failed to load
//                //Log.e(TAG, "Rewarded video ad failed to load: " + error.getErrorMessage());
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                rewardedVideoAd.show();
//                // Rewarded video ad is loaded and ready to be displayed
//                //Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Rewarded video ad clicked
//                //Log.d(TAG, "Rewarded video ad clicked!");
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Rewarded Video ad impression - the event will fire when the
//                // video starts playing
//                //Log.d(TAG, "Rewarded video ad impression logged!");
//            }
//
//            @Override
//            public void onRewardedVideoCompleted() {
//                // Rewarded Video View Complete - the video has been played to the end.
//                // You can use this event to initialize your reward
//                //Log.d(TAG, "Rewarded video completed!");
//                rewardedVideoAd.loadAd();
//                // Call method to give reward
//                // giveReward();
//            }
//
//            @Override
//            public void onRewardedVideoClosed() {
//                // The Rewarded Video ad was closed - this can occur during the video
//                // by closing the app, or closing the end card.
//                //Log.d(TAG, "Rewarded video ad closed!");
//            }
//        };
//        rewardedVideoAd.loadAd(
//                rewardedVideoAd.buildLoadAdConfig()
//                        .withAdListener(rewardedVideoAdListener)
//                        .build());
//    }
//
//    private static void showFANInters(Context context, String fanIntersId){
//        InterstitialAd interstitialAd = new InterstitialAd(context, fanIntersId);
//        // Create listeners for the Interstitial Ad
//        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
//            @Override
//            public void onInterstitialDisplayed(Ad ad) {
//                // Interstitial ad displayed callback
//                //Log.e(TAG, "Interstitial ad displayed.");
//                interstitialAd.loadAd();
//            }
//
//            @Override
//            public void onInterstitialDismissed(Ad ad) {
//                // Interstitial dismissed callback
//                //Log.e(TAG, "Interstitial ad dismissed.");
//            }
//
//            @Override
//            public void onError(Ad ad, AdError adError) {
//                //interstitialAd.loadAd();
//                // Ad error callback
//                //Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                // Interstitial ad is loaded and ready to be displayed
//                //Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
//                // Show the ad
//                interstitialAd.show();
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Ad clicked callback
//                //Log.d(TAG, "Interstitial ad clicked!");
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Ad impression logged callback
//                //Log.d(TAG, "Interstitial ad impression logged!");
//            }
//        };
//
//        // For auto play video ads, it's recommended to load the ad
//        // at least 30 seconds before it is shown
//        interstitialAd.loadAd(
//                interstitialAd.buildLoadAdConfig()
//                        .withAdListener(interstitialAdListener)
//                        .build());
//    }
}
