package app.repbulic.order.orderrepublic.controllers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.repbulic.order.orderrepublic.models.Order;

public class OrderController {


    //create order (only users, owners can't create order)
    public static String createOrder(Order order) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order");
        //update already existed favoritesList in user
        String id = dbref.push().getKey();
        order.setOrderId(id);
        dbref.child(id).setValue(order);
        return id;
    }

    //TODO:update ui when data received
    //read orders or specific order when orderId is passed
    public static void readOrders() {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order");
        //add eventlistener to reference
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot orderDataSnapshot : dataSnapshot.getChildren()) {
                    Order order = orderDataSnapshot.getValue(Order.class);
                    //order.logger();
                    //Log.d("foods", order.toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void readOrder(String orderId) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order").child(orderId);
        //add eventlistener to reference
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Order order = dataSnapshot.getValue(Order.class);
                // order.logger();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //update order
    //order can be updated only by owner users

    public static void updateOrder(final Order updatedOrder) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order").child(updatedOrder.getOrderId());
        //add eventlistener to reference
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dbref.setValue(updatedOrder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //delete order
    public static void deleteOrder(String orderId) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order").child(orderId);
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
