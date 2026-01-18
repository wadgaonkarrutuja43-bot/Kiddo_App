package com.example.miniproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitAdapter extends BaseAdapter {
    private final Context context;
    private final int[] fruitImages;
    private final String[] fruitNames;

    public FruitAdapter(Context context, int[] fruitImages, String[] fruitNames) {
        this.context = context;
        this.fruitImages = fruitImages;
        this.fruitNames = fruitNames;
    }

    @Override
    public int getCount() {
        return fruitImages.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_fruit, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.fruitImage);
        TextView textView = convertView.findViewById(R.id.fruitName);

        imageView.setImageResource(fruitImages[position]);
        textView.setText(fruitNames[position]);

        return convertView;
    }
}