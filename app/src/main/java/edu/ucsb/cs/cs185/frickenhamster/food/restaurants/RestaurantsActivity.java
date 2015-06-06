package edu.ucsb.cs.cs185.frickenhamster.food.restaurants;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.Menu;
import android.view.MenuItem;
import edu.ucsb.cs.cs185.frickenhamster.food.*;


public class RestaurantsActivity extends Activity
{

	private RecyclerView recyclerView;
	private RestaurantAdapter restaurantAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants);
		
		recyclerView = (RecyclerView) findViewById(R.id.restaurant_recycler_view);
		restaurantAdapter = new RestaurantAdapter();
		recyclerView.setAdapter(restaurantAdapter);
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_restaurants, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
