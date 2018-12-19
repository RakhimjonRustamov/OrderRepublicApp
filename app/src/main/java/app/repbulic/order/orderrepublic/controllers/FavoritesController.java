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

public class FavoritesController {

    //create
    public static void createFavorite(String userId, ArrayList<String> favList) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users");
        //update already existed favoritesList in user
        dbref.child(userId).child("favoritesList").setValue(favList);

    }



    //TODO:update ui when data received
    //read
    public static void readFavorites(String userId, final ListView favoritesList, final Context context) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users").child(userId);
        //add eventlistener to reference
        dbref.child("favoritesList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                ArrayList<String> favs = new ArrayList<>();

                for (DataSnapshot favDataSnapshot : dataSnapshot.getChildren()) {
                    String fav = favDataSnapshot.getValue(String.class);
                    Log.d("favor", fav);
                    favs.add(fav);
                }
                FoodController.readFoods(favoritesList, context,favs);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //update = delete when nothing is left in list

    public static void deleteFavorite(String userId, final String foodId) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users").child(userId);
        //add eventlistener to reference
        dbref.child("favoritesList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> updated = new ArrayList<>();
                for (DataSnapshot favDataSnapshot : dataSnapshot.getChildren()) {
                    String fav = favDataSnapshot.getValue(String.class);
                    Log.d("favor", fav);

                    if (foodId.equals(fav)) {
                        Log.d("favor", "this one should be deleted ");
                    } else updated.add(fav);
                }
                dbref.child("favoritesList").setValue(updated);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
