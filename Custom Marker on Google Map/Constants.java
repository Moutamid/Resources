package com.moutamid.trip4pet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class Constants {

    public static Bitmap convertVectorToBitmap(Context context, int vectorDrawableId, int width, int height) {
        // Get the VectorDrawable from the resources
        Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
        if (vectorDrawable instanceof VectorDrawable) {
            // Create a Bitmap with the desired width and height
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            // Create a Canvas to draw the vector onto the Bitmap
            Canvas canvas = new Canvas(bitmap);
            // Set the bounds for the vector drawable
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            // Draw the vector onto the Canvas
            vectorDrawable.draw(canvas);
            return bitmap;
        } else {
            // Return null if the drawable is not a VectorDrawable
            return null;
        }
    }
   
    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
