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
    private static final String TAG = "ReadService";

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Toast.makeText(getApplicationContext(), "Connected!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getSource() == null) {
            return;
        }

        traverseViewHierarchy(event.getSource());


        /** This below code also read text text but only focused or clicked view text such as buttons  */

//        StringBuilder textBuilder = new StringBuilder();
//        for (CharSequence charSequence : event.getText()) {
//            textBuilder.append(charSequence);
//        }
//        String text = textBuilder.toString();
//
//        // Log the extracted text
//        Log.d(TAG, "Extracted Text: " + text);
    }

    private void traverseViewHierarchy(AccessibilityNodeInfo node) {
        if (node == null) {
            return;
        }

        if (node.getText() != null && !node.getText().toString().isEmpty()) {
            Log.d(TAG, "Extracted Text: " + node.getText());
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            traverseViewHierarchy(node.getChild(i));
        }
    }

    @Override
    public void onInterrupt() {

    }
}
