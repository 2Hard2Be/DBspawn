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
public class CustomList2 extends ArrayAdapter<String> {

    private final Activity context;

    private final String[] eltextodehoy;
    public CustomList2 (Activity context,
                      String[] eltextodehoy) {
        super(context, R.layout.texto_listado, eltextodehoy);
        this.context = context;

        this.eltextodehoy = eltextodehoy;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView1= inflater.inflate(R.layout.texto_listado, null, true);

        TextView txtos = (TextView) rowView1.findViewById(R.id.texto_listado);

        txtos.setText(eltextodehoy[position]);
        return rowView1;
    }
}

