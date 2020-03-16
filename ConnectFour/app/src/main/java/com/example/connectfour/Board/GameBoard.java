package com.example.connectfour.Board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.connectfour.R;

import java.util.ArrayList;

public class GameBoard extends AppCompatActivity {
    ArrayList<ImageView> drawnCounters = new ArrayList<ImageView>(); //list of drawn counters
    ArrayList<View> listView =  new ArrayList<View>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        }
        else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridView = (GridView) findViewById(R.id.grid_view);
        final ImageAdapter imageAdapter = new ImageAdapter(this); //Instance of ImageAdapter Class


        gridView.setAdapter(imageAdapter);

        /*
            setOnItemClickListener (AdapterView.OnItemClickListener listener)
                Register a callback to be invoked when an item
                in this AdapterView has been clicked.
                Parameters
                listener : The callback that will be invoked.
         */
        // Set an item click listener for GridView widget
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (imageAdapter.getNumCounters()< imageAdapter.getMaxCounters()) {
                    imageAdapter.setNumCounters(imageAdapter.getNumCounters() +1);

                    listView.add(view);
                    imageAdapter.setView(view);

                    imageAdapter.setSelectedPosition(position);
                    imageAdapter.addSelectedPositions(position);
                    imageAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}
