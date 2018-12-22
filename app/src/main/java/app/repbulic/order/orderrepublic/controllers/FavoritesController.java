package app.repbulic.order.orderrepublic.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.adapters.FoodAdapter;
import app.repbulic.order.orderrepublic.models.Food;

public class FavoritesController {




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
                    favs.add(fav);
                }
                FoodController.readFavoriteFoods(favoritesList, context, favs);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //update = delete when nothing is left in list

    //when option is false, item in favorites will be deleted, otherwise added

    public static void updateFavorite(String userId, final String foodId, final boolean option, final ImageButton favButton) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users").child(userId);
        //add eventlistener to reference
        dbref.child("favoritesList").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> updated = new ArrayList<>();
                boolean exists = false;
                for (DataSnapshot favDataSnapshot : dataSnapshot.getChildren()) {
                    String fav = favDataSnapshot.getValue(String.class);
                    if (!option) {
                        if (foodId.equals(fav))
                            Log.d("favor", "this one should be deleted ");
                        else
                            updated.add(fav);
                    } else {
                        if (foodId.equals(fav)) {
                            exists = true;
                            updated.add(fav);
                        } else
                            updated.add(fav);
                    }
                }
                if (!exists && option) {
                    favButton.setImageResource(R.drawable.ic_favorite);
                    updated.add(foodId);
                }
                else
                    favButton.setImageResource(R.drawable.ic_favorite_empty);
                dbref.child("favoritesList").setValue(updated);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
