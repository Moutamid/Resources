package com.moutamid.trip4pet.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moutamid.trip4pet.Constants;
import com.moutamid.trip4pet.R;
import com.moutamid.trip4pet.databinding.ActivityAddPlaceBinding;

public class AddPlaceActivity extends AppCompatActivity {
    ActivityAddPlaceBinding binding;
    private FusedLocationProviderClient fusedLocationClient;
    GoogleMap mMap;
    private static final String TAG = "AddPlaceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddPlaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    private OnMapReadyCallback callback = googleMap -> {
        this.mMap = googleMap;
/** getting user current location requires to enable the GPS so ask that first */
// For getting the user current location and move the map to that location. 
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f));
                    } else {
                        // Location not found, handle the case where there is no last known location
                        Toast.makeText(this, "Location not found!", Toast.LENGTH_SHORT).show();
                    }
                });

        // Adding a marker on the map
        LatLng parkolo = new LatLng(47.521024288430404, 21.62947502487398);
        LatLng egyetem = new LatLng(47.55174025908643, 21.62166138069917);
        LatLng kossuth = new LatLng(47.531415874646726, 21.624710724874703);

        // Default Marker icon
        googleMap.addMarker(new MarkerOptions()
                .position(parkolo).title("Marker Title"));

        // Adding a custom marker icon from vector/svg/xml
        float density = getResources().getDisplayMetrics().density;
        int widthPx = (int) (20 * density);
        int heightPx = (int) (32 * density);
        googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(convertVectorToBitmap(requireContext(), R.drawable.resturant_mark, widthPx, heightPx2)))
                .position(kossuth).title("kossuth ter tram station Debrecen"));

        // Adding a custom marker icon as png
        googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.beach_mark))
                .position(egyetem).title("Title"));


//       mMap.setMaxZoomPreference(20f);
//        googleMap.setMinZoomPreference(12f);
        mMap.setOnCameraIdleListener(() -> {
            LatLng centerOfMap = mMap.getCameraPosition().target;
            double latitude = centerOfMap.latitude;
            double longitude = centerOfMap.longitude;
            // Use latitude and longitude as needed
            Log.d(TAG, "lat & long:  " + latitude + "  " + longitude);
        });
    };

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
}