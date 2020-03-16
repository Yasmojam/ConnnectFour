package com.example.connectfour.SettingsActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.connectfour.R;

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

        ImageButton sound = (ImageButton) findViewById(R.id.imageButton);

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PauseActivity.this, "Sound Off", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
