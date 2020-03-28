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
import android.widget.TextView;

import com.example.connectfour.NewGameActivity;
import com.example.connectfour.R;
import com.example.connectfour.SettingsActivities.PauseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**Class which represents the "Vs AI" game board screen.**/
public class VsAIGameBoard extends AppCompatActivity {

    int player1Col = Color.RED;
    int player2Col = Color.YELLOW;

    private int rows;
    private int columns;
    private int boardSize;

    int[] image;

    GridView gridView;
    Random rand = new Random();
    ArrayList<ImageModel> imageModels;

    ArrayList<Integer> filledSlot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MediaPlayer media = MediaPlayer.create(VsAIGameBoard.this, R.raw.lol);
        //DARK MODE
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
            ;
        } else {
            setTheme(R.style.AppTheme);
        }

        Intent intent = getIntent();
        setRows(intent.getIntExtra(NewGameActivity.EXTRA_ROWS, 0));
        setColumns(intent.getIntExtra(NewGameActivity.EXTRA_COLUMNS, 0));
        setBoardSize(getRows(), getColumns());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_a_i_game_board);
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

        for (int i = 0; i < image.length; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setmThumbIds(image[i]);
            //add to array list of imageModels
            imageModels.add(imageModel);
        }

        ImageAdapter adapter = new ImageAdapter(getApplicationContext(), imageModels, getBoardSize());
        gridView.setAdapter(adapter);

        //item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Tracks whose turn it is
            int turnNo = 0;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if turn number is even player one colour
                if (!filledSlot.contains(position)) {
                    filledSlot.add(position);

                    System.out.println("PLAYER CHOOSES: " + position);
                    System.out.println("List of filled slots: " + filledSlot);

                    playerTurnText.setText("AI Turn!");
                    adapter.setBackgroundOfImageView(position, getPlayer1Col());


                    //AI TAKES TURN and adds position to filled slots if board has slots
                    if (filledSlot.size() < boardSize){
                        adapter.setBackgroundOfImageView(chooseAIMove(), getGetPlayer2Col());
                        playerTurnText.setText("Player 1 Turn!");
                    }
                    else{
                        System.out.println("BOARD IS FULL");
                    }


                    adapter.notifyDataSetChanged();
                }
            }
        });

        Button pause = findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VsAIGameBoard.this, PauseActivity.class);
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

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int rows, int columns) {
        boardSize = rows * columns;
    }

    public ArrayList<Integer> getFilledSlot() {
        return filledSlot;
    }

    public boolean isPosValid(int position){
        if (getFilledSlot().contains(position)){
            return false;
        }
        else {
            return true;
        }
    }

    public int chooseAIMove() {
        int chosenAIPos = rand.nextInt(getBoardSize());
        while (!isPosValid(chosenAIPos)){
            System.out.println("AI TRIED: " + chosenAIPos);
            chosenAIPos = rand.nextInt(getBoardSize());
            if (isPosValid(chosenAIPos)){
                filledSlot.add(chosenAIPos);

                System.out.println("AI CHOOSES: " + chosenAIPos);
                System.out.println("List of filled slots: " + filledSlot);
                //Function will terminate here and not below if this new one is valid.
                return chosenAIPos;
            }
        }
        //If the first random chosen is valid it will play.
        if (isPosValid(chosenAIPos)) {
            filledSlot.add(chosenAIPos);
        }
        System.out.println("AI CHOOSES: " + chosenAIPos);
        System.out.println("List of filled slots: " + filledSlot);

        return chosenAIPos;
    }
}
