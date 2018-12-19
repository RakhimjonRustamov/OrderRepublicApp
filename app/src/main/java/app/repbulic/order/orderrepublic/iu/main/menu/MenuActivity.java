package app.repbulic.order.orderrepublic.iu.main.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {
  @BindView(R.id.menu_rv) RecyclerView recyclerView;
  private static final String TAG = "MenuActivity";
  private List<Food> foodList = new ArrayList<>();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);
    ButterKnife.bind(this);
    Log.d(TAG, "OnCreate started");
    initMenu();
  }

  private void initMenu() {
    String id = "0";
    String name = "Food";
    String description = "Description";
    String price = "price";
    String picture = "picture";
    String categoryId = "catID";
    String restMenu = "Restmenu";
    String restLogo = "RestLogo";

    double vote;

    for (int i=1;i<11;i++){
      id=""+i;
      name="Sandwich";
      description=""+i;
      price=""+1200;
      picture="http://www.loook.uz/uploads/thumbs/store/product/190x150_4adaa8dbda3eb6eabebef0c5ecd21f1b.png";
      restMenu="Barley and Salt Restauran";
      restLogo="Barley and Salt Restaurant";
      categoryId=""+i;
      vote=0.0;
      Food food = new Food(id,name,description,price,picture,categoryId, vote,restMenu,restLogo);
      foodList.add(food);
    }
    initRecyclerView();
  }

  private void initRecyclerView(){
    Log.d(TAG, "initRecyclerView started");
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, foodList);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
  }
}
