package edu.ucsb.cs.cs185.frickenhamster.food;

import android.graphics.Bitmap;

public class FoodImage {
    private Bitmap mBitmap;
    private String foodType;

    public FoodImage(Bitmap newBitmap, String foodCategory) {
        mBitmap = newBitmap;
        foodType = foodCategory;
    }

    public String getFoodType() {
        return foodType;
    }

    public Bitmap getFoodBitmap() {
        return mBitmap;
    }
}
