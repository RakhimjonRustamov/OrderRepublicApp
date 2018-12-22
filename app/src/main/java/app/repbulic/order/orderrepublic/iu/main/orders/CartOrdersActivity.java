package app.repbulic.order.orderrepublic.iu.main.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.MainActivity;
import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.adapters.CartRestaurantsRAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartOrdersActivity extends AppCompatActivity {
  @BindView(R.id.cart_restaurants_rv)
  RecyclerView recyclerViewCartRes;
  @BindView(R.id.toolbar)
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
    final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerViewCartRes.setLayoutManager(layoutManager);
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
