package app.repbulic.order.orderrepublic.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.adapters.FoodAdapter;
import app.repbulic.order.orderrepublic.models.Food;

public class FoodController {

    //create food (only users whose isOwner=true are able to call this method)
    public static String createFood(Food food) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("food");
        //update already existed favoritesList in user
        String id = dbref.push().getKey();
        food.setFoodId(id);
        dbref.child(id).setValue(food);
        return id;
    }

    //TODO:update ui when data received
    //read foods or food when foodId is passed
    public static void readFoods(final ListView favlistView, final Context context, final ArrayList<String> foodIds) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("food");
        //add eventlistener to reference
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                ArrayList<Food> foods = new ArrayList<>();
                FoodAdapter foodAdapter;
                for (DataSnapshot foodDataSnapshot : dataSnapshot.getChildren()) {
                    Food food = foodDataSnapshot.getValue(Food.class);
                    if(food.getFoodId().equals(foodIds.get(i)))
                    {
                        foods.add(food);
                    }
                    i++;
                    //food.logger();
                    //Log.d("foods", food.toString());
                }
                foodAdapter = new FoodAdapter(context, foods);
                favlistView.setAdapter(foodAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void readFood(String foodId) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("food").child(foodId);
        //add eventlistener to reference
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);
                //food.logger();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //update food
    //food can be updated only by owner users

    public static void updateFood(final Food updatedFood) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("food").child(updatedFood.getFoodId());
        //add eventlistener to reference
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dbref.setValue(updatedFood);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //delete food
    public static void deleteFood(String foodId) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("food").child(foodId);
        //add eventlistener to reference
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                dbref.setValue(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
