package com.moutamid.sprachelernen.activities;

import android.Manifest;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PDFActivity extends AppCompatActivity {
    ActivityPdfBinding binding;
    String link;
    private static final String TAG = "PDFActivity";
    String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String link = "https://google.com"; // Your Pdf link
        new RetrivePDFfromUrl().execute(link);

        // if want to open from a file
        File file = new File("FILE_PATH");
        displayFromFile(file);

        // If you want to open pdf from assets
        String path =  getFileFromAssets(this, "fileName.extension").getAbsolutePath();
        displayFromAssets("ASSETS_FILE_PATH");
    }

    public File getFileFromAssets(Context context, String fileName) throws IOException {
        File file = new File(context.getCacheDir(), fileName);

        if (!file.exists()) {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                inputStream = context.getAssets().open(fileName);
                outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return file;
    }

    private void displayFromAssets(String file) {
        binding.pdf.fromAsset(file)
                .defaultPage(0)
                .onPageChange((page, pageCount) -> {
                })
                .enableAnnotationRendering(true)
                .onLoad(nbPages ->)
                .onError(t -> {
                    Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
                })
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10)
                .load();
    }

    private void displayFromFile(File file) {
        binding.pdf.fromFile(file)
                .defaultPage(0)
                .onPageChange((page, pageCount) -> {
                })
                .enableAnnotationRendering(true)
                .onLoad(nbPages ->)
                .onError(t -> {
                    Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
                })
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10)
                .load();
    }


    private void displayFromUrl(InputStream url) {
        binding.pdf.fromStream(url)
                .defaultPage(0)
                .onPageChange((page, pageCount) -> {
                })
                .enableAnnotationRendering(true)
                .onLoad(nbPages ->)
                .onError(t -> {
                    ;
                    Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
                })
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10)
                .load();
    }

    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    ;
                    Toast.makeText(PDFActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            displayFromUrl(inputStream);
        }
    }

}