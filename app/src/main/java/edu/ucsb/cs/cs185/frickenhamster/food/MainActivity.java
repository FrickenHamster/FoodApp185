package edu.ucsb.cs.cs185.frickenhamster.food;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {

    private ArrayList<String> al;
    private ArrayList<Bitmap> array_image;

    private ArrayAdapter<String> arrayAdapter;
    private customImageAdapter arrayPicAdapter;
    private int i;
    private Drawable myDrawableDefault;
    private Bitmap imageDefault;
    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        myDrawableDefault = getResources().getDrawable(R.drawable.image4);
        imageDefault= ((BitmapDrawable) myDrawableDefault).getBitmap();
        al = new ArrayList<String>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");
        al.add("html");
        al.add("c++");
        al.add("css");
        al.add("javascript");

        array_image = new ArrayList<Bitmap>();
        Drawable myDrawable = getResources().getDrawable(R.drawable.image1);
        Bitmap image1= ((BitmapDrawable) myDrawable).getBitmap();
        array_image.add(image1);
        Drawable myDrawable2 = getResources().getDrawable(R.drawable.image2);
        Bitmap image2= ((BitmapDrawable) myDrawable2).getBitmap();
        array_image.add(image2);
        Drawable myDrawable3 = getResources().getDrawable(R.drawable.image3);
        Bitmap image3= ((BitmapDrawable) myDrawable3).getBitmap();
        array_image.add(image3);
        //array_image.add(R.drawable.image4);

        arrayPicAdapter = new customImageAdapter(this, array_image);


        //CUSTOM ADAPTER
        flingContainer.setAdapter(arrayPicAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                array_image.remove(0);
                arrayPicAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                makeToast(MainActivity.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(MainActivity.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                array_image.add(imageDefault);
                arrayPicAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                //view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                //view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(MainActivity.this, "Clicked!");
            }
        });

    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.right)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }




}
