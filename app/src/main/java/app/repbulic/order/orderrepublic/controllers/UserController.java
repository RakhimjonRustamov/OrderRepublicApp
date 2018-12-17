package app.repbulic.order.orderrepublic.controllers;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.models.User;

public class UserController {

//    //create
//    public static void createFavorite(String userId, ArrayList<String> favList) {
//        //get database reference
//        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users");
//        //update already existed favoritesList in user
//        dbref.child(userId).child("favoritesList").setValue(favList);
//
//    }


    //TODO:update ui when data received
    //read
    public static void readUser(final String userId, final TextView firstName, final TextView lastName, final TextView email) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users").child(userId);
        //add eventlistener to reference
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    user.logger();
                    firstName.setText(user.getFirstName());
                    lastName.setText(user.getLastName());
                    email.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //update

    public static void updateUser(String userId, final String firstName, final String lastName, final String email) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users").child(userId);
        //add eventlistener to reference
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dbref.child("firstName").setValue(firstName);
                dbref.child("lastName").setValue(lastName);
                dbref.child("email").setValue(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //delete

    public static void deleteUser(String userId) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users").child(userId);
        //add eventlistener to reference
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
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
