package com.moutamid.daiptv.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;

public class TranslateActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    ActivityTranslateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTranslateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TranslateAPI translateAPI = new TranslateAPI(
                Language.AUTO_DETECT,    // Source Language
                Language.FRENCH,         // Target Language
                "Text To Translate");    // Query Text

        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                // Update the textview with translatedText
                Log.d(TAG, "onSuccess: " + translatedText);
            }

            @Override
            public void onFailure(String ErrorText) {
                Log.d(TAG, "onFailure: " + ErrorText);
            }
        });

    }
}