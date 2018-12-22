package app.repbulic.order.orderrepublic.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.MainActivity;
import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.OrderController;
import app.repbulic.order.orderrepublic.models.Food;
import app.repbulic.order.orderrepublic.models.Order;
import de.hdodenhof.circleimageview.CircleImageView;

public class FoodAdapter extends ArrayAdapter {

    private ArrayList<Food> foods;
    private Context context;
    private ListView favlist;

    private ArrayList<Boolean> added;

    public FoodAdapter(@NonNull Context context, @NonNull ArrayList<Food> foods, ListView favlist) {
        super(context, R.layout.favorites_list_item);
        this.foods = foods;
        this.context = context;
        this.favlist=favlist;
        added = new ArrayList<>();

        for (int i=0;i<foods.size();i++)
        {
            added.add(false);
        }
    }


    @Override
    public int getCount() {
        return foods.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FoodViewHolder holder = new FoodViewHolder();

        final Food food = foods.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.favorites_list_item, parent, false);
        }

        holder.nameTextView = convertView.findViewById(R.id.food_name_fav);
        holder.priceTextView = convertView.findViewById(R.id.food_price_fav);
        holder.restaurantTextView = convertView.findViewById(R.id.restaurantName_fav);
        holder.descriptionTextView = convertView.findViewById(R.id.food_description_fav);
        holder.image = convertView.findViewById(R.id.food_image_fav);
        holder.addToCart = convertView.findViewById(R.id.btn_add_to_cart__fav);

        Glide.with(context)
                .asBitmap()
                .load(foods.get(position).getPictureLink())
                .into(holder.image);


        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View item = (View) v.getParent();
                int pos = favlist.getPositionForView(item);

                if (!added.get(pos)) {
                    added.set(pos, true);
                    ArrayList<Integer> quantity = new ArrayList<>();
                    quantity.add(1);
                    ArrayList<Food> foodList = new ArrayList<>();

                   // Log.d("test", pos+"");
                    foodList.add(foods.get(pos));

                    Order order = new Order(
                            "",
                            "",
                            MainActivity.userEmail,
                            "",
                            "in_cart",
                            "",
                            foodList,
                            quantity,
                            MainActivity.userId,
                            foodList.get(0).getRestaurantLogoLink(),
                            foodList.get(0).getRestaurantName()
                    );
                    OrderController.updateOrder(order);
                    Toast.makeText(context, "Added to cart", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "You already added this item to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.setData(food.getFoodName(), food.getPrice(), food.getRestaurantName(), food.getFoodDesctiption());

        return convertView;
    }

    private class FoodViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        TextView restaurantTextView;
        TextView descriptionTextView;
        CircleImageView image;
        TextView addToCart;
        void setData(String name, String price, String res, String desc) {
            nameTextView.setText(name);
            priceTextView.setText(price);
            restaurantTextView.setText(res);
            descriptionTextView.setText(desc);

        }
    }
}
