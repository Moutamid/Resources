package com.moutamid.sampleproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    String[] permissions13 = new String[]{
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (above13Check()) {
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES);
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_VIDEO);
                ActivityCompat.requestPermissions(MainActivity.this, permissions13, 2);
            } else {
                if (isExternalStorageWritable()) {
                    File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File newFolder = new File(downloadsDir, "Dummy Folder");
                    if (!newFolder.exists()) {
                        boolean success = newFolder.mkdir();
                        if (success) {
                            Log.d("MainActivity", "Folder created successfully");
                            saveDataToCSV();
                        } else {
                            Log.e("MainActivity", "Failed to create folder");
                        }
                    } else {
                        Log.d("MainActivity", "Folder already exists");
                    }
                } else {
                    Log.e("MainActivity", "External storage is not writable");
                }            }
        } else {
            if (below13Check()) {
                shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
                ActivityCompat.requestPermissions(MainActivity.this, permissions, 2);
            } else {
                if (isExternalStorageWritable()) {
                    File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File newFolder = new File(downloadsDir, "Dummy Folder");
                    if (!newFolder.exists()) {
                        boolean success = newFolder.mkdir();
                        if (success) {
                            Log.d("MainActivity", "Folder created successfully");
                            saveDataToCSV();
                        } else {
                            Log.e("MainActivity", "Failed to create folder");
                        }
                    } else {
                        Log.d("MainActivity", "Folder already exists");
                    }
                } else {
                    Log.e("MainActivity", "External storage is not writable");
                }            }
        }


    }

    private void saveDataToCSV() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault()).format(new Date());
        String filename = "dummy_file"+timestamp+".csv"; // Append timestamp to the file name
        String title = "\nDummy Title\n\n";
        String csvHeader = "Dummy header name\n";
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File newFolder = new File(downloadsDir, "Dummy Folder");
        File csvFile = new File(newFolder, filename);
        try {
            FileWriter writer = new FileWriter(csvFile, true); // Open the file in append mode
            writer.append(title);
            writer.append(csvHeader);
            for (int i = 0; i < 5; i++) {
                 writer.append(String.valueOf(i )).append(",\"")
                             .append("\n");
            }

             writer.flush();
            writer.close();
        } catch (IOException e) {
            Log.e("CSV", "Error writing CSV file: " + e.getMessage());
        }
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private boolean above13Check() {
        return ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_MEDIA_VIDEO) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    private boolean below13Check() {
        return ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }
}