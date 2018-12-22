package app.repbulic.order.orderrepublic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.models.Order;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DoneOrdersAdapter extends RecyclerView.Adapter<DoneOrdersAdapter.ViewHolder> {
    ArrayList<Order> orders;
    private Context rcontext;


    public DoneOrdersAdapter(ArrayList<Order> orders, Context rcontext) {
        this.orders = orders;
        this.rcontext = rcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(rcontext);
        View view = layoutInflater.inflate(R.layout.done_orders_fragment_item, parent, false);
        return new DoneOrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(rcontext)
                .asBitmap()
                .load("https://cdn2.iconfinder.com/data/icons/business-office-14/256/5-512.png")
                .into(holder.processImage);
        holder.orderId.setText(orders.get(position).getOrderId());
        holder.title.setText(orders.get(position).getOrderTitle());
        holder.totalPrice.setText(orders.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.process_icon__done_orders)
        ImageView processImage;
        @BindView(R.id.order_title__done_orders)
        TextView title;
        @BindView(R.id.total__done_orders)
        TextView totalPrice;
        @BindView(R.id.orderId__done_orders)
        TextView orderId;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
