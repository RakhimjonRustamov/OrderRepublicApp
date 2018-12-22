package app.repbulic.order.orderrepublic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PlacedOrdersAdapter extends RecyclerView.Adapter<PlacedOrdersAdapter.ViewHolder> {
    private Context rcontext;
    private ArrayList<String> restaurantNameList;
    private ArrayList<String> restPicLinks;
    private ArrayList<String> statuses;
    private ArrayList<String> orderIds;

    private ArrayList<String> titles;
    private ArrayList<String> totals;



    public PlacedOrdersAdapter(Context context, ArrayList<String> restaurantNameList, ArrayList<String> restPicLinks, ArrayList<String> statuses, ArrayList<String>orderIds ,ArrayList<String>titles,ArrayList<String>totals) {
        this.restaurantNameList = restaurantNameList;
        this.restPicLinks = restPicLinks;
        this.rcontext = context;
        this.statuses = statuses;
        this.orderIds = orderIds;
        this.titles = titles;
        this.totals = totals;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(rcontext);
        View view = layoutInflater.inflate(R.layout.orders_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(rcontext)
                .asBitmap()
                .load(restPicLinks.get(position))
                .into(holder.circleImageViewLogo);
        holder.restaurantName.setText(restaurantNameList.get(position));
        holder.statusTextV.setText(statuses.get(position));
        holder.orderId.setText(orderIds.get(position));
        holder.title.setText(titles.get(position));
        holder.totalPrice.setText(totals.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurantNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cart_restaurant_logo__orders_fragment_item)
        CircleImageView circleImageViewLogo;
        @BindView(R.id.cart_restaurant_name__orders_fragment_item)
        TextView restaurantName;
        @BindView(R.id.status__orders_fragment_item)
        TextView statusTextV;
        @BindView(R.id.order_title__orders_fragment_item)
        TextView title;
        @BindView(R.id.total_price__orders_fragment_item)
        TextView totalPrice;
        @BindView(R.id.orderId__orders_fragment_item)
        TextView orderId;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

