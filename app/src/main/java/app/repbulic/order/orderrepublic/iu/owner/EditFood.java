package app.repbulic.order.orderrepublic.iu.owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.FoodController;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditFood extends AppCompatActivity {

    @BindView(R.id.categories_spinner__edit_food)
    Spinner category;
    @BindView(R.id.description__edit_food)
    EditText description;
    @BindView(R.id.picture_link__edit_food)
    EditText picLink;
    @BindView(R.id.price__edit_food)
    EditText price;
    @BindView(R.id.food_name__edit_food)
    EditText name;

    String foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
        ButterKnife.bind(this);
        foodId = getIntent().getStringExtra("foodId");
        ArrayList<View> widgets = new ArrayList<>();
        widgets.add(description);
        widgets.add(picLink);
        widgets.add(price);
        widgets.add(name);
        FoodController.readFood(foodId, widgets);
    }

    public void change_button_clicked__edit_food(View view) {
        ArrayList<String> changed = new ArrayList<>();
        changed.add(foodId);
        changed.add(category.getSelectedItem().toString());
        changed.add(description.getText().toString());
        changed.add(picLink.getText().toString());
        changed.add(price.getText().toString());
        changed.add(name.getText().toString());
        FoodController.updateFood(changed);
        finish();
    }
}
