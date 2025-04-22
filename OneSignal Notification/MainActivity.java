package com.moutamid.onesignal;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OSDeviceState deviceState = OneSignal.getDeviceState();
        if (deviceState != null) {
            String playerId = deviceState.getUserId();
            Log.d("OneSignal", "Player ID: " + playerId);
        }
        OneSignalHelper.sendNotification(this, "RECEIVER_PLAYER_ID", "Notification Title", "Notification Body");

    }
}