package app.repbulic.order.orderrepublic.iu.main.orders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.adapters.CartSingleRestaurantAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartSingleRestaurantOrder extends AppCompatActivity {
  @BindView(R.id.cart_recycler_view)
  RecyclerView recyclerViewCartRes;
  private List<String> list;
  int please =0;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart_single_restaurant_order);
    ButterKnife.bind(this);
    list= new ArrayList<>();
    list.add("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
    list.add("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
    list.add("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
    list.add("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");

    CartSingleRestaurantAdapter adapter = new CartSingleRestaurantAdapter(this, list);
    recyclerViewCartRes.setAdapter(adapter);
    final LinearLayoutManager layoutManager = new  LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerViewCartRes.setLayoutManager(layoutManager);

    CartSingleRestaurantAdapter.setOnItemClickListener(new CartSingleRestaurantAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(int position, List<String> returnList, int elegantNumber) {
        please = elegantNumber;
      }
      });
  }
}
