package app.repbulic.order.orderrepublic.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
/*//
    //TODO:update ui when data received
    //read foods or food when foodId is passed
    public static void readFoods() {
        //get database reference
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("food");
        //add eventlistener to reference
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot favDataSnapshot : dataSnapshot.getChildren()) {
                    Food food = favDataSnapshot.getValue(Food.class);

                    Log.d("foods", food.toString());
                }

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
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Food food = dataSnapshot.getValue(Food.class);

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
*/
}
