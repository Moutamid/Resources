package com.moutamid.antitiktok;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Window extends ContextWrapper {

    // declaring required variables
    public static View counterView;
    public static TextView counter;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;
    private LayoutInflater layoutInflater;

    public Window(Context context) {
        super(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // set the layout parameters of the window
            mParams = new WindowManager.LayoutParams(
                    // Shrink the window to wrap the content rather
                    // than filling the screen
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    //      WindowManager.LayoutParams.TYPE_SYSTEM_ALERT |
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                            WindowManager.LayoutParams.FLAG_FULLSCREEN |
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    // through any transparent parts
                    PixelFormat.TRANSLUCENT);
        } else {
            mParams = new WindowManager.LayoutParams(
                    // Shrink the window to wrap the content rather
                    // than filling the screen
                    WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_SYSTEM_ALERT |
                            WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                            WindowManager.LayoutParams.FLAG_FULLSCREEN |
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    // through any transparent parts
                    PixelFormat.TRANSLUCENT);
        }

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflating the view with the custom layout we created
        counterView = layoutInflater.inflate(R.layout.counter, null);

        counter = counterView.findViewById(R.id.counter);

        mParams.gravity = Gravity.TOP | Gravity.START;
        mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);

    }


    public void open() {

        try {
            // check if the view is already
            // inflated or present in the window

            if (counterView.getWindowToken() == null) {
                if (counterView.getParent() == null) {
                    mWindowManager.addView(counterView, mParams);
                }
            }
        } catch (Exception e) {
            Log.d("Error1", e.toString());
        }

    }

    public void close() {

        try {
            // remove the view from the window
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(counterView);
            counterView.invalidate();
            stopService(new Intent(this, ForegroundService.class));
            // the above steps are necessary when you are adding and removing
            // the view simultaneously, it might give some exceptions
        } catch (Exception e) {
            Log.d("Error2", e.toString());
        }
    }

}
