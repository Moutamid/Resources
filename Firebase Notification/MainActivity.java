package com.moutamid.webviewdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        signInAnonymously();

        EditText senderIdEt = findViewById(R.id.senderIDEt);
        EditText messageEt = findViewById(R.id.messageEt);

        Button sendBtn = findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(view -> {
            FCMNotificationHelper fcmNotificationHelper = new FCMNotificationHelper(this);
            fcmNotificationHelper.sendNotification(
                    senderIdEt.getText().toString(),
                    "Testing title",
                    "" + messageEt.getText().toString());
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if ((ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)) {
                shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS);
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

    }

    private void signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnSuccessListener(authResult -> {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        saveFCMToken(user.getUid());
                    }
                })
                .addOnFailureListener(e -> Log.e("TAG", "Sign-in failed", e));
    }

    private void saveFCMToken(String userId) {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String token = task.getResult();
                        FirebaseDatabase.getInstance().getReference("USER")
                                .child(userId)
                                .child("fcmToken")
                                .setValue(token);

                        FirebaseDatabase.getInstance().getReference("USER")
                                .child(userId)
                                .child("ID")
                                .setValue(userId);

                        TextView tv = findViewById(R.id.idTv);
                        tv.setText("MY ID: " + userId);
                    }
                });
    }

}