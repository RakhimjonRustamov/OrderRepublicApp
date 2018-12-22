package app.repbulic.order.orderrepublic.iu.main.orders;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.adapters.CartSingleRestaurantAdapter;
import app.repbulic.order.orderrepublic.authentication.LoginActivity;
import app.repbulic.order.orderrepublic.controllers.OrderController;
import app.repbulic.order.orderrepublic.controllers.UserController;
import app.repbulic.order.orderrepublic.models.Food;
import app.repbulic.order.orderrepublic.models.Order;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartSingleRestaurantOrder extends Activity implements View.OnClickListener, CartSingleRestaurantAdapter.OnItemClickListener {
    @BindView(R.id.cart_recycler_view)
    RecyclerView recyclerViewCartRes;
    @BindView(R.id.finish_order_btn)
    Button finishOrderBtn;
    @BindView(R.id.cart_total_price)
    TextView totalPrice;
    public static Order order;
    private ArrayList<Food> list;
    private String orderAddress;
    private String orderTitle;
    private String orderDetail;
    private int total = 0;
    public static boolean deleteOrder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_single_restaurant_order);
        ButterKnife.bind(this);

        order = (Order) getIntent().getSerializableExtra("order");
        list = order.getFoods();

        finishOrderBtn.setOnClickListener(this);
        CartSingleRestaurantAdapter adapter = new CartSingleRestaurantAdapter(this, list);
        recyclerViewCartRes.setAdapter(adapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCartRes.setLayoutManager(layoutManager);

        // Calculate Given Price
        calculateTotal(list);
        CartSingleRestaurantAdapter.setOnItemClickListener(this);
    }

    // calculate Total Price
    public void calculateTotal(ArrayList<Food> returnList) {
        total = 0;
        if (!returnList.isEmpty()) {
            for (Food food : returnList) {
                int price = Integer.parseInt(food.getPrice());
                total += price;
                totalPrice.setText(Integer.toString(total));
                order.setTotalPrice(String.valueOf(total));
            }
        } else {
            totalPrice.setText("0");


        }
    }


    // Delete From Order list
    @Override
    public void itemDeleted(String foodId, int position) {
        // the operation based on food Id to delete it
        order.getFoods().set(position, null);

    }

    // Additional info for order, when order button clicked
    @Override
    public void onClick(View v) {

        if (order.getFoods().size() == 0) {
            Toast.makeText(this, "This order is empty", Toast.LENGTH_SHORT).show();
            OrderController.deleteOrder(order.getOrderId());
            finish();
        } else {
            final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_layout, null);

            final EditText dialogTitle = dialogView.findViewById(R.id.dialog_order_title);
            final EditText dialogAddress = dialogView.findViewById(R.id.dialog_order_address);
            final EditText dialogDetail = dialogView.findViewById(R.id.dialog_order_detail);
            Button btnSubmit = dialogView.findViewById(R.id.buttonSubmit);
            Button btnCancel = dialogView.findViewById(R.id.buttonCancel);

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogBuilder.dismiss();
                }
            });
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    order.setAddress(dialogAddress.getText().toString());
                    order.setOrderTitle(dialogTitle.getText().toString());
                    order.setSpecialGuidelines(dialogDetail.getText().toString());
                    order.setStatus("placed");
                    dialogBuilder.dismiss();
                    Toast.makeText(CartSingleRestaurantOrder.this, "Your order is placed", Toast.LENGTH_SHORT).show();
                    OrderController.placeOrder(order);
                    finish();
                }
            });
            dialogBuilder.setView(dialogView);
            dialogBuilder.show();
        }
    }

}
