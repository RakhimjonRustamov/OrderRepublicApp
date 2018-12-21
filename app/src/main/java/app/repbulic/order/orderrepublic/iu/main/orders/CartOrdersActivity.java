package app.repbulic.order.orderrepublic.iu.main.orders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.adapters.CartRestaurantsRAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartOrdersActivity extends AppCompatActivity {
  @BindView(R.id.cart_restaurants_rv)
  RecyclerView recyclerViewCartRes;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart_orders);
    ButterKnife.bind(this);
    getCartRestautantList();
  }

  private void getCartRestautantList() {
    // Ravshan choose just taking name and logo name+ "{"+logolink
    // or Fetch the whole object and pass
    ArrayList<String> cartRestaurantsList = new ArrayList<>();
    String rayhon = "Rayhon{https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260";
    cartRestaurantsList.add(rayhon);
    CartRestaurantsRAdapter adapter = new CartRestaurantsRAdapter(this, cartRestaurantsList);
    recyclerViewCartRes.setAdapter(adapter);
    final LinearLayoutManager layoutManager = new  LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerViewCartRes.setLayoutManager(layoutManager);
  }


}
