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

import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.iu.main.orders.CartSingleRestaurantOrder;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CartRestaurantsRAdapter extends RecyclerView.Adapter<CartRestaurantsRAdapter.ViewHolder> {
  private static final String TAG = "CartRecyclerViewAdapter";
  private Context rcontext;
  private List<String> restaurantNameList;
  private String restaurantName;

  public CartRestaurantsRAdapter(Context context, List<String> restaurantNameList) {
    this.restaurantNameList = restaurantNameList;
    this.rcontext = context;
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(rcontext);
    View view = layoutInflater.inflate(R.layout.cart_restaurants_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    restaurantName = restaurantNameList.get(position).toString();
    int iend = restaurantName.indexOf("{");
    int length = restaurantName.length();
    String resName = "";
    String linkLogo = "";
    if (iend != -1) {
      resName = restaurantName.substring(0, iend); //this will give restaurantName
      linkLogo = restaurantName.substring(iend + 1, length); // logo link
    }
    Log.d("num", "Message test" + resName + " " + linkLogo);

    Glide.with(rcontext)
      .asBitmap()
      .load(linkLogo)
      .into(holder.circleImageViewLogo);
    holder.cartRestaurant.setText(resName);
    holder.cartResCardview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // sending order list by the name of restaurant use variable
        // send resName and link Logo
        Intent intent = new Intent(rcontext, CartSingleRestaurantOrder.class);
        rcontext.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {return restaurantNameList.size();}

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
