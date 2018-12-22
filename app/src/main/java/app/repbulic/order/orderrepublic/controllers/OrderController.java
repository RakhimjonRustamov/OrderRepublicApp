package app.repbulic.order.orderrepublic.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.adapters.CartRestaurantsAdapter;
import app.repbulic.order.orderrepublic.adapters.RecyclerViewAdapter;
import app.repbulic.order.orderrepublic.models.Food;
import app.repbulic.order.orderrepublic.models.Order;

public class OrderController {

    private static DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order");

    //create order (only users, owners can't create order)
    public static String createOrder(Order order) {

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

    public static void readOrderRestaurants(final String userId, final RecyclerView recyclerView, final Context context) {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order");
        //add eventlistener to reference


        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> restNames = new ArrayList<>();
                ArrayList<String> restPics = new ArrayList<>();
                Log.d("tag", "here");
                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    Order order = orderSnapshot.getValue(Order.class);
                    if (order.getUserId().equals(userId) && order.getStatus().equals("in_cart")) {
                        for (Food food : order.getFoods()) {
                            restNames.add(food.getRestaurantName());
                            restPics.add(food.getRestaurantLogoLink());
                        }
                    }
                }
                recyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));
                CartRestaurantsAdapter adapter = new CartRestaurantsAdapter(context, restNames, restPics);
                recyclerView.setAdapter(adapter);


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
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order");
        //add eventlistener to reference
        dbref.orderByChild("restaurantName").equalTo(updatedOrder.getRestaurantName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // for(DataSnapshot a: dataSnapshot.getChildren())
                if (dataSnapshot.getValue() == null)
                    createOrder(updatedOrder);
                else {
                    Order oldOrder = null;
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                         oldOrder = dataSnapshot1.getValue(Order.class);
                    }
                    ArrayList<Food> newList = new ArrayList<>();
                    ArrayList<Integer> newQuantities = new ArrayList<>();
                    newQuantities = oldOrder.getQuantities();
                    newList= oldOrder.getFoods();
                    newQuantities.add(updatedOrder.getQuantities().get(0));
                    newList.add(updatedOrder.getFoods().get(0));
//                        oldOrder.getFoods().add(updatedOrder.getFoods().get(0));
                    dbref.child(oldOrder.getOrderId()).child("foods").setValue(newList);
                    dbref.child(oldOrder.getOrderId()).child("quantities").setValue(newQuantities);
                    createOrder(oldOrder);
                }
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
