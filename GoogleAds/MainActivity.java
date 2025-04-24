package com.example.facebookads;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    private AdView adView;
    private FrameLayout nativeAdContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdManager.initAdMob(this);

        adView = findViewById(R.id.adView);
        nativeAdContainer = findViewById(R.id.nativeAdContainer);

        AdManager.loadBannerAd(adView);
        AdManager.loadInterstitial(this);
        AdManager.loadRewarded(this);
        AdManager.loadNativeAd(this, nativeAdContainer);

        findViewById(R.id.showRewarded).setOnClickListener(v -> AdManager.showRewarded(this));
        findViewById(R.id.showInterstitial).setOnClickListener(v -> AdManager.showInterstitial(this));
    }
}
