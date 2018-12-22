package app.repbulic.order.orderrepublic.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.models.Food;

public class FoodAdapter extends ArrayAdapter {

  private ArrayList<Food> foods;

  public FoodAdapter(@NonNull Context context, @NonNull ArrayList<Food> foods) {
    super(context, R.layout.favorites_list_item);
    this.foods = foods;

  }


  @Override
  public int getCount() {
    return foods.size();
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    FoodViewHolder holder = new FoodViewHolder();

    Food food = foods.get(position);

    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).
        inflate(R.layout.favorites_list_item, parent, false);
    }

    holder.nameTextView = convertView.findViewById(R.id.food_name_fav);
    holder.priceTextView = convertView.findViewById(R.id.food_price_fav);
    holder.restaurantTextView = convertView.findViewById(R.id.restaurantName_fav);
    holder.descriptionTextView = convertView.findViewById(R.id.food_description_fav);

    holder.setData(food.getFoodName(), food.getPrice(), food.getRestaurantName(), food.getFoodDesctiption());
    return convertView;
  }

  private class FoodViewHolder {
    TextView nameTextView;
    TextView priceTextView;
    TextView restaurantTextView;
    TextView descriptionTextView;


    void setData(String name, String price, String res, String desc) {
      nameTextView.setText(name);
      priceTextView.setText(price);
      restaurantTextView.setText(res);
      descriptionTextView.setText(desc);
    }
  }
}
