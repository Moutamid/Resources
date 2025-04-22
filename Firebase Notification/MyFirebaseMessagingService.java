package com.moutamid.webviewdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String TAG = "FCMService";

        @Override
        public void onNewToken(String token) {
            super.onNewToken(token);
            Log.d(TAG, "FCM token: " + token);

            // Store token to Firebase
            FirebaseUtils.storeTokenToDatabase(token);
        }

        @Override
        public void onMessageReceived(RemoteMessage message) {
            super.onMessageReceived(message);
            Log.d(TAG, "Message received: " + message.getData());

            String title = message.getNotification() != null ? message.getNotification().getTitle() : "New Message";
            String body = message.getNotification() != null ? message.getNotification().getBody() : "You have a new message";

            showNotification(title, body);
        }

        private void showNotification(String title, String message) {
            String channelId = "FCM_CHANNEL";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, "FCM Channel", NotificationManager.IMPORTANCE_HIGH);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManagerCompat.from(this).notify(1, builder.build());
        }
    }


