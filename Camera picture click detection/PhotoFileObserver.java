package com.moutamid.backgroundcamerauploader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class PhotoFileObserver extends FileObserver {
// Add URL
    private static final String ROOT_URL = "";
    private static final String CAMERA_IMAGE_BUCKET_NAME =
            Environment.getExternalStorageDirectory().toString()
                    + "/DCIM/Camera";

    private static final String CAMERA_IMAGE_BUCKET_ID =
            String.valueOf(CAMERA_IMAGE_BUCKET_NAME.toLowerCase().hashCode());

    private Context mContext;

    String type;

    public PhotoFileObserver(Context context) {
        super(CAMERA_IMAGE_BUCKET_NAME, FileObserver.CREATE);
        mContext = context;
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (event == FileObserver.CREATE && path != null) {

            String appName = "backgroundCameraUploader";
            if (path.toLowerCase().endsWith(".jpg") || path.toLowerCase().endsWith(".png")) {
                            String[] pp = path.split("-");
                            // A new photo has been taken!
                            Log.d("CameraApp", "New photo taken: " + path);
                            Log.d("CameraApp", "ll photo taken: " + pp[pp.length - 1]);
                            // Get the new image file
                            File newImageFile = new File(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DCIM) + "/Camera/" + pp[pp.length - 1]);

                            type = pp[pp.length - 1];
                            Log.d("CameraApp", "t: " + type);

                            String filePath = newImageFile.getAbsolutePath();

                            Log.d("CameraApp", "filePath: " + filePath);
}

        }
    }

}
