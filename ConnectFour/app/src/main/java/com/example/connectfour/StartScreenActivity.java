package com.example.connectfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.connectfour.SettingsActivities.SettingsActivity;

public class StartScreenActivity extends AppCompatActivity {

    private  MediaPlayer ring;

    public MediaPlayer getChill() {
        return ring;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ring = MediaPlayer.create(StartScreenActivity.this, R.raw.chill);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getChill().isPlaying()) {
            getChill().stop(); }
            else {
                getChill().start();
            }

        Button start = findViewById(R.id.startGameButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startgame = new Intent(StartScreenActivity.this, NewGameActivity.class);
                startActivity(startgame);
                getChill().pause();
            }
        });

        Button options = findViewById(R.id.optionsButton);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startoptions = new Intent(StartScreenActivity.this, SettingsActivity.class);
                startActivity(startoptions);
            }
        });


    }
}
