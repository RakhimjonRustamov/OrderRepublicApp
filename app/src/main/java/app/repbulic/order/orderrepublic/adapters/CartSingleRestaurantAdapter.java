package app.repbulic.order.orderrepublic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.DecimalFormat;
import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CartSingleRestaurantAdapter extends RecyclerView.Adapter<CartSingleRestaurantAdapter.ViewHolder> {

  private Context rcontext;
  private double foodPrice;
  private List<Food> cartList;
  private static OnItemClickListener onItemClickListener;

  public CartSingleRestaurantAdapter(Context context, List<Food> list) {
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
  public void onBindViewHolder(final ViewHolder holder, int position) {
    final Food food = cartList.get(position);
    Glide.with(rcontext)
      .asBitmap()
      .load(food.getPictureLink())
      .into(holder.singleImage);
    final int price = Integer.parseInt(food.getPrice());
    holder.singlePrice.setText(String.valueOf(food.getPrice()));
    holder.singleTopPrice.setText(String.valueOf(price));
    holder.singleName.setText(food.getFoodName());
    holder.elegantNumberButton.setNumber("1");
    holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
      @Override
      public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
        DecimalFormat newDf = new DecimalFormat("#,###");
        String newFormat;
        if (price < 1000) {
          newDf = new DecimalFormat("###");
        }
        if (price > 1000 && price < 10000) {
          newDf = new DecimalFormat("#,###");
        }
        if (price >= 10000) {
          newDf = new DecimalFormat("##,###");
        }
        newFormat = newDf.format(price * newValue);
        cartList.get(holder.getAdapterPosition()).setPrice(Integer.toString(price * newValue));
        holder.singleTopPrice.setText(newFormat);
        onItemClickListener.calculateTotal(cartList);
      }
    });

    holder.deleteCartOrder.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        deleteFromCart(food.getFoodId(), holder.getAdapterPosition());
      }
    });
  }

  private void deleteFromCart(String foodId, int position){
    onItemClickListener.itemDeleted(foodId);
    cartList.remove(position);
    onItemClickListener.calculateTotal(cartList);
    notifyItemRemoved(position);
  }

  @Override
  public int getItemCount() {
    return cartList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
    @BindView(R.id.elegant_btn)
    ElegantNumberButton elegantNumberButton;


    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      deleteCartOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    }
  }

  public interface OnItemClickListener {
    void calculateTotal(List<Food> returnList);
    void itemDeleted(String foodId);
  }

}




