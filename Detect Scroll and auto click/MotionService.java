package com.moutamid.antitiktok;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.accessibilityservice.GestureDescription.StrokeDescription;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.fxn.stash.Stash;

import java.text.DecimalFormat;
import java.util.Locale;

public class MotionService extends AccessibilityService {
    private boolean canScroll = true;
    private boolean tikTokInForeground = false;
    public static final String TIKTOK = "com.zhiliaoapp.musically";

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Toast.makeText(getApplicationContext(), "Connected!", Toast.LENGTH_SHORT).show();
    }

    boolean continueBool = true;
    boolean homeScroll = true;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getSource() == null) {
            return;
        }
        // check if app is opened or not and is the screen is scrollable
        if (String.valueOf(event.getPackageName()).equals(TIKTOK) && event.getEventType() == AccessibilityEvent.TYPE_VIEW_SCROLLED && continueBool && homeScroll) {
            Log.d(TAG, "onAccessibilityEvent: Started");
            continueBool = false;
            if (Stash.getBoolean("sensory", true)) {
                new Handler().postDelayed(() -> clickWindow(), 1500);
            }
            new Handler().postDelayed(() -> continueBool = true, 1000);
        } else if (!String.valueOf(event.getPackageName()).equals(TIKTOK)) { // check if app is closed or not
            // app is closed
        }
    }

    private void clickWindow() {
        Point position = new Point();
        position.x = getResources().getDisplayMetrics().widthPixels / 2;
        position.y = getResources().getDisplayMetrics().heightPixels / 2;
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(position.x, position.y);
        builder.addStroke(new StrokeDescription(path, 50L, 1L));
        GestureDescription gesture = builder.build();
        boolean dispatched = dispatchGesture(gesture, new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                Log.d("Gesture", "Completed");
            }
        }, null);
    }

    @Override
    public void onInterrupt() {

    }
}
