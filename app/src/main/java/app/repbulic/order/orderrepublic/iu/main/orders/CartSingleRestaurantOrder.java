package app.repbulic.order.orderrepublic.iu.main.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.adapters.CartSingleRestaurantAdapter;
import app.repbulic.order.orderrepublic.models.Food;
import app.repbulic.order.orderrepublic.models.Order;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartSingleRestaurantOrder extends AppCompatActivity implements View.OnClickListener, CartSingleRestaurantAdapter.OnItemClickListener {
  @BindView(R.id.cart_recycler_view)
  RecyclerView recyclerViewCartRes;
  @BindView(R.id.finish_order_btn)
  Button finishOrderBtn;
  @BindView(R.id.cart_total_price)
  TextView totalPrice;
  @BindView(R.id.toolbar)
  Toolbar toolbar;
  private List<Order> list;
  private String orderAddress;
  private String orderTitle;
  private String orderDetail;
  private int total = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart_single_restaurant_order);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    // You need to send appropriate food list by single restaurant
    list = new ArrayList<>();
    list = Order.getDefaults();
    List<Food> foods = new ArrayList<>();
    foods = list.get(0).getFoods();
    //

    finishOrderBtn.setOnClickListener(this);
    CartSingleRestaurantAdapter adapter = new CartSingleRestaurantAdapter(this, foods);
    recyclerViewCartRes.setAdapter(adapter);
    final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerViewCartRes.setLayoutManager(layoutManager);
    // Calculate Given Price
    calculateTotal(foods);
    CartSingleRestaurantAdapter.setOnItemClickListener(this);
  }

  // calculate Total Price
  @Override
  public void calculateTotal(List<Food> returnList) {
    total = 0;
    if (!returnList.isEmpty()) {
      for (Food food : returnList) {
        int price = Integer.parseInt(food.getPrice());
        total += price;
        totalPrice.setText(Integer.toString(total));
      }
    }else{
      totalPrice.setText("0");
    }
  }

  // Delete From Order list
  @Override
  public void itemDeleted(String foodId) {
    // the operation based on food Id to delete it
    String check = foodId; //check with debugger
  }

  // Additional info for order
  @Override
  public void onClick(View v) {
    final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
    LayoutInflater inflater = this.getLayoutInflater();
    View dialogView = inflater.inflate(R.layout.dialog_layout, null);

    final EditText dialogTitle = (EditText) dialogView.findViewById(R.id.dialog_order_title);
    final EditText dialogAddress = (EditText) dialogView.findViewById(R.id.dialog_order_address);
    final EditText dialogDetail = (EditText) dialogView.findViewById(R.id.dialog_order_detail);
    Button btnSubmit = (Button) dialogView.findViewById(R.id.buttonSubmit);
    Button btnCancel = (Button) dialogView.findViewById(R.id.buttonCancel);

    btnCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialogBuilder.dismiss();
      }
    });
    btnSubmit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        orderAddress = dialogAddress.getText().toString();
        orderDetail = dialogDetail.getText().toString();
        orderTitle = dialogTitle.getText().toString();
        dialogBuilder.dismiss();
      }
    });
    dialogBuilder.setView(dialogView);
    dialogBuilder.show();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // todo: goto back activity from here

        Intent intent = new Intent(CartSingleRestaurantOrder.this, CartOrdersActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
