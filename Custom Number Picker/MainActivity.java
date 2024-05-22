package com.moutamid.sampleproject;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.shawnlin.numberpicker.NumberPicker;

public final class MainActivity extends AppCompatActivity {
    NumberPicker numberPicker;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberPicker = findViewById(R.id.number_picker);
        numberPicker.setDividerThickness(0);
        numberPicker.setFormatter(getString(R.string.number_picker_formatter));
        numberPicker.setFormatter(R.string.number_picker_formatter);
        numberPicker.setSelectedTextColor(ContextCompat.getColor(MainActivity.this, R.color.dark_grey));
        numberPicker.setSelectedTextSize(getResources().getDimension(R.dimen.selected_text_size));
        numberPicker.setSelectedTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        numberPicker.setSelectedTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        numberPicker.setTextColorResource(R.color.dark_grey);
        numberPicker.setTextSize(R.dimen.text_size);
        numberPicker.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        numberPicker.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        numberPicker.setMinValue(1);
        numberPicker.setValue(1);
        numberPicker.setMaxValue(5);
        numberPicker.setFadingEdgeEnabled(true);
        numberPicker.setFadingEdgeStrength(80);
        numberPicker.setScrollerEnabled(true);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setAccessibilityDescriptionEnabled(true);
        numberPicker.setSelectedTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        int value = view.getValue();
        Toast.makeText(MainActivity.this, "Current Value is "+ value, Toast.LENGTH_SHORT).show();
    }
});
    }

}
