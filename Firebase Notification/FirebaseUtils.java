package com.moutamid.webviewdemo;


import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    public static void storeTokenToDatabase(String token) {
        String uid = FirebaseAuth.getInstance().getCurrentUser() != null ?
                FirebaseAuth.getInstance().getCurrentUser().getUid() : "test-user";

        FirebaseDatabase.getInstance().getReference()
                .child("USER")
                .child(uid)
                .child("fcmToken")
                .setValue(token)
                .addOnSuccessListener(aVoid -> Log.d("FirebaseUtils", "Token saved"))
                .addOnFailureListener(e -> Log.e("FirebaseUtils", "Failed to save token", e));
    }
}
