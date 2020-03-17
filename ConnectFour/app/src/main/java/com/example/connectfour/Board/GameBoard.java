package com.example.connectfour.Board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import android.os.Bundle;
import android.widget.GridView;

import com.example.connectfour.R;

import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard extends AppCompatActivity {

    final int boardSize = 47; //7x6
    int[] image;

    GridView gridView;
    ArrayList<ImageModel> imageModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DARK MODE
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        //initialise board to be chosen size and fill with board images
        image = new int[boardSize];
        Arrays.fill(image, R.drawable.board);

        gridView = (GridView) findViewById(R.id.grid_view);

        imageModels = new ArrayList<ImageModel>();

        for (int i = 0; i < image.length; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setmThumbIds(image[i]);
            //add to arraylist of imageModels
            imageModels.add(imageModel);
        }

        ImageAdapter adapter = new ImageAdapter(getApplicationContext(), imageModels);
        gridView.setAdapter(adapter);

    }

}
