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

import com.bumptech.glide.Glide;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailActivity extends AppCompatActivity implements View.OnClickListener {
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
  private String orderQuantity;
  private Food food;

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
    toolbar.setTitle(food.getFoodName());
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
        // send food object to inform it is in favorite list
        Log.d("check", "Favorite Button is working");
        break;
      case R.id.addTocart__food_detail:
        // send food object to cart
        Log.d("check", "Cart Button is working");
        break;
    }

  }
}
