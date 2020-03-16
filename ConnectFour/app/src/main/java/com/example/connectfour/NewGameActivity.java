package com.example.connectfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.connectfour.Board.GameBoard;

public class NewGameActivity extends AppCompatActivity {

    private ImageButton vsPlayerButton;
    private ImageButton vsAIButton;
    private Spinner rowSpinner;
    private Spinner columnSpinner;
    private int rows;
    private int columns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        }
        else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_screen);
        vsPlayerButton = findViewById(R.id.vsPlayerButton);
        vsAIButton = findViewById(R.id.vsAIButton);
        rowSpinner = findViewById(R.id.rowSpinner);
        columnSpinner = findViewById(R.id.columnSpinner);
        rows = 6;
        columns = 6;
        vsPlayerOnClickListener();
        vsAIOnClickListener();

        ImageButton vsPlayerButton = findViewById(R.id.vsPlayerButton);
        vsPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startGame = new Intent(NewGameActivity.this, GameBoard.class);
                startActivity(startGame);
            }
        });


    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }


    public void vsPlayerOnClickListener() {
        vsPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSpinnerSelections();
            }
        });

    }

    public void vsAIOnClickListener() {
        vsAIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSpinnerSelections();
            }
        });
    }

    public void setSpinnerSelections() {
        setRows((int) rowSpinner.getSelectedItem());
        setColumns((int) columnSpinner.getSelectedItem());
    }

}
