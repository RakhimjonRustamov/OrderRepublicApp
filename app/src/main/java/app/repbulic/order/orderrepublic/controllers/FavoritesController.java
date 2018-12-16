package app.repbulic.order.orderrepublic.controllers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavoritesController {

    //create
    public static boolean createFavorites(String userId, ArrayList<String> favList) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users");
        //update already existed favoritesList in user
        dbref.child(userId).child("favoritesList").setValue(favList);

        //return some boolean to confirm something
        return true;
    }

    //read
    public static boolean readFavorites(String userId) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users").child(userId);
        //add eventlistener to reference
        dbref.child("favoritesList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot favDataSnapshot : dataSnapshot.getChildren()) {
                    String fav = favDataSnapshot.getValue(String.class);
                    Log.d("favor", fav);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return true;
    }


    //delete
    //update

}
