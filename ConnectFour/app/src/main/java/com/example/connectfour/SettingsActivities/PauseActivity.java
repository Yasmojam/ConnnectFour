package com.example.connectfour.SettingsActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.connectfour.Board.VsPlayerGameBoard;
import com.example.connectfour.R;
import com.example.connectfour.StartScreenActivity;

public class PauseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        }
        else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        ImageButton sound = (ImageButton) findViewById(R.id.sound);

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PauseActivity.this, "Sound Off", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnHome = new Intent(PauseActivity.this, StartScreenActivity.class);
                startActivity(returnHome);
            }
        });

        ImageButton options = findViewById(R.id.settings);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changeSettings = new Intent(PauseActivity.this, SettingsActivity.class);
                startActivity(changeSettings);
            }
        });

        Button resume = findViewById(R.id.resume);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToGame = new Intent(PauseActivity.this, VsPlayerGameBoard.class);
                startActivity(returnToGame);
            }
        });
    }
}
