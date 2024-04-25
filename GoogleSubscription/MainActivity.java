package com.moutamid.sprachelernen;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.PurchaseInfo;
import com.anjlab.android.iab.v3.SkuDetails;
import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.moutamid.sprachelernen.activities.LanguageSelectionActivity;
import com.moutamid.sprachelernen.activities.ProfileEditActivity;
import com.moutamid.sprachelernen.activities.SettingsActivity;
import com.moutamid.sprachelernen.databinding.ActivityMainBinding;
import com.moutamid.sprachelernen.fragments.HomeFragment;
import com.moutamid.sprachelernen.models.UserModel;
import com.moutamid.sprachelernen.utilis.BaseSecureActivity;
import com.moutamid.sprachelernen.utilis.Constants;
import com.moutamid.sprachelernen.utilis.FirebaseDataSenderReceiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BillingProcessor.IBillingHandler {
    ActivityMainBinding binding;
    BillingProcessor bp;
    public static final String VIP_6_MONTH = "vip.six.month.com.moutamid.sprachelernen";
    public static final String VIP_3_MONTH = "vip.three.month.com.moutamid.sprachelernen";
    public static final String VIP_YEAR = "vip.year.com.moutamid.sprachelernen";

    public static final String LICENSE_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        bp = BillingProcessor.newBillingProcessor(this, LICENSE_KEY, this);
        bp.initialize();

        checkSubscription();

    }


    private void checkSubscription() {

        ArrayList<String> ids = new ArrayList<>();
        ids.add(VIP_YEAR);
        ids.add(VIP_6_MONTH);
        ids.add(VIP_3_MONTH);
        bp.getSubscriptionsListingDetailsAsync(ids, new BillingProcessor.ISkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(@Nullable List<SkuDetails> products) {
                Log.d("PURSS", "Size : " + products.size());
                for (int i = 0; i < products.size(); i++) {
                    boolean isSub = products.get(i).isSubscription;
                    // if isSub is true that mean user is subsribed and if false user cancel the subscription or didn't buy the subsription
                }
            }

            @Override
            public void onSkuDetailsError(String error) {

            }
        });

        // Alternate way
        boolean isSubcribe = bp.isSubscribed(VIP_3_MONTH) || bp.isSubscribed(VIP_YEAR) || bp.isSubscribed(VIP_6_MONTH);
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable PurchaseInfo details) {

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }

}