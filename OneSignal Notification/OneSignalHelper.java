package com.moutamid.onesignal;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class OneSignalHelper {

    private static final String TAG = "OneSignalHelper";
    private static final String ONESIGNAL_API_URL = "https://onesignal.com/api/v1/notifications";
    private static final String REST_API_KEY = "os_v2_app_rovg2ptfxngibaljot4s3zyvck6j43oyxqoe2qvowpgvwyyhe2zjgmjkz2jmk6zxklaucltntiojmfx6kr3psoo3ha7dxiybipnjmki";
    private static final String APP_ID = "8baa6d3e-65bb-4c80-8169-74f92de71512";

    public static void sendNotification(Context context, String playerId, String title, String message) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject json = new JSONObject();
        try {
            json.put("app_id", APP_ID);
            json.put("include_player_ids", new org.json.JSONArray().put(playerId));

            JSONObject contents = new JSONObject();
            contents.put("en", message);
            json.put("contents", contents);

            JSONObject headings = new JSONObject();
            headings.put("en", title);
            json.put("headings", headings);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, ONESIGNAL_API_URL, json,
                response -> Log.d(TAG, "Notification sent: " + response.toString()),
                error -> Log.e(TAG, "Error sending notification", error)) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Basic " + REST_API_KEY);
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };

        queue.add(request);
    }
}
