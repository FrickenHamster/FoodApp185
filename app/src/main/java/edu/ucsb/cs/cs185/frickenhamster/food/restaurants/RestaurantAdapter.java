package edu.ucsb.cs.cs185.frickenhamster.food.restaurants;

import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import edu.ucsb.cs.cs185.frickenhamster.food.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hamster
 * Date: 6/5/2015
 * Time: 4:22 PM
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>
{
	
	
	
	public class RestaurantViewItem
	{
		String name;
		String imageUrl;

		public RestaurantViewItem(String name, String imageUrl)
		{
			this.name = name;
			this.imageUrl = imageUrl;
		}
	}
	
	public class RestaurantViewHolder extends RecyclerView.ViewHolder
	{
		ImageView imageView;
		TextView textView;
		public RestaurantViewHolder(View itemView)
		{
			super(itemView);
			imageView = (ImageView) itemView.findViewById(R.id.restaurant_image_view);
			textView = (TextView) itemView.findViewById(R.id.restaurant_text_view);
		}
	}
	
	
	private ArrayList<RestaurantViewItem> restaurantViewItems;
	
	public RestaurantAdapter()
	{
		restaurantViewItems = new ArrayList<RestaurantViewItem>();
		restaurantViewItems.add(new RestaurantViewItem("uguu", "gay"));
	}

	@Override
	public int getItemCount()
	{
		return restaurantViewItems.size();
	}

	@Override
	public void onBindViewHolder(RestaurantViewHolder holder, int position)
	{
		
	}

	@Override
	public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
		RestaurantViewHolder vh = new RestaurantViewHolder(v);
		return vh;
	}
}
