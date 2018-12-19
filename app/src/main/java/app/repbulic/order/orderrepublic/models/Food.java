package app.repbulic.order.orderrepublic.models;


import android.util.Log;

import java.util.ArrayList;

public class Food {
    private String foodId;
    private String foodName;
    private String foodDesctiption;
    private String price;
    private String pictureLink;
    private String category;
    private double rating;
    private String restaurantName;
    private String restaurantLogoLink;

    //firebase requires it!
    public Food(){}

    public Food(String foodId, String foodName, String foodDesctiption, String price, String pictureLink, String category, double rating, String restaurantName, String restaurantLogoLink) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodDesctiption = foodDesctiption;
        this.price = price;
        this.pictureLink = pictureLink;
        this.category = category;
        this.rating = rating;
        this.restaurantName = restaurantName;
        this.restaurantLogoLink = restaurantLogoLink;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDesctiption() {
        return foodDesctiption;
    }

    public void setFoodDesctiption(String foodDesctiption) {
        this.foodDesctiption = foodDesctiption;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLogoLink() {
        return restaurantLogoLink;
    }

    public void setRestaurantLogoLink(String restaurantLogoLink) {
        this.restaurantLogoLink = restaurantLogoLink;
    }

    public static ArrayList<Food> getDefaults() {
        ArrayList<Food> foods = new ArrayList<Food>();
        Food food1 = new Food(
                "-LTra7xYft_4merocAoL",
                "osh",
                "rice and meat",
                "7000",
                "oshlink",
                "entreee",
                4.5,
                "Rayhon",
                "Rayhon logo");
        Food food2 = new Food(
                "-LTr_vGHijvuDW95r0zv",
                "sushi",
                "rice and fish",
                "70000",
                "sushilink",
                "entreee",
                4.5,
                "Japana mama",
                "Rayhon logo");
        foods.add(food1);
        foods.add(food2);
        return foods;
    }

    public void logger()
    {
        Log.d("food", this.getFoodId());
        Log.d("food", this.getFoodName());
        Log.d("food", this.getFoodDesctiption());
        Log.d("food", this.getCategory());
        Log.d("food", this.getPrice());
        Log.d("food", this.getRestaurantName());
        Log.d("food", String.valueOf(this.getRating()));
    }
}
