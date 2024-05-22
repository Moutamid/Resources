package com.example.spinningwheel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.adefruandta.spinningwheel.SpinningWheelView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpinningWheelView wheelView = (SpinningWheelView) findViewById(R.id.wheel);
        // Can be array string or list of object
        wheelView.setItems(R.array.dummy);

        // Set listener for rotation event
        wheelView.setOnRotationListener(new SpinningWheelView.OnRotationListener<String>() {
            // Call once when start rotation
            @Override
            public void onRotation() {
                Log.d("XXXX", "On Rotation");
            }

            // Call once when stop rotation
            @Override
            public void onStopRotation(String item) {
                Log.d("XXXX", "On Rotation");
            }
        });

        // If true: user can rotate by touch
        // If false: user can not rotate by touch
        wheelView.setEnabled(true);

    }

}