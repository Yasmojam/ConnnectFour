package com.example.connectfour.SettingsActivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.connectfour.R;
import com.example.connectfour.StartScreenActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /**
         * Takes player to graphics settings when clicked
         */
        Button graph = findViewById(R.id.graphicsSettingsButton);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent graphOptions = new Intent(SettingsActivity.this, GraphicalSettings.class);
                startActivity(graphOptions);
            }
        });

        /**
         * Takes player to the volume settings when clicked
         */
        Button vol = findViewById(R.id.volumeSettings);
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volOptions = new Intent(SettingsActivity.this, VolumeSettingsActivity.class);
                startActivity(volOptions);
            }
        });

        /**
         * Takes player back to the start screen when 'Back' button clicked
         */
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(SettingsActivity.this, StartScreenActivity.class);
                startActivity(goBack);
            }
        });

    }



}