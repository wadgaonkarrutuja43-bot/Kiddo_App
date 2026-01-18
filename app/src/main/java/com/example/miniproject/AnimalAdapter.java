package com.example.miniproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalAdapter extends BaseAdapter {
    private final Context context;
    private final int[] animalImages;
    private final String[] animalNames;

    public AnimalAdapter(Context context, int[] animalImages, String[] animalNames) {
        this.context = context;
        this.animalImages = animalImages;
        this.animalNames = animalNames;
    }

    @Override
    public int getCount() {
        return animalImages.length;
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
                    .inflate(R.layout.item_animal, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.animalImage);
        TextView textView = convertView.findViewById(R.id.animalName);

        imageView.setImageResource(animalImages[position]);
        textView.setText(animalNames[position]);

        return convertView;
    }
}