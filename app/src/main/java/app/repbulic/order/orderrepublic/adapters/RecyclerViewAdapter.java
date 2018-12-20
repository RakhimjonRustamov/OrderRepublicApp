package app.repbulic.order.orderrepublic.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.iu.main.menu.FoodDetailActivity;
import app.repbulic.order.orderrepublic.iu.owner.OwnerSideFoodDetail;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private Context rcontext;
    private List<Food> foodList;
    private String activity;

    public RecyclerViewAdapter(Context context, List<Food> list, String activity) {
        this.foodList = list;
        this.rcontext = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(rcontext);
        View view;
        if (activity.equals(""))
            view = layoutInflater.inflate(R.layout.owner_menu_list_item, parent, false);
        else
            view = layoutInflater.inflate(R.layout.list_item, parent, false);
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
                if (activity.equals("")) {
                    Intent intent = new Intent(rcontext, OwnerSideFoodDetail.class);
                    intent.putExtra("food", foodList.get(position));
                    rcontext.startActivity(intent);
                    Toast.makeText(rcontext, "Food Name: " + foodList.get(position).getFoodName(), Toast.LENGTH_LONG).show();

                } else {
                    Intent intent = new Intent(rcontext, FoodDetailActivity.class);
                    intent.putExtra("food", foodList.get(position));
                    rcontext.startActivity(intent);
                    Toast.makeText(rcontext, "Food Name: " + foodList.get(position).getFoodName(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.food_image)
        CircleImageView circleImageView;
        @BindView(R.id.food_name)
        TextView foodName;
        @BindView(R.id.restaurantName)
        TextView restaurantName;
        @BindView(R.id.food_price)
        TextView foodPrice;
        @BindView(R.id.food_cardview)
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

