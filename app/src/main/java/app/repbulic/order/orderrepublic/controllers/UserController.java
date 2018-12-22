package app.repbulic.order.orderrepublic.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.MainActivity;
import app.repbulic.order.orderrepublic.authentication.LoginActivity;
import app.repbulic.order.orderrepublic.iu.owner.OwnerActivity;
import app.repbulic.order.orderrepublic.models.User;

public class UserController {




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

    public static void readUserByEmail(String email, final ProgressBar progressBar, final Context applicationContext) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users");
        //add eventlistener to reference
        dbref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnap: dataSnapshot.getChildren())
                {

                    User user = userSnap.getValue(User.class);
                    progressBar.setVisibility(View.GONE);
                    if (user.isOwner()) {
                        Intent intent = new Intent(applicationContext, OwnerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("user", user);
                        applicationContext.startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(applicationContext, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("user", user);
                        applicationContext.startActivity(intent);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
