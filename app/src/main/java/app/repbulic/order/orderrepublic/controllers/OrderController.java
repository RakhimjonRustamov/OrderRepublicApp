package app.repbulic.order.orderrepublic.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.adapters.CartRestaurantsAdapter;
import app.repbulic.order.orderrepublic.adapters.DoneOrdersAdapter;
import app.repbulic.order.orderrepublic.adapters.PendingOrdersAdapter;
import app.repbulic.order.orderrepublic.adapters.PlacedOrdersAdapter;
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
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order");
        //add eventlistener to reference


        dbref.orderByChild("status").equalTo("in_cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> restNames = new ArrayList<>();
                ArrayList<String> restPics = new ArrayList<>();
                ArrayList<Order> orders = new ArrayList<>();
                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    Order order = orderSnapshot.getValue(Order.class);
                    if (order.getUserId().equals(userId)) {
                        restNames.add(order.getRestaurantName());
                        restPics.add(order.getRestauranPicLink());
                        orders.add(order);
                    }
                }
                recyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));
                CartRestaurantsAdapter adapter = new CartRestaurantsAdapter(context, restNames, restPics, orders);
                recyclerView.setAdapter(adapter);


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
        dbref.orderByChild("status").equalTo("in_cart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() == null)
                    createOrder(updatedOrder);
                else {
                    Order oldOrder = null;
                    boolean ordered = false;
                    boolean finished = false;

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        oldOrder = dataSnapshot1.getValue(Order.class);
                        if (!finished) {
                            if (oldOrder.getRestaurantName().equals(updatedOrder.getRestaurantName())) {

                                for (int i = 0; i < oldOrder.getFoods().size(); i++) {
                                    if (oldOrder.getFoods().get(i).getFoodId().equals(updatedOrder.getFoods().get(0).getFoodId()))
                                        ordered = true;
                                }
                                if (!ordered) {
                                    ArrayList<Food> newList = new ArrayList<>();
                                    ArrayList<Integer> newQuantities = new ArrayList<>();
                                    newQuantities = oldOrder.getQuantities();
                                    newList = oldOrder.getFoods();
                                    newQuantities.add(updatedOrder.getQuantities().get(0));
                                    newList.add(updatedOrder.getFoods().get(0));
                                    dbref.child(oldOrder.getOrderId()).child("foods").setValue(newList);
                                    dbref.child(oldOrder.getOrderId()).child("quantities").setValue(newQuantities);
                                }
                                finished = true;
                            } else{
                                createOrder(updatedOrder);
                                finished = true;
                            }
                        } else {

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void placeOrder(final Order updatedOrder) {
        //get database reference
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order").child(updatedOrder.getOrderId());
        dbref.setValue(updatedOrder);
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


    public static void readPlacedOrders(String userId, final RecyclerView ordersRecyclerView, final Context context) {
        final ArrayList<String> restNames = new ArrayList<>();
        final ArrayList<String> restPics = new ArrayList<>();
        final ArrayList<String> statuses = new ArrayList<>();
        final ArrayList<String> orderIds = new ArrayList<>();

        final ArrayList<String> titles = new ArrayList<>();

        final ArrayList<String> totals = new ArrayList<>();
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order");
        dbref.orderByChild("userId").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot orderSnap : dataSnapshot.getChildren()) {
                    Order order = orderSnap.getValue(Order.class);
                    if (!order.getStatus().equals("in_cart")) {
                        restNames.add(order.getRestaurantName());
                        restPics.add(order.getRestauranPicLink());
                        statuses.add(order.getStatus());
                        orderIds.add(order.getOrderId());
                        titles.add(order.getOrderTitle());
                        totals.add(order.getTotalPrice());
                    }
                }
                ordersRecyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));
                PlacedOrdersAdapter adapter = new PlacedOrdersAdapter(context, restNames, restPics, statuses, orderIds, titles, totals);
                ordersRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static void readDoneOrders(String restName, final RecyclerView doneOrdersRecyclerView, final Context context) {
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order");

        dbref.orderByChild("restaurantName").equalTo(restName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Order> orders = new ArrayList<>();
                for (DataSnapshot orderSnap : dataSnapshot.getChildren()) {
                    Order order = orderSnap.getValue(Order.class);
                    if (order.getStatus().equals("done")) {
                        orders.add(order);
                    }
                }
                doneOrdersRecyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));
                DoneOrdersAdapter adapter = new DoneOrdersAdapter(orders, context);
                doneOrdersRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void readPendingOrders(String restName, final RecyclerView pendingOrdersRecyclerView, final Context context) {
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order");

        dbref.orderByChild("restaurantName").equalTo(restName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Order> orders = new ArrayList<>();
                for (DataSnapshot orderSnap : dataSnapshot.getChildren()) {
                    Order order = orderSnap.getValue(Order.class);
                    if (order.getStatus().equals("in_process") || order.getStatus().equals("placed")) {
                        orders.add(order);
                    }
                }
                pendingOrdersRecyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));
                PendingOrdersAdapter adapter = new PendingOrdersAdapter(orders, context);
                pendingOrdersRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void updateOrderStatus(String orderId, String status) {
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("order").child(orderId);
        dbref.child("status").setValue(status);
    }
}
