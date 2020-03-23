package com.example.connectfour.Board;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.connectfour.NewGameActivity;
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

    // TODO updated these
    private int rows;
    private int columns;
    final int boardSize = getRows()*getColumns();



    int[] image;

    GridView gridView;
    ArrayList<ImageModel> imageModels;
    ArrayList<Integer> filledSlotPositions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MediaPlayer media = MediaPlayer.create(GameBoard.this, R.raw.lol);
        //DARK MODE
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        // TODO Get rows and columns from new game activity
        Intent intent = getIntent();
        setRows(intent.getIntExtra(NewGameActivity.EXTRA_ROWS, getRows()));
        setColumns(intent.getIntExtra(NewGameActivity.EXTRA_COLUMNS, getColumns()));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        media.start();


        filledSlotPositions = new ArrayList<Integer>();

        //initialise board to be chosen size and fill with board images
        image = new int[boardSize];
        Arrays.fill(image, R.drawable.board);

        TextView playerTurnText = (TextView) findViewById(R.id.playerTurn);
        playerTurnText.setText("Player 1 Turn!");

        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setNumColumns(getColumns());

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
                    playerTurnText.setText("Player 2 Turn!");
                    adapter.setBackgroundOfImageView(position, getPlayer1Col());
                }
                else{
                    playerTurnText.setText("Player 1 Turn!");
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
                media.stop();
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

    public int getBoardSize(){
        return boardSize;
    }
}
