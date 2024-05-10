package com.moutamid.trip4pet.fragments;

import java.util.Objects;

public class AroundMeFragment extends Fragment {
    FragmentAroundMeBinding binding;

    public AroundMeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAroundMeBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private OnMapReadyCallback callback = googleMap -> {

// We use 20dp of width and 32 dp of height you can adjust the values based on your needs
        float density = getResources().getDisplayMetrics().density;
        int widthPx = (int) (20 * density);
        int heightPx = (int) (32 * density);
// if you want to add a icon from a vector drawable use this line
 googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromBitmap(Constants.convertVectorToBitmap(requireContext(), icon, widthPx, heightPx)))
                        .position(latLng).title(model.name)).setTag(model.id);

// If you want to show a custom marker by creating a custom layout use this line of code
View marker = getLayoutInflater().inflate(R.layout.custom_marker, null, false);
                ImageView iconImage = marker.findViewById(R.id.icon);
                iconImage.setImageResource(R.drawable.icon);
        googleMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromBitmap(Constants.createDrawableFromView(requireContext(), marker)))
                        .position(latLng).title(model.name)).setTag(model.id);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(30.3753, 69.3451)));

    };

}