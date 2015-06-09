package edu.ucsb.cs.cs185.frickenhamster.food;

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


	public FoodManager()
	{
		allowedFoods = new ArrayList<Food>();
		cuisines = new HashMap<String, Cuisine>();

		addFood("Pizza", "American");
		addFood("Boiled Brocolli", "Kawaii");
		addFood("Hamburger", "American");
		addFood("Cheeseburger", "American");
		addFood("Katsu", "Japanese");
		addFood("Ramen", "Japanese");
		addFood("Curry Rice", "Japanese");
		addFood("Burrito", "Mexican");
		addFood("Tacos", "Mexican");
	}


	public void addFood(String foodName, String cuisineName)
	{
		Cuisine cc = cuisines.get(cuisineName);

		if (cc == null)
		{
			cc = new Cuisine(cuisineName);
			cuisines.put(cuisineName, cc);
		}

		Food food = new Food(foodName);
		cc.addFood(food);
		if (cc.isAllowed())
		{
			allowedFoods.add(food);
		}
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

	public ArrayList<Cuisine> getCuisineList()
	{
		return new ArrayList<Cuisine>(cuisines.values());
	}

}
