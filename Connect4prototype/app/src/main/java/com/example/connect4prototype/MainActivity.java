package com.example.connect4prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<ImageView> drawnCounters = new ArrayList<ImageView>(); //list of drawn counters
    ArrayList<View> listView =  new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final GridView gridView = (GridView) findViewById(R.id.grid_view);

       //Instance of ImageAdapter Class
       final ImageAdapter imageAdapter = new ImageAdapter(this);
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
