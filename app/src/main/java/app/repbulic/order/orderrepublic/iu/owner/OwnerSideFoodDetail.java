package app.repbulic.order.orderrepublic.iu.owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.FoodController;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OwnerSideFoodDetail extends AppCompatActivity {

    @BindView(R.id.toolbar__food_detail_owner_side)
    Toolbar toolbar;
    @BindView(R.id.food_image__food_detail_owner_side)
    ImageView foodImage;
    @BindView(R.id.desctiption__food_detail_owner_side)
    TextView description;
    @BindView(R.id.price__owner_side)
    TextView price;
    @BindView(R.id.category__food_detail_owner_side)
    TextView category;

    private String foodId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_side_food_detail);
        ButterKnife.bind(this);

        Food food = (Food) getIntent().getSerializableExtra("food");
        foodId = food.getFoodId();
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(food.getPictureLink())
                .into(foodImage);
        description.setText(food.getFoodDesctiption());
        toolbar.setTitle(food.getFoodName());
        price.setText(food.getPrice());
        category.setText(food.getCategory());


    }

    public void delete__owner_side(View view) {
        FoodController.deleteFood(foodId);
        finish();
    }

    public void edit_food__owner_side(View view) {
        Intent editFood=new Intent(this, EditFood.class);
        editFood.putExtra("foodId", foodId);
        startActivity(editFood);

    }
}
