package app.repbulic.order.orderrepublic.iu.owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.FoodController;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateFood extends AppCompatActivity {

    private String restName;
    private String restLogoLink;

    @BindView(R.id.description__create_food)
    EditText description;

    @BindView(R.id.price__create_food)
    EditText price;
    @BindView(R.id.food_name__create_food)
    EditText name;
    @BindView(R.id.picture_link__create_food)
    EditText picLink;
    @BindView(R.id.categories_spinner)
    Spinner category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);
        restName = getIntent().getStringExtra("restName");
        restLogoLink = getIntent().getStringExtra("restLogoLink");
        ButterKnife.bind(this);

    }

    public void submit_button_clicked(View view) {

        Food food = new Food();
        food.setRestaurantLogoLink(restLogoLink);
        food.setRestaurantName(restName);
        food.setPrice(price.getText().toString());
        food.setCategory(category.getSelectedItem().toString());
        food.setPictureLink(picLink.getText().toString());
        food.setFoodDesctiption(description.getText().toString());
        food.setFoodId("");
        food.setFoodName(name.getText().toString());
        food.setRating(0.0);

        FoodController.createFood(food);
        finish();
    }

}
