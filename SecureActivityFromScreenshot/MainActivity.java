package com.moutamid.sprachelernen;

import com.moutamid.sprachelernen.databinding.ActivityMainBinding;
import com.moutamid.sprachelernen.utilis.BaseSecureActivity;

// You just need to extend your regular Activity from BaseSecureActivity
public class MainActivity extends BaseSecureActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
     
    }
}