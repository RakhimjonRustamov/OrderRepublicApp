package app.repbulic.order.orderrepublic.iu.main.menu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private static final String TAG="RecyclerViewAdapter";
  private Context rcontext;
  private List<Food> foodList;
  RecyclerViewAdapter(Context context, List<Food> list)
  {
    this.foodList=list;
    this.rcontext=context;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(rcontext);
    View view = layoutInflater.inflate(R.layout.list_item, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    Log.d(TAG, "OnBindViewHolder message");

    Glide.with(rcontext)
         .asBitmap()
         .load(foodList.get(position).getPictureLink())
         .into(holder.circleImageView);
    holder.foodName.setText(foodList.get(position).getFoodName());
    holder.foodPrice.setText(foodList.get(position).getPrice());
    holder.restaurantName.setText(foodList.get(position).getRestaurantName());
    holder.cardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(rcontext, FoodDetailActivity.class);
        rcontext.startActivity(intent);
        Toast.makeText(rcontext, "Food Name"+foodList.get(position).getFoodName(), Toast.LENGTH_LONG).show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return foodList.size();
  }


  public class ViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.food_image)ImageView circleImageView;
    @BindView(R.id.food_name) TextView foodName;
    @BindView(R.id.restaurantName) TextView restaurantName;
    @BindView(R.id.food_price)TextView foodPrice;
    @BindView(R.id.food_cardview)
    CardView cardView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
     }
  }
}

