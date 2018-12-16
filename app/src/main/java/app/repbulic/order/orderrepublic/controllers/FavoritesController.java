package app.repbulic.order.orderrepublic.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        //commented lines below are not working for now


//        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("favorites").child(userId);
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                User post = dataSnapshot.getValue(User.class);
//                Log.d("check",post.toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("check", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        };
//        dbref.addValueEventListener(postListener);
        return true;
    }


    //delete
    //update

}
