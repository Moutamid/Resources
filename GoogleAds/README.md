README file specifically for integrating **Google AdMob** ads into your Android project:

# Google AdMob Ads Integration Guide

## Step 1: Add Dependencies

To integrate Google AdMob ads, add the following dependencies in your `build.gradle` (Module: app) file:

dependencies {
    // Google AdMob SDK
    implementation 'com.google.android.gms:play-services-ads:21.4.0'
}


## Step 2: Update AndroidManifest.xml

In your `AndroidManifest.xml`, add the following permissions and meta-data:


    <!-- Google AdMob App ID -->
    <meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="YOUR_GOOGLE_ADMOB_APP_ID" />
    
    <!-- Permissions for Ads -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />


### Replace `YOUR_GOOGLE_ADMOB_APP_ID` with your actual **AdMob App ID**.

## Step 3: Obtain Your AdMob App ID & Ad Unit IDs

1. **Go to the AdMob Console**: Visit [Google AdMob Console](https://admob.google.com/home/) and sign in with your Google account.
   
2. **Create a New App**: Follow the prompts to create a new app if you haven't already. You will be asked to provide some basic details about your app.

3. **Get Your App ID**: After creating your app, you'll be provided with an **AdMob App ID**. This ID is required for initializing AdMob in your app.

4. **Create Ad Units**: You will need to create **Ad Units** for different ad formats such as:
    - **Banner Ads**
    - **Interstitial Ads**
    - **Rewarded Video Ads**
    - **Native Ads**
   
   For each ad unit, AdMob will provide an **Ad Unit ID**. These IDs are used to load and display ads.
   
   Then copy these ids and put this **Ad Manager** class

## Step 4: Testing Ads

For testing ads, ensure you use **test ad unit IDs** during development:

- **Banner Ads**: `ca-app-pub-3940256099942544/6300978111`
- **Interstitial Ads**: `ca-app-pub-3940256099942544/1033173712`
- **Rewarded Ads**: `ca-app-pub-3940256099942544/5224354917`

