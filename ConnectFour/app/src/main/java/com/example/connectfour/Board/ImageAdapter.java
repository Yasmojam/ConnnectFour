package com.example.connectfour.Board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.example.connectfour.R;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<ImageModel> ImageModels = new ArrayList<ImageModel>();

    public ImageAdapter(Context context, ArrayList<ImageModel> ImageModels){
        this.context = context;
        this.ImageModels = ImageModels;
    }

    @Override
    public int getCount() {
        return ImageModels.size();
    }

    @Override
    public Object getItem(int position) {
        return ImageModels.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(ImageModels.get(position).getMThumbId());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(150, 150));

        return imageView;
    }
}