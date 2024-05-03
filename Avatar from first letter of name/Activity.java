package com.moutamid.readnumberplates;


public class OcrActivity extends AppCompatActivity {
    ActivityOcrBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOcrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

 Glide.with(this)
    .load("http://brokenfortest")
    .placeholder(AvatarGenerator.avatarImage(this, 200, AvatarConstants.CIRCLE, "Fooliz"))
    .into(imageView3);

// Dont use as a placeholder

 Glide.with(this)
    .load(AvatarGenerator.avatarImage(this, 200, AvatarConstants.CIRCLE, "Fooliz"))
    .into(imageView3);

// Without any Library

imageView.setImageDrawable(
    AvatarGenerator.avatarImage(
      this,
      200,
      AvatarConstants.RECTANGLE,
      "Skyways"
    );
        
    }

}