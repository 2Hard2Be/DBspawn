package com.example.dbspawn;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Peto-1 on 1/28/2015.
 */


    public class CustomList extends ArrayAdapter<Integer> {
        private final Activity context;

        private final Integer[] imageId;
        public CustomList(Activity context,
                          Integer[] imageId) {
            super(context, R.layout.imagenes, imageId);
            this.context = context;

            this.imageId = imageId;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.imagenes, null, true);

            ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

            imageView.setImageResource(imageId[position]);
            return rowView;
        }
    }

