package com.example.connectfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.connectfour.Board.VsAIGameBoard;
import com.example.connectfour.Board.VsPlayerGameBoard;

/**
 * Class for implementing functionality of the new game screen.
 * Player can select game type (vs Player or vs AI) and choose from a few different pre-set board sizes.
 */
public class NewGameActivity extends AppCompatActivity {

    public static final String EXTRA_ROWS = "com.example.connectfour.Board.EXTRA_ROWS";
    public static final String EXTRA_COLUMNS = "com.example.connectfour.Board.EXTRA_COLUMNS";

    private ImageButton vsPlayerButton;
    private ImageButton vsAIButton;
    private Spinner sizeSpinner;
    private int rows;
    private int columns;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rows = 6;
        columns = 7;
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_screen);
        vsPlayerButton = findViewById(R.id.vsPlayerButton);
        vsAIButton = findViewById(R.id.vsAIButton);
        sizeSpinner = findViewById(R.id.sizeSpinner);

        //Initialises the vs player button
        vsPlayerOnClickListener();
        //Initialises the vs AI button
        vsAIOnClickListener();
    }

    /**
     * Get the String from the spinner containing the board size
     * @return the selected board size
     */
    public String getBoardSize() {
        return sizeSpinner.getSelectedItem().toString();
    }


    /**
     * Get the number of rows on the game board selected.
     * @return number or rows.
     */
    public int getRows() {
        return Integer.parseInt(getBoardSize().substring(0, 1));
    }

    /**
     * Get the number of columns on the game board selected.
     * @return number of columns.
     */
    public int getColumns() {
        return Integer.parseInt(getBoardSize().substring(2, 3));
    }

    public void setFromSpinner() {
        columns = Integer.parseInt(getBoardSize().substring(2, 3));
        rows = Integer.parseInt(getBoardSize().substring(0, 1));
    }

    /**
     * Create new vs Player game when button is clicked.
     */
    public void vsPlayerOnClickListener() {
        vsPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFromSpinner();
                Intent startGame = new Intent(NewGameActivity.this, VsPlayerGameBoard.class);
                //TODO Add implementation of new game that takes into account the selected board size.
                startGame.putExtra(EXTRA_ROWS, getRows());
                startGame.putExtra(EXTRA_COLUMNS, getColumns());
                startActivity(startGame);
            }
        });
    }

    /**
     * Create new vs AI game when button is clicked.
     */
    public void vsAIOnClickListener() {
        vsAIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFromSpinner();
                //TODO Add implementation of new game that takes into account the selected board size.
                // - Also connect to a new game board screen.
                Intent startGame = new Intent(NewGameActivity.this, VsAIGameBoard.class);
                //TODO Add implementation of new game that takes into account the selected board size.
                startGame.putExtra(EXTRA_ROWS, getRows());
                startGame.putExtra(EXTRA_COLUMNS, getColumns());
                startActivity(startGame);
            }
        });
    }

    public void goBack () {
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBack = new Intent(NewGameActivity.this, StartScreenActivity.class);
                startActivity(goBack);
            }
        });
    }





}
