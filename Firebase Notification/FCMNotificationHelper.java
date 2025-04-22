package com.moutamid.webviewdemo;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FCMNotificationHelper {

    private static final String TAG = "FCMNotificationHelper";
    private static final String FCM_API_URL = "https://fcm.googleapis.com/v1/projects/chatty-40368/messages:send";

    private final Context context;

    public FCMNotificationHelper(Context context) {
        this.context = context;
    }

    public void sendNotification(String receiverUserId, String title, String body) {
        new Thread(() -> {
            try {
                TokenManager tokenManager = new TokenManager(context);
                String accessToken = tokenManager.getAccessToken();

                if (accessToken == null) {
                    Log.e(TAG, "Access token is null");
                    return;
                }

                FirebaseDatabase.getInstance().getReference("USER")
                        .child(receiverUserId)
                        .child("fcmToken")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                String token = snapshot.getValue(String.class);
                                if (token != null) {
                                    sendFCM(token, accessToken, title, body);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                Log.e(TAG, "Error getting token", error.toException());
                            }
                        });

            } catch (Exception e) {
                Log.e(TAG, "Error", e);
            }
        }).start();
    }

    private void sendFCM(String token, String accessToken, String title, String body) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject message = new JSONObject();
        JSONObject notification = new JSONObject();
        JSONObject bodyJson = new JSONObject();

        try {
            notification.put("title", title);
            notification.put("body", body);

            bodyJson.put("token", token);
            bodyJson.put("notification", notification);

            message.put("message", bodyJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, FCM_API_URL, message,
                response -> {
                    Log.d(TAG, "Notification sent: " + response);
                    Toast.makeText(context, "Sent", Toast.LENGTH_SHORT).show();
                },
                error -> Log.e(TAG, "Error sending notification", error)) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer " + accessToken);
                map.put("Content-Type", "application/json");
                return map;
            }
        };

        queue.add(request);
    }
}
