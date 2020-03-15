package com.example.connectfour.SettingsActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.connectfour.R;

public class PauseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
