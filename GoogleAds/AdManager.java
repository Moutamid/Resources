package com.example.facebookads;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.AdLoader;

public class AdManager {
    public static final String APP_ID = "ca-app-pub-3940256099942544~3347511713"; // Optional for test
    public static final String BANNER_ID = "ca-app-pub-3940256099942544/6300978111";
    public static final String INTERSTITIAL_ID = "ca-app-pub-3940256099942544/1033173712";
    public static final String REWARDED_ID = "ca-app-pub-3940256099942544/5224354917";
    public static final String NATIVE_ID = "ca-app-pub-3940256099942544/2247696110";

    private static InterstitialAd interstitialAd;
    private static RewardedAd rewardedAd;

    public static void initAdMob(Context context) {
        MobileAds.initialize(context, initializationStatus -> {});
    }

    public static void loadBannerAd(AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public static void loadInterstitial(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, INTERSTITIAL_ID, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(InterstitialAd ad) {
                        interstitialAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        interstitialAd = null;
                    }
                });
    }

    public static void showInterstitial(Activity activity) {
        if (interstitialAd != null) {
            interstitialAd.show(activity);
        } else {
            Toast.makeText(activity, "Interstitial not ready", Toast.LENGTH_SHORT).show();
        }
    }

    public static void loadRewarded(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(context, REWARDED_ID, adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(RewardedAd ad) {
                rewardedAd = ad;
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                rewardedAd = null;
            }
        });
    }

    public static void showRewarded(Activity activity) {
        if (rewardedAd != null) {
            rewardedAd.show(activity, rewardItem -> {
                Toast.makeText(activity, "Reward Earned: " + rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(activity, "Rewarded Ad not ready", Toast.LENGTH_SHORT).show();
        }
    }

    public static void loadNativeAd(Context context, FrameLayout container) {
        AdLoader adLoader = new AdLoader.Builder(context, NATIVE_ID)
                .forNativeAd(nativeAd -> {
                    NativeAdView adView = (NativeAdView) ((Activity) context).getLayoutInflater()
                            .inflate(R.layout.native_ad_layout, null);
                    adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
                    ((android.widget.TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
                    adView.setNativeAd(nativeAd);
                    container.removeAllViews();
                    container.addView(adView);
                })
                .withNativeAdOptions(new NativeAdOptions.Builder().build())
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }
}
