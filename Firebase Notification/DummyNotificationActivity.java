package com.moutamid.misshelper.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.moutamid.misshelper.R;
import com.moutamid.misshelper.databinding.ActivityDummyNotificationBinding;
import com.moutamid.misshelper.utils.FirebaseHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DummyNotificationActivity extends AppCompatActivity {

    ActivityDummyNotificationBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDummyNotificationBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(token -> {
                    String uid;
                    uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    FirebaseDatabase.getInstance().getReference("fcmTokens")
                            .child(uid).setValue(token);

                    b.myIdTextview.setText(uid + "\n\n" + token);
                });

        b.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toUid = b.uidEt.getText().toString().trim();
                String title = b.textEt.getText().toString().trim();
                if (toUid.isEmpty()) {
                    Toast.makeText(DummyNotificationActivity.this, "Enter recipient UID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (title.isEmpty()) {
                    Toast.makeText(DummyNotificationActivity.this, "Enter title", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth.getInstance().getCurrentUser().getIdToken(true)
                        .addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                            @Override
                            public void onSuccess(GetTokenResult getTokenResult) {

                                JSONObject body = new JSONObject();
                                try {
                                    body.put("toUid", toUid);
                                    body.put("title", title);
                                    body.put("message", "Device A says hi");
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }


                                Request req = new Request.Builder()
                                        .url("https://nextjs-boilerplate-five-black-73.vercel.app/api/sendNotification")
                                        .addHeader("Authorization", "Bearer " + getTokenResult.getToken())
                                        .addHeader("Content-Type", "application/json")
                                        .post(RequestBody.create(body.toString(), MediaType.get("application/json")))
                                        .build();

                                new OkHttpClient().newCall(req).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        e.printStackTrace();
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        Log.i("push", "result: " + response.code() + " body: " + response.body().string());
                                    }
                                });

                            }
                        });

            }
        });

    }
}