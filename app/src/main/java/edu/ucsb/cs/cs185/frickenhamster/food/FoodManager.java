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
    private int lastRandom;

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

        food = addFood("Coffee", "American", R.drawable.caje_coffee);
        food.addRestaurant("Caje", "http://www.yelp.com/biz/caje-isla-vista", "http://1.bp.blogspot.com/-I57oXWaiBW4/U3ppByNtIUI/AAAAAAAAA2o/HXAcIVGSAQs/s1600/caje-coffee.jpg");
        food.addRestaurant("Coffee Collaborative", "http://www.yelp.com/biz/coffee-collaborative-isla-vista", "https://s-media-cache-ak0.pinimg.com/736x/9e/35/37/9e353772511c0d89c8c2001a2887ada4.jpg");

        food = addFood("Barbecue", "American", R.drawable.captains_bbq);
        food.addRestaurant("Kaptain’s Firehouse BBQ", "http://www.yelp.com/biz/kaptains-firehouse-bbq-isla-vista", "https://m1.behance.net/rendition/pm/3517159/disp/29a18efe443464e46387676904b37d58.png");
        food.addRestaurant("Woody's BBQ", "http://www.yelp.com/biz/woodys-bbq-goleta", "http://ifranchisenews.com/wp-content/uploads/2013/01/woodys-bar-b-q-logo-300.jpg");

        food = addFood("Salad", "American", R.drawable.silvergreens_salad);
        food.addRestaurant("Silvegreens", "http://www.yelp.com/biz/silvergreens-isla-vista", "http://www.silvergreens.com/images/New-Silvergreens-Logo.jpg");
        food.addRestaurant("South Coast Deli", "http://www.yelp.com/biz/south-coast-deli-isla-vista", "http://www.santabarbara.com/dining/news/wp-content/uploads/2011/10/111017-south-coast-deli.jpg");

		food = addFood("Bagel", "American", R.drawable.spudnuts_bagels);
        food.addRestaurant("Spudnuts Donuts", "http://www.yelp.com/biz/spudnuts-donuts-isla-vista", "http://www.dazzleitupevents.com/wp-content/uploads/2014/04/spudnuts.jpg");

        food = addFood("Donut", "American", R.drawable.spudnuts_donuts);
        food.addRestaurant("Spudnuts Donuts", "http://www.yelp.com/biz/spudnuts-donuts-isla-vista", "http://www.dazzleitupevents.com/wp-content/uploads/2014/04/spudnuts.jpg");

		food = addFood("Burrito", "Mexican", R.drawable.burrito);
        food.addRestaurant("Freebirds World Burrito", "http://www.yelp.com/biz/freebirds-world-burrito-isla-vista", "http://loyalogy.com/wp-content/uploads/2013/06/freebirds.jpg");
        food.addRestaurant("Rosarito", "http://www.yelp.com/biz/rosarito-goleta", "http://s3-media3.fl.yelpcdn.com/bphoto/xFRMZv1QUu-g1NONvZ4f5g/o.jpg");

        food = addFood("Tacos", "Mexican", R.drawable.tacos);
        food.addRestaurant("El Sitio", "http://www.yelp.com/biz/el-sitio-isla-vista?osq=Tacos", "https://www.santabarbara.com/dining/photos/el-sitio-iv-2.jpg");
        food.addRestaurant("Los Agaves", "http://www.yelp.com/biz/los-agaves-santa-barbara-2", "http://www.losagavesfl.com/i/los_agaves.png");

        food = addFood("Sushi", "Japanese", R.drawable.sushiya_sushi);
        food.addRestaurant("Sushiya Express", "http://www.yelp.com/biz/sushiya-express-goleta-2", "https://www.santabarbara.com/dining/photos/sushiya-1.jpg");
		food.addRestaurant("Goleta Sushi House", "http://www.yelp.com/biz/goleta-sushi-house-goleta", "https://res.cloudinary.com/roadtrippers/image/upload/c_fill,h_316,w_520/v1377120383/goleta-sushi-house-52152f504203c3863b000e3c.jpg");


		/*
		addFood("Boiled Brocolli", "Kawaii");
		addFood("Katsu", "Japanese");
		addFood("Ramen", "Japanese");
		addFood("Curry Rice", "Japanese");
		*/
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
        while(lastRandom == nn) {
            nn = (int)(Math.random() * allowedFoods.size());
        }
        lastRandom = nn;
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
