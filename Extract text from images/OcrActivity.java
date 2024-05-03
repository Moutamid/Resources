package com.moutamid.readnumberplates;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.fxn.stash.Stash;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.moutamid.readnumberplates.databinding.ActivityOcrBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OcrActivity extends AppCompatActivity {
    ActivityOcrBinding binding;
    Uri image_uri;
    private static final String TAG = "OcrActivity";
    TextRecognizer recognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOcrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pick.setOnClickListener(v -> {
            // pick a image from gallery or camera
        });

        recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);


        /** When using Chinese script library */
//        TextRecognizer recognizer =
//                TextRecognition.getClient(new ChineseTextRecognizerOptions.Builder().build());

        /** When using Devanagari script library */
//        TextRecognizer recognizer =
//                TextRecognition.getClient(new DevanagariTextRecognizerOptions.Builder().build());

        /** When using Japanese script library */
//        TextRecognizer recognizer =
//                TextRecognition.getClient(new JapaneseTextRecognizerOptions.Builder().build());

        /** When using Korean script library */
//        TextRecognizer recognizer =
//                TextRecognition.getClient(new KoreanTextRecognizerOptions.Builder().build());
    }

    File file;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            image_uri = data.getData(); //get image uri
            //set image to image view
            file = new File(image_uri.getPath());
            Log.d(TAG, "name: " + file.getName());
            Log.d(TAG, "path: " + file.getPath());
            InputImage image;
            try {
                image = InputImage.fromFilePath(OcrActivity.this, image_uri);
                Task<Text> result =
                        recognizer.process(image)
                                .addOnSuccessListener(new OnSuccessListener<Text>() {
                                    @Override
                                    public void onSuccess(Text visionText) {
                                        // Task completed successfully
                                        String resultText = visionText.getText();
                                        Log.d(TAG, "onSuccess: " + resultText);
                                    }
                                })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Task failed with an exception
                                                Toast.makeText(OcrActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}