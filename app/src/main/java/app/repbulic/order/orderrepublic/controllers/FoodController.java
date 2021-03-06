package app.repbulic.order.orderrepublic.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import app.repbulic.order.orderrepublic.adapters.FoodAdapter;
import app.repbulic.order.orderrepublic.adapters.RecyclerViewAdapter;
import app.repbulic.order.orderrepublic.models.Food;

public class FoodController {

    //get database reference
    private static DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("food");

    //create food (only users whose isOwner=true are able to call this method)
    public static String createFood(Food food) {

        //update already existed favoritesList in user
        String id = dbref.push().getKey();
        food.setFoodId(id);
        dbref.child(id).setValue(food);
        return id;
    }

    public static void readFoodsByCategory(final String categoryName, final RecyclerView recyclerView, final Context context) {


        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Food> foods = new ArrayList<>();
                for (DataSnapshot foodDataSnapshot : dataSnapshot.getChildren()) {
                    Food food = foodDataSnapshot.getValue(Food.class);
                    if (food.getCategory().equals(categoryName))
                        foods.add(food);
                }

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, foods, "menu_activity");
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static void readFoodsByRestaurant(final String restName, final RecyclerView recyclerView, final Context context) {
        Log.d("food", "inside" + restName);
        dbref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Food> foods = new ArrayList<>();
                for (DataSnapshot foodDataSnapshot : dataSnapshot.getChildren()) {
                    Food food = foodDataSnapshot.getValue(Food.class);
                    if (food.getRestaurantName().equals(restName))
                        foods.add(food);
                    food.logger();
                }

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, foods, "");
                recyclerView.setAdapter(adapter);

//                FoodAdapter foodAdapter;
//                foodAdapter = new FoodAdapter(context, foods);
//                recyclerView.setAdapter(foodAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //TODO:update ui when data received
    //read foods or food when foodId is passed
    public static void readFavoriteFoods(final ListView favlistView, final Context context, final ArrayList<String> foodIds) {

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            int i = 0, j = foodIds.size();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Food> foods = new ArrayList<>();
                FoodAdapter foodAdapter;
                for (DataSnapshot foodDataSnapshot : dataSnapshot.getChildren()) {
                    Food food = foodDataSnapshot.getValue(Food.class);

                    if (i < j) {
                        for (int k = 0; k < j; k++) {
                            if (food.getFoodId().equals(foodIds.get(k))) {
                                foods.add(food);
                                i++;
                            }
                        }
                    }
                }
                Log.d("t", foods.toString());
                foodAdapter = new FoodAdapter(context, foods, favlistView);
                favlistView.setAdapter(foodAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void readFood(String foodId, final ArrayList<View> widgets) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("food").child(foodId);
        //add eventlistener to reference
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);
                ((EditText) (widgets.get(0))).setText(food.getFoodDesctiption());
                ((EditText) (widgets.get(1))).setText(food.getPictureLink());
                ((EditText) (widgets.get(2))).setText(food.getPrice());
                ((EditText) (widgets.get(3))).setText(food.getFoodName());
                //food.logger();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //update food
    //food can be updated only by owner users

    public static void updateFood(final ArrayList<String> values) {
        final DatabaseReference newref = dbref.child(values.get(0));
        newref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newref.child("category").setValue(values.get(1));
                newref.child("foodDesctiption").setValue(values.get(2));
                newref.child("pictureLink").setValue(values.get(3));
                newref.child("price").setValue(values.get(4));
                newref.child("foodName").setValue(values.get(5));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //delete food
    public static void deleteFood(final String foodId) {

        dbref.child(foodId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dbref.child(foodId).setValue(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
