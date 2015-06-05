package edu.ucsb.cs.cs185.frickenhamster.food;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class customImageAdapter extends ArrayAdapter<Bitmap> {
    private final Context context;
    private final ArrayList<Bitmap> values;

    public customImageAdapter(Context context, ArrayList<Bitmap> values) {
        super(context, R.layout.imageitem, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.imageitem, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        imageView.setImageBitmap(values.get(position));
        return rowView;
    }
}