package com.example.connectfour.Board;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConnectFourGame {

    private static final char[] players = {'R', 'Y'}; //define players Red R, Yellow Y

    private final int row, col;  //board dimensions

    private final char[][] grid;  //grid for board

    private int lastCol = -1, lastTop = -1; //last move by a player, last column and the top counter ie. row

    private int chosenCol;

    int[] gridViewPosRep;

    int[][] convertedGridView;

    public ConnectFourGame(int row, int col) {
        this.row = row;
        this.col = col;
        grid = new char[col][];

        //initiate the grid with no counters
        for (int i = 0; i < col; i++){
            Arrays.fill(grid[i] = new char[row], '.');
        }

        //create grid representation and populate
        gridViewPosRep = new int[col*row];
        for (int i = 0; i < col*row; i++){
            gridViewPosRep[i] = i;
        }

        //convert this grid to 2d array
        convertedGridView = new int[col][row];
        for (int i = 0; i < col; i++){
            for (int j = 0; j < row; j++){
                convertedGridView[i][j] = gridViewPosRep[(j*col) + i];
            }
        }

    }

    //method for representing the board in strings
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String toString(){
        return IntStream.range(0, row).
                mapToObj(Integer::toString).
                collect(Collectors.joining()) +
                "\n" +
                Arrays.stream(grid).
                        map(String::new).collect(Collectors.joining("/n"));
    }

    //get string representation of the row containing the last play of the user
    public String horizontal(){
        return new String(grid[lastTop]);
    }

    //get string representation of the column containing rhe last play of the user
    public String vertical() {
        StringBuilder stringBuilder = new StringBuilder(col);

        for (int h = 0; h < col; h++) {
            stringBuilder.append(grid[h][lastCol]);
        }
        return stringBuilder.toString();
    }

    //get string representation of the "/" diagonals containing the last play of the user
    public String slashDiagonal(){
        StringBuilder stringBuilder = new StringBuilder(col);

        for (int h = 0; h < col; h++) {
            int w = lastCol + lastTop - h;

            if (0 <= w && w < row){
                stringBuilder.append(grid[h][w]);
            }
        }
        return stringBuilder.toString();
    }

    //get string representation of the "\" diagonals containing rhe last play of the user
    public String blackslashDiagonal(){
        StringBuilder stringBuilder = new StringBuilder(col);

        for (int h = 0; h < col; h++){
            int w = lastCol - lastTop + h;

            if (0 <= w && w < row){
                stringBuilder.append(grid[h][w]);
            }
        }
        return stringBuilder.toString();
    }

    //static method checking if a substring is in str (tru if index of substring in string is more than or equal to zero
    public static boolean contains(String string, String substring){
        return string.indexOf(substring) >= 0;
    }

    //method checking if last play is a winning play
    public boolean isWinningPlay(){
        if (lastCol == -1){
            System.err.println("No move has been made yet"); //won't be used really be used but maybe useful for debugging
            return false;
        }

        char sym = grid[lastTop][lastCol];
        //winning streak with the last play symbol
        String streak = String.format("%c%c%c%c", sym, sym, sym, sym); //%c representing one symbol either Y or R and if they all match 4 in a row then you win

        //check if streak is in row, col, diagonal, or backslash diagonal
        return contains(horizontal(), streak) || contains(vertical(), streak)
                || contains(slashDiagonal(), streak) || contains(blackslashDiagonal(), streak);
    }

    //PLAYER INPUT METHODS AND CHECKS
    /**Method which sets player's chosen column**/
    public void setChosenCol(int chosenCol) {
        this.chosenCol = chosenCol;
    }

    /**Method which checks if player's chosen column is valid (e.g. not full)**/
    public boolean isChosenColValid(int chosenCol){
        if (!(0<= chosenCol && chosenCol < row)){
            return true;
        }
        else{
            return false;
            //CONSIDER MAKING TOAST HERE WHICH TELLS PLAYER COLUMN IS FULL.
        }
    }

    /**Method which places counter (R or Y) in first available row in the chosen column**/
    public void placeCounter(char playerCounter, int chosenCol){
        for (int h = col - 1; h >= 0; h--){
            if (grid[h][chosenCol] == '.'){
                grid[lastTop = h][lastCol = chosenCol] = playerCounter; //make this slot the counter
                return;
            }
        }
    }

    /**Method which returns column from chosen position.**/
    public int getPositionCol(int position){
        //Find the position in 2d array
        int positionCol = 0 ;
        for (int i = 0; i < col; ++i ) {
            for (int j = 0; j < row; ++j ) {
                if (convertedGridView[i][j] == position ) {
                    // Found the correct i,j - return col
                    positionCol = j;
                }
            }
        }
        return positionCol;
    }

    /**Method which returns row from chosen position.**/
    public int getPositionRow(int position){
        //Find the position in 2d array
        int positionRow = 0 ;
        for (int i = 0; i < col; ++i ) {
            for (int j = 0; j < row; ++j ) {
                if (convertedGridView[i][j] == position ) {
                    // Found the correct i,j - return row
                    positionRow = i;
                }
            }
        }
        return positionRow;
    }

    /**Method which converts a row into gridview position by inputting **/
    //    /**Method which returns position that the counter will be places in after accounting for top row.**/
//    public int getPlayablePosFromChosenCol()

    /**Method which returns position where counter is placed.**/
    public int placeCounterPosition(char playerCounter, int position){
        int chosenCol = getPositionCol(position);
        for (int h = col - 1; h >= 0; h--){
            if (grid[h][chosenCol] == '.'){
                grid[lastTop = h][lastCol = chosenCol] = playerCounter; //make this slot the counter
            }
        }
        return lastTop + lastCol;
    }

    /**Method for processing player turn**/
    public void chooseAndDrop(char playerCounter, int position){
        if (isChosenColValid(position)){
            placeCounter(playerCounter, position);
        }
        else{
            return;
        }
    }

    public int getLastCol() {
        return lastCol;
    }

    public int getLastTop() {
        return lastTop;
    }
}
