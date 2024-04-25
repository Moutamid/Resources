package com.moutamid.sprachelernen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.PurchaseInfo;
import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.android.material.card.MaterialCardView;
import com.moutamid.sprachelernen.activities.WelcomeActivity;
import com.moutamid.sprachelernen.databinding.ActivitySubscriptionBinding;
import com.moutamid.sprachelernen.models.PaymentModel;
import com.moutamid.sprachelernen.models.UserModel;
import com.moutamid.sprachelernen.utilis.BaseSecureActivity;
import com.moutamid.sprachelernen.utilis.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class SubscriptionActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {

    ActivitySubscriptionBinding binding;

    String selectedPlan = Constants.VIP_YEAR;
    BillingProcessor bp;

    public static final String VIP_6_MONTH = "vip.six.month.com.moutamid.sprachelernen";
    public static final String VIP_3_MONTH = "vip.three.month.com.moutamid.sprachelernen";
    public static final String VIP_YEAR = "vip.year.com.moutamid.sprachelernen";

    public static final String LICENSE_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubscriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bp = BillingProcessor.newBillingProcessor(this, LICENSE_KEY, this);
        bp.initialize();

        binding.annual.setOnClickListener(v -> {
            selectedPlan = VIP_YEAR;
            updateView(binding.annual);
        });

        binding.half.setOnClickListener(v -> {
            selectedPlan = VIP_6_MONTH;
            updateView(binding.half);
        });

        binding.month.setOnClickListener(v -> {
            selectedPlan = VIP_3_MONTH;
            updateView(binding.month);
        });

        binding.start.setOnClickListener(v -> {
            bp.subscribe(SubscriptionActivity.this, selectedPlan);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable PurchaseInfo details) {
        Toast.makeText(this, "Thank you for being our valuable user", Toast.LENGTH_SHORT).show();
        onBackPressed();
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