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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.iu.main.orders.CartSingleRestaurantOrder;
import app.repbulic.order.orderrepublic.models.Order;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CartRestaurantsAdapter extends RecyclerView.Adapter<CartRestaurantsAdapter.ViewHolder> {
    private Context rcontext;
    private ArrayList<String> restaurantNameList;
    private ArrayList<String> restPicLinks;
    private String restaurantName;
    private String restLogoLink;
    private ArrayList<Order> orders;

    public CartRestaurantsAdapter(Context context, ArrayList<String> restaurantNameList, ArrayList<String> restPicLinks, ArrayList<Order> orders) {
        this.restaurantNameList = restaurantNameList;
        this.restPicLinks = restPicLinks;
        this.rcontext = context;
        this.orders = orders;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(rcontext);
        View view = layoutInflater.inflate(R.layout.cart_restaurants_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cartResCardview.setTag(position);
        restaurantName = restaurantNameList.get(position);
        restLogoLink = restPicLinks.get(position);
        Glide.with(rcontext)
                .asBitmap()
                .load(restLogoLink)
                .into(holder.circleImageViewLogo);
        holder.cartRestaurant.setText(restaurantName);
        holder.cartResCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                Intent intent = new Intent(rcontext, CartSingleRestaurantOrder.class);
                intent.putExtra("order", orders.get(position));
                rcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cart_restaurant_logo)
        CircleImageView circleImageViewLogo;
        @BindView(R.id.cart_restaurant_name)
        TextView cartRestaurant;
        @BindView(R.id.card_restaurants_list)
        CardView cartResCardview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
