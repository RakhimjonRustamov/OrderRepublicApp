package app.repbulic.order.orderrepublic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

import app.repbulic.order.orderrepublic.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CartSingleRestaurantAdapter extends RecyclerView.Adapter<CartSingleRestaurantAdapter.ViewHolder> {

  private Context rcontext;
  private int quantity=12;
  private List<String> cartList;
  private static OnItemClickListener onItemClickListener;

  public CartSingleRestaurantAdapter(Context context, List<String> list) {
    this.cartList = list;
    this.rcontext = context;
  }
  public static void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    CartSingleRestaurantAdapter.onItemClickListener = onItemClickListener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(rcontext);
    View view = inflater.inflate(R.layout.cart_single_res, parent, false);
    return new ViewHolder(view);
  }


  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Glide.with(rcontext)
      .asBitmap()
      .load(cartList.get(position))
      .into(holder.singleImage);

    // single food price
    // top price is on top of single cart item  quantity * single price
    // food name
  }

  @Override
  public int getItemCount() {
    return cartList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements ElegantNumberButton.OnClickListener {
    @BindView(R.id.cart_delete_btn)
    TextView deleteCartOrder;
    @BindView(R.id.cart_single_image)
    CircleImageView singleImage;
    @BindView(R.id.cart_single_name)
    TextView singleName;
    @BindView(R.id.cart_single_price)
    TextView singlePrice;
    @BindView(R.id.cart_top_price)
    TextView singleTopPrice;
    @BindView(R.id.cart_elegant_btn) ElegantNumberButton elegantNumberButton;
    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
      elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
        @Override
        public void onClick(View view) {
          quantity =Integer.parseInt(elegantNumberButton.getNumber());
          onItemClickListener.onItemClick(getPosition(), cartList, quantity);
        }
      });
    }

    @Override
    public void onClick(View view) {

    }
  }

  public interface OnItemClickListener {
    void onItemClick(int position, List<String> returnList, int elegantNumber);
  }


}




