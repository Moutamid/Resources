package com.moutamid.tiptop.tiper_side.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.moutamid.tiptop.R;
import com.moutamid.tiptop.databinding.ActivityAddBankTipperBinding;
import com.moutamid.tiptop.models.Address;
import com.moutamid.tiptop.models.BankDetails;
import com.moutamid.tiptop.models.UserModel;
import com.moutamid.tiptop.receiver_side.activities.AddBankReceiverActivity;
import com.moutamid.tiptop.stripe.ExchangeAuthorizationCodeForAccessToken;
import com.moutamid.tiptop.utilis.Constants;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.AccountLink;

import java.util.HashMap;
import java.util.Map;

public class AddBankTipperActivity extends AppCompatActivity {
    ActivityAddBankTipperBinding binding;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBankTipperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

       
        binding.save.setOnClickListener(v -> {

		Stripe.apiKey = Constants.API_KEY;
                            Map<String, Object> params = new HashMap<>();
                            params.put("account", "Bank Account Number"); // TODO Change with user bank account number
                            params.put("refresh_url", "https://google.com/");
                            params.put(
                                    "return_url",
                                    "https://google.com/"
                            );
                            params.put("type", "account_onboarding");

                            try {
                                AccountLink accountLink = AccountLink.create(params);
                                String url = accountLink.getUrl();
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                                browserIntent.setData(Uri.parse(url));
                                startActivity(browserIntent);
                            } catch (StripeException e) {
                                e.printStackTrace();
                            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getData() != null) {
            Uri uri = intent.getData();
            if (uri.getQueryParameter("code") != null) {
                // Exchange the authorization code for an access token
                String code = uri.getQueryParameter("code");
                new ExchangeAuthorizationCodeForAccessToken(code).exchangeAuthorizationCode(AddBankTipperActivity.this);
            }
        }
    }
}