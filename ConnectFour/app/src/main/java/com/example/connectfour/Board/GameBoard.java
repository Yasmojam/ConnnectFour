package com.example.connectfour.Board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connectfour.R;
import com.example.connectfour.SettingsActivities.PauseActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard extends AppCompatActivity {

    //TO DO:
    // THESE WILL BE INIT AS NULL AND SET ONCE INPUT BETWEEN NEW GAME SCREEN IS ESTABLISHED!!
    //Update the player turn view
    int player1Col = Color.RED;
    int player2Col = Color.YELLOW;
    int columns = 7;
    int rows = 6;


    final int boardSize = getColumns()*getRows();

    int[] image;

    GridView gridView;
    ArrayList<ImageModel> imageModels;
    ArrayList<Integer> filledSlot;


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

        MediaPlayer media = MediaPlayer.create(GameBoard.this, R.raw.lol);
        media.start();

        filledSlot = new ArrayList<Integer>();

        //initialise board to be chosen size and fill with board images
        image = new int[boardSize];
        Arrays.fill(image, R.drawable.board);

        TextView playerTurnText = (TextView) findViewById(R.id.playerTurn);
        playerTurnText.setText("Player 1 Turn!");
        
        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setNumColumns(columns);

        imageModels = new ArrayList<ImageModel>();

        for (int i = 0; i < image.length; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setmThumbIds(image[i]);
            //add to array list of imageModels
            imageModels.add(imageModel);
        }

        ImageAdapter adapter = new ImageAdapter(getApplicationContext(), imageModels);
        gridView.setAdapter(adapter);

        //item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Tracks whose turn it is
            int turnNo = 0;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if turn number is even player one colour
                if (turnNo%2 == 0) {
                    playerTurnText.setText("Player 1 Turn!");
                    adapter.setBackgroundOfImageView(position, getPlayer1Col());
                }
                else{
                    playerTurnText.setText("Player 2 Turn!");
                    adapter.setBackgroundOfImageView(position, getGetPlayer2Col());
                }
                adapter.notifyDataSetChanged();
                turnNo++;
            }
        });

        Button pause = findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameBoard.this, PauseActivity.class);
                startActivity(intent);
            }
        });

    }

    public int getPlayer1Col() {
        return player1Col;
    }

    public void setPlayer1Col(int player1Col) {
        this.player1Col = player1Col;
    }

    public int getGetPlayer2Col() {
        return player2Col;
    }

    public void setGetPlayer2Col(int player2Col) {
        this.player2Col = player2Col;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


}
