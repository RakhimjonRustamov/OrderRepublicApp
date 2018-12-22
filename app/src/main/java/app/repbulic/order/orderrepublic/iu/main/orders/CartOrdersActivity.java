package app.repbulic.order.orderrepublic.iu.main.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
  @BindView(R.id.toolbar_cart)
  Toolbar toolbar;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart_orders);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setTitle("Cart Restaurant Orders");
    recyclerViewCartRes = findViewById(R.id.cart_restaurants_rv);
    OrderController.readOrderRestaurants(MainActivity.userId, recyclerViewCartRes, getApplicationContext());
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // todo: goto back activity from here

        Intent intent = new Intent(CartOrdersActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

}
