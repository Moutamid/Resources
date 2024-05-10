package com.moutamid.antitiktok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.android.material.button.MaterialButton;
import com.moutamid.antitiktok.databinding.ActivityMainBinding;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.checkApp(this);

        binding.privacy.setOnClickListener(v -> {
if (!isServiceRunningInForeground(getApplicationContext(), ForegroundService.class)) {
                startService();
            }
});

    }
    public static boolean isServiceRunningInForeground(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                if (service.foreground) {
                    return true;
                }

            }
        }
        return false;
    }
}