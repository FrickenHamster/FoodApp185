package edu.ucsb.cs.cs185.frickenhamster.food;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import edu.ucsb.cs.cs185.frickenhamster.food.history.HistoryActivity;
import edu.ucsb.cs.cs185.frickenhamster.food.FoodImage;
import edu.ucsb.cs.cs185.frickenhamster.food.restaurants.*;


public class MainActivity extends Activity {
    public final static String FOOD_TYPE = "edu.ucsb.cs.cs185.frickenhamster.food.FOOD_TYPE";

    private ArrayList<FoodImage> array_image;

    private ArrayAdapter<String> arrayAdapter;
    private CustomImageAdapter arrayPicAdapter;
    private int i;
    @InjectView(R.id.frame)
    SwipeFlingAdapterView flingContainer;
    private FoodImage fImage1;
    private FoodImage fImage2;
    private FoodImage fImage3;
    private FoodImage fImage4;
    private FoodImage fImage5;
    private FoodImage fImage6;
    private FoodImage fImage7;
    private FoodImage fImage8;
    private Intent intent;
    
    private Context mainContext = this;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//startActivity(new Intent(this, RestaurantsActivity.class));
		
		ButterKnife.inject(this);

		array_image = new ArrayList<FoodImage>();
		Drawable myDrawable = getResources().getDrawable(R.drawable.caje_coffee);
		Bitmap image1 = ((BitmapDrawable) myDrawable).getBitmap();
		fImage1 = new FoodImage(image1, "coffee");
        array_image.add(fImage1);
        
		Drawable myDrawable2 = getResources().getDrawable(R.drawable.captains_bbq);
		Bitmap image2 = ((BitmapDrawable) myDrawable2).getBitmap();
        fImage2 = new FoodImage(image2, "bbq");
		array_image.add(fImage2);
        
		Drawable myDrawable3 = getResources().getDrawable(R.drawable.habit_burger);
		Bitmap image3 = ((BitmapDrawable) myDrawable3).getBitmap();
        fImage3 = new FoodImage(image3, "burger");
        array_image.add(fImage3);

        Drawable myDrawable4 = getResources().getDrawable(R.drawable.silvergreens_salad);
        Bitmap image4 = ((BitmapDrawable) myDrawable4).getBitmap();
        fImage4 = new FoodImage(image4, "salad");
        array_image.add(fImage4);

        Drawable myDrawable5 = getResources().getDrawable(R.drawable.spudnuts_bagels);
        Bitmap image5 = ((BitmapDrawable) myDrawable5).getBitmap();
        fImage5 = new FoodImage(image5, "bagels");
        array_image.add(fImage5);

        Drawable myDrawable6 = getResources().getDrawable(R.drawable.spudnuts_donuts);
        Bitmap image6 = ((BitmapDrawable) myDrawable6).getBitmap();
        fImage6 = new FoodImage(image6, "donuts");
        array_image.add(fImage6);

        Drawable myDrawable7 = getResources().getDrawable(R.drawable.sushiya_sushi);
        Bitmap image7 = ((BitmapDrawable) myDrawable7).getBitmap();
        fImage7 = new FoodImage(image7, "sushi");
        array_image.add(fImage7);

        Drawable myDrawable8 = getResources().getDrawable(R.drawable.woodstocks_pizza);
        Bitmap image8 = ((BitmapDrawable) myDrawable8).getBitmap();
        fImage8 = new FoodImage(image8, "salad");
        array_image.add(fImage8);

        arrayPicAdapter = new CustomImageAdapter(this, array_image);

        //temporarily creating the fake history file
        /*boolean exists = false;
        for (String file : fileList()) {
            if (file == "history.txt") exists = true;
        }
        if (!exists) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new BufferedOutputStream(openFileOutput("history.txt", MODE_PRIVATE)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (writer != null) {
                writer.println("Pizza---Woodstocks---Tuesday, June 2nd, 2015");
                writer.println("Pizza---Woodstocks---Wednesday, June 3rd, 2015");
                writer.println("Hamburger---The Habit---Thursday, June 4th, 2015");
                writer.println("Steak---Outback Steakhouse---Thursday, June 6th, 2015");
                writer.println("Pancakes---Denny's---Thursday, June 7th, 2015");
            }
            writer.close();
        }
        */

		//CUSTOM ADAPTER
		flingContainer.setAdapter(arrayPicAdapter);
		flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener()
		{
			@Override
			public void removeFirstObjectInAdapter()
			{
				// this is the simplest way to delete an object from the Adapter (/AdapterView)
				Log.d("LIST", "removed object!");
				array_image.remove(0);
				arrayPicAdapter.notifyDataSetChanged();
			}

			@Override
			public void onLeftCardExit(Object dataObject)
			{
				//Do something on the left!
				//You also have access to the original object.
				//If you want to use it just cast it (String) dataObject
                FoodImage mFoodImage = (FoodImage) dataObject;
                String foodType = mFoodImage.getFoodType();

			}

			@Override
			public void onRightCardExit(Object dataObject) {
                FoodImage mFoodImage = (FoodImage) dataObject;
                String foodType = mFoodImage.getFoodType();

                intent = new Intent(mainContext, RestaurantsActivity.class);
                intent.putExtra(FOOD_TYPE, foodType);
                startActivity(intent);
			}

			@Override
			public void onAdapterAboutToEmpty(int itemsInAdapter)
			{
				// Ask for more data here
                array_image.add(fImage1);
                array_image.add(fImage2);
                array_image.add(fImage3);
				array_image.add(fImage4);
                array_image.add(fImage5);
                array_image.add(fImage6);
                array_image.add(fImage7);
                array_image.add(fImage8);
				arrayPicAdapter.notifyDataSetChanged();
				Log.d("LIST", "notified");
				i++;
			}

			@Override
			public void onScroll(float scrollProgressPercent)
			{
				View view = flingContainer.getSelectedView();
				//view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
				//view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
			}
		});


		// Optionally add an OnItemClickListener
		flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
			@Override
			public void onItemClicked(int itemPosition, Object dataObject) {

			}
		});

	}

	static void makeToast(Context ctx, String s)
	{
		Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
	}


	//@OnClick(R.id.right)
	public void right()
	{
		/**
		 * Trigger the right event manually.
		 */
		flingContainer.getTopCardListener().selectRight();
	}

	//@OnClick(R.id.left)
	public void left()
	{
		flingContainer.getTopCardListener().selectLeft();
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_history) {
            launchHistoryActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    void launchHistoryActivity() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
