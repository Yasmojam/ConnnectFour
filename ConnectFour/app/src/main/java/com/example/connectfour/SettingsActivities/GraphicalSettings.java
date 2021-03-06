package com.example.connectfour.SettingsActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.connectfour.R;
import com.example.connectfour.StartScreenActivity;

public class GraphicalSettings extends AppCompatActivity {

    private Switch darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StartScreenActivity s = new StartScreenActivity();
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);;
        }
        else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphical_settings);
        darkMode = findViewById(R.id.darkModeSwitch);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            darkMode.setChecked(true);
        }
        darkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });

        goBack();
    }
    public void restartApp() {
        Intent intent = new Intent(getApplicationContext(), GraphicalSettings.class);
        startActivity(intent);
        finish();
    }

    /**
     * Takes player back to the main Settings menu when clicked
     */
    public void goBack () {
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(GraphicalSettings.this, SettingsActivity.class);
                startActivity(goBack);
            }
        });
    }

}

