package com.moutamid.sprachelernen.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.database.DataSnapshot;
import com.moutamid.sprachelernen.utilis.BaseSecureActivity;
import com.moutamid.sprachelernen.utilis.Constants;
import com.moutamid.sprachelernen.R;
import com.moutamid.sprachelernen.adapters.ChipsAdapter;
import com.moutamid.sprachelernen.adapters.OptionsAdapter;
import com.moutamid.sprachelernen.databinding.ActivityExerciseQuestionBinding;
import com.moutamid.sprachelernen.listeners.ChipsClick;
import com.moutamid.sprachelernen.models.ExerciseModel;
import com.moutamid.sprachelernen.utilis.Features;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PlayAudioActivity extends BaseSecureActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);

        /// This button is used for Play/Pause audio
        binding.play.setOnClickListener(v -> {
            if (mediaPlayer == null) {
                String audioUrl = "AUDIO_LINK";
                releaseMediaPlayer();
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(audioUrl);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(mp -> {
                        mediaPlayer.start();
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer = null;
                        }
                    });
                } catch (IOException | IllegalStateException e) {
                    e.printStackTrace();
                }
            } else {
                if (mediaPlayer.isPlaying()) {
                    releaseMediaPlayer();
                }
            }
        });
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }
}