package app.repbulic.order.orderrepublic.iu.main.orders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.MainActivity;
import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.adapters.CartRestaurantsAdapter;
import app.repbulic.order.orderrepublic.controllers.OrderController;
import app.repbulic.order.orderrepublic.controllers.UserController;
import app.repbulic.order.orderrepublic.models.Order;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartOrdersActivity extends AppCompatActivity {

  private RecyclerView recyclerViewCartRes;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart_orders);
    recyclerViewCartRes = findViewById(R.id.cart_restaurants_rv);
    OrderController.readOrderRestaurants(MainActivity.userId, recyclerViewCartRes, getApplicationContext());
  }


}
