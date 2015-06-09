package edu.ucsb.cs.cs185.frickenhamster.food;

import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hamster
 * Date: 6/2/2015
 * Time: 5:03 PM
 */
public class FoodManager
{

	private HashMap<String, Cuisine> cuisines;
	
	private ArrayList<Food> allowedFoods;

	private Context context;
	
	private Food selected;

	public FoodManager(Context context)
	{
		this.context = context;
		
		allowedFoods = new ArrayList<Food>();
		cuisines = new HashMap<String, Cuisine>();

		Food food;
		food = addFood("Pizza", "Italian", R.drawable.woodstocks_pizza);
		food.addRestaurant("Woodstock's Pizza", "http://www.yelp.com/biz/woodstocks-pizza-isla-vista-2", "http://s3-media1.fl.yelpcdn.com/bphoto/MqngpKj1_2KjhdIoJqYAAw/l.jpg");
        food.addRestaurant("Pizza My Heart", "http://www.yelp.com/biz/pizza-my-heart-isla-vista", "https://s-media-cache-ak0.pinimg.com/originals/51/2e/5c/512e5c37a0a1a8b7d66e988722f0bfa1.jpg");

        food = addFood("Hamburger", "American", R.drawable.habit_burger);
        food.addRestaurant("The Habit", "http://www.yelp.com/biz/the-habit-isla-vista", "http://www.habitburger.com/wp-content/themes/habitburger/images/logo-habit.jpg");
        food.addRestaurant("The Krusty Krab", "http://www.yelp.com/biz/the-krusty-krab-portland", "http://d.ibtimes.co.uk/en/full/1385860/actual-krusty-krab.jpg");

		/*addFood("Pizza", "American");
		addFood("Boiled Brocolli", "Kawaii");
		addFood("Hamburger", "American");
		addFood("Cheeseburger", "American");
		addFood("Katsu", "Japanese");
		addFood("Ramen", "Japanese");
		addFood("Curry Rice", "Japanese");
		addFood("Burrito", "Mexican");
		addFood("Tacos", "Mexican");*/
	}


	public Food addFood(String foodName, String cuisineName, int drawable)
	{
		Cuisine cc = cuisines.get(cuisineName);

		if (cc == null)
		{
			cc = new Cuisine(cuisineName);
			cuisines.put(cuisineName, cc);
		}

		Drawable myDrawable = context.getResources().getDrawable(drawable);
		Bitmap image = ((BitmapDrawable) myDrawable).getBitmap();
		Food food = new Food(foodName, image, cc);
		cc.addFood(food);
		if (cc.isAllowed())
		{
			allowedFoods.add(food);
		}
		
		return food;
	}
	
	public Food getRandomFood()
	{
		int nn = (int)(Math.random() * allowedFoods.size());
		return allowedFoods.get(nn);
	}
	
	public void allowCuisine(String cuisineName)
	{
		Cuisine cuisine = cuisines.get(cuisineName);
		if (cuisine == null)
			return;
		cuisine.setAllowed(true);
	}
	
	public void disallowCuisine(String cuisineName)
	{
		Cuisine cuisine = cuisines.get(cuisineName);
		if (cuisine == null)
			return;
		cuisine.setAllowed(false);
	}
	
	public void rebuildAllowedFoods()
	{
		allowedFoods.clear();
		for (Cuisine cuisine : cuisines.values())
		{
			if (cuisine.isAllowed())
			{
				for (Food food:cuisine.getFoods())
				{
					allowedFoods.add(food);
				}
			}
		}
	}
	
	public void selectFood(Food food)
	{
		selected = food;
	}

	public Food getSelectedFood()
	{
		return selected;
	}

	public ArrayList<Cuisine> getCuisineList()
	{
		return new ArrayList<Cuisine>(cuisines.values());
	}

}
