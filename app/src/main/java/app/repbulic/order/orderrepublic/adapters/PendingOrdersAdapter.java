package app.repbulic.order.orderrepublic.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.OrderController;
import app.repbulic.order.orderrepublic.models.Order;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingOrdersAdapter extends RecyclerView.Adapter<PendingOrdersAdapter.ViewHolder> {
    ArrayList<Order> orders;
    private Context rcontext;


    public PendingOrdersAdapter(ArrayList<Order> orders, Context rcontext) {
        this.orders = orders;
        this.rcontext = rcontext;
    }

    @Override
    public PendingOrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(rcontext);
        View view = layoutInflater.inflate(R.layout.pending_orders_fragment_item, parent, false);
        return new PendingOrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PendingOrdersAdapter.ViewHolder holder, int position) {
        holder.cardView.setTag(position);
        holder.orderId.setText(orders.get(position).getOrderId());
        holder.title.setText(orders.get(position).getOrderTitle());
        holder.totalPrice.setText(orders.get(position).getTotalPrice());
        holder.status.setText(orders.get(position).getStatus());
        Glide.with(rcontext)
                .asBitmap()
                .load("https://static.thenounproject.com/png/1485921-200.png")
                .into(holder.process_icon);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = (int) v.getTag();
                AlertDialog.Builder dialog = new AlertDialog.Builder(rcontext);
                dialog.setTitle("Status of order");
                dialog.setMessage("Set status of order\n"+orders.get(pos).getOrderId());

                dialog.setPositiveButton("Started", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        OrderController.updateOrderStatus(orders.get(pos).getOrderId(), "in_process");
                    }
                });

                dialog.setNegativeButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        OrderController.updateOrderStatus(orders.get(pos).getOrderId(), "done");
                    }
                });

                AlertDialog alert = dialog.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.process_icon__pending_orders)
        ImageView process_icon;
        @BindView(R.id.order_title__pending_orders)
        TextView title;
        @BindView(R.id.total__pending_orders)
        TextView totalPrice;
        @BindView(R.id.orderId__pending_orders)
        TextView orderId;
        @BindView(R.id.status__pending_orders)
        TextView status;
        @BindView(R.id.cardview__pending_orders)
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
