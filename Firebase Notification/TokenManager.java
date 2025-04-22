package com.moutamid.webviewdemo;


import android.content.Context;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.InputStream;
import java.util.Collections;

public class TokenManager {
    private final Context context;

    public TokenManager(Context context) {
        this.context = context;
    }

    public String getAccessToken() {
        try {
            InputStream serviceAccount = context.getAssets().open("service_account.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount)
                    .createScoped(Collections.singletonList("https://www.googleapis.com/auth/firebase.messaging"));
            credentials.refreshIfExpired();
            return credentials.getAccessToken().getTokenValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
