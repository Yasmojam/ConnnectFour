package com.example.connectfour.Board;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<ImageModel> imageModels = new ArrayList<ImageModel>();
    ArrayList<ImageView> imageViews = new ArrayList<ImageView>();

    public ImageAdapter(Context context, ArrayList<ImageModel> ImageModels){
        this.context = context;
        this.imageModels = ImageModels;
    }

    @Override
    public int getCount() {
        return imageModels.size();
    }

    @Override
    public ImageModel getItem(int position) {
        return imageModels.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //TODO switch statement for layout.
            //FOR 7X6
            imageView.setLayoutParams(new GridView.LayoutParams(150,150));
            //FOR 8X8
            //imageView.setLayoutParams(new GridView.LayoutParams(150,138));
            //FOR 6x5
            //imageView.setLayoutParams(new GridView.LayoutParams(170,170));
        }
        else{
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(imageModels.get(position).getMThumbId());
        //add to list of image views
        imageViews.add(imageView);
        return imageView;
    }

    public ArrayList<ImageView> getImageViews() {
        return imageViews;
    }

    public void setBackgroundOfImageView(int position , int colour){
        //I DON'T KNOW WHY THIS IS OFF BY 2
        imageViews.get(position + 2).setBackgroundColor(colour); //+2
    }
}