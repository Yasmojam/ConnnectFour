package com.example.connectfour.Board;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.example.connectfour.R;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int selectedPosition = -1;
    private View selectedView = null;

    int player1Counter = Color.RED;
    int player2Counter = Color.YELLOW;

    private int numCounters = 0;

    private int maxCounters = 42; //6 x 7 board

    private ArrayList<Integer> selectedPositions = new ArrayList<Integer>();



    private ArrayList<ImageView> boardPieces = new ArrayList<>();

    //Keep all images in array
    public Integer[] mThumbIds = {
            R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board,
            R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board,
            R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board,
            R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board,
            R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board,
            R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board, R.drawable.board
    };


    //Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
        getBoardPieces().add(imageView);

        if (getSelectedPositions().contains(position) && getNumCounters()%2 != 0) {
            //boardPieces.get(getSelectedPositions().indexOf(position)).setBackgroundColor(player1Counter);
            imageView.setBackgroundColor(player1Counter);
        }
        else if (getSelectedPositions().contains(position) && getNumCounters()%2 == 0){
            //getBoardPieces().get(getSelectedPositions().indexOf(position)).setBackgroundColor(player2Counter);
            imageView.setBackgroundColor(player2Counter);
        }
        else {
            ;
        }
        return imageView;
    }

//


//        if (position == selectedPosition){
//            imageView.setBackgroundColor(Color.RED);
//        }
//        else{
//            //imageView.setBackgroundColor(Color.TRANSPARENT);
//            ;


    public ArrayList<ImageView> getBoardPieces() {
        return boardPieces;
    }

    public void addSelectedPositions(int selectedPosition){
        selectedPositions.add(selectedPosition);
    }

    public ArrayList<Integer> getSelectedPositions() {
        return selectedPositions;
    }

    public void setView(View view){
        selectedView = view;
    }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getNumCounters(){
        return numCounters;
    }

    public void setNumCounters(int numCounters) {
        this.numCounters = numCounters;
    }

    public int getMaxCounters() {
        return maxCounters;
    }
}

