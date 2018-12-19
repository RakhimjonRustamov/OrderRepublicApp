package app.repbulic.order.orderrepublic.iu.main.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.FoodController;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.menu_rv)
    RecyclerView recyclerView;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = new Intent();
        categoryName = getIntent().getStringExtra("category_name");
        ButterKnife.bind(this);
        initMenu();
    }

    private void initMenu() {
        FoodController.readFoodsByCategory(categoryName, recyclerView, getApplicationContext());

    }

}
