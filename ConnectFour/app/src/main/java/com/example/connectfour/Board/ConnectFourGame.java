package com.example.connectfour.Board;

import java.util.Arrays;


/**Class which represents the Connect Four game logic.**/
public class ConnectFourGame {
    //Initialise board with dimensions of width x height
    private final int width, height;
    private final int[][] grid;

    /**Method which initialised the empty Connect Four game board with a width and height.**/
    public ConnectFourGame(int width, int height){
        this.width = width;
        this.height = height;
        grid = new int[width][height];


        //Initialised the board with no counters i.e. 0 is an empty slot.
        for (int w = 0; w < width; w++){
            for (int h = 0; h < height; h++){
                grid[w][h] = 0;
            }
        }
        printGrid();
    }

    /**Method which replaces the empty slot at coordinate position with 1.**/
    public void placeCounterInGrid(Coordinate coordinate){
        grid[coordinate.getHeight()][coordinate.getWidth()] = 1;
    }

    /**Method which converts GridView position to a coordinate with width and height.**/
    public Coordinate convertPositionToCoordinate(int position){
        //Common maths ways to do this:
        // x = i % width
        // y = i / width
        int posWidth = position % (width - 1);
        //floorDiv divides a by b and returns the integer rounded down so 5/2 = 2.5 but floorDiv returns 2.
        int posHeight = Math.floorDiv(position - posWidth, height);

        return new Coordinate(posWidth, posHeight);
    }


    /**Method which converts coordinate width and height to GridView position.**/
    public int convertCoordinateToPosition(Coordinate coordinate){
        //1dIndex = column + (row * length_of_row)
        int toPosition = coordinate.getWidth() + ((coordinate.getHeight()) * height);

        System.out.println("Converted coordinate, " + coordinate.toString() + ", to position: " + toPosition);

        return toPosition;
    }

    /**Method that checks if a slot on the board is full. If slot is full, returns true.**/
    public boolean isSlotFull(Coordinate coordinate){
        if (grid[coordinate.getHeight()][coordinate.getWidth()] != 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**Method which creates a Coordinate using the position on GridView and returns counter position.**/
    public int chooseAndDropCounter(int position){
        Coordinate chosenPosCoord = convertPositionToCoordinate(position);

        Coordinate playablePosCoord = chosenPosCoord;

        boolean full = isSlotFull(playablePosCoord);

        //Try to place counter at the position on the board. If something is in this slot and there is a space below, goes down one space.
        while (!full && playablePosCoord.getHeight() < height){
            Coordinate nextPlayablePosCoord =  new Coordinate(playablePosCoord.getWidth(), playablePosCoord.getHeight() + 1);
            if (isSlotFull(nextPlayablePosCoord)){
                full = true;
            }
            else {
                playablePosCoord = nextPlayablePosCoord;
            }
        }
        placeCounterInGrid(playablePosCoord);

        printGrid();

        return convertCoordinateToPosition(playablePosCoord);
    }

        /**Method which prints the board grid to the console. This is used for debugging.**/
    public void printGrid(){
        StringBuilder gridPrint = new StringBuilder("Grid: \n");
        for (int i = 0; i < width; i++){
            gridPrint.append("[").append(i).append("]").append(Arrays.toString(grid[i])).append("\n");
        }
        System.out.println(gridPrint);
    }
}