package app.repbulic.order.orderrepublic.iu.main.menu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.MainActivity;
import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.FavoritesController;
import app.repbulic.order.orderrepublic.controllers.OrderController;
import app.repbulic.order.orderrepublic.models.Food;
import app.repbulic.order.orderrepublic.models.Order;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailActivity extends AppCompatActivity implements View.OnClickListener {
    //  private Toolbar toolbar;
    @BindView(R.id.toolbar__food_detail)
    Toolbar toolbar;
    @BindView(R.id.food_image__food_detail)
    ImageView foodImage;
    @BindView(R.id.desctiption__food_detail)
    TextView description;
    @BindView(R.id.price__food_detail)
    TextView
            priceFood;
    @BindView(R.id.restaurantName__food_detail)
    TextView restaurantName;
    @BindView(R.id.rating_info__food_detail)
    TextView ratingInfo;
    @BindView(R.id.addTocart__food_detail)
    Button addToCartButton;
    @BindView(R.id.favButton__food_detail)
    FloatingActionButton favoriteButton;

    private Food food;
    private boolean added = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);
        // getting single item data
        food = (Food) getIntent().getSerializableExtra("food");
        food.logger();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setSupportActionBar(toolbar);
        setFoodDetails();
        favoriteButton.setOnClickListener(this); // to add food to favorite list
        addToCartButton.setOnClickListener(this); // to add food to order cart

    }

    private void setFoodDetails() {
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(food.getPictureLink())
                .into(foodImage);
        description.setText(food.getFoodDesctiption());
        priceFood.setText(food.getPrice());
        restaurantName.setText(food.getRestaurantName());
        double rat = food.getRating();
        ratingInfo.setText("" + rat);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.favButton__food_detail:
                favoriteButton.setImageResource(R.drawable.ic_favorite);
                FavoritesController.updateFavorite(MainActivity.userId, food.getFoodId(), true);
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addTocart__food_detail:
                if (!added) {
                    added = true;
                    ArrayList<Integer> quantity = new ArrayList<>();
                    quantity.add(1);
                    ArrayList<Food> foods = new ArrayList<>();
                    foods.add(food);
                    Order order = new Order(
                            "",
                            "",
                            MainActivity.userEmail,
                            "",
                            "in_cart",
                            "",
                            foods,
                            quantity,
                            MainActivity.userId,
                            foods.get(0).getRestaurantLogoLink(),
                            foods.get(0).getRestaurantName()
                    );
                    OrderController.createOrder(order);
                    Toast.makeText(this, "Added to cart", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "You already added this item to cart", Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }
}
