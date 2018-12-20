package app.repbulic.order.orderrepublic.iu.main.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailActivity extends AppCompatActivity {
    //  private Toolbar toolbar;
    @BindView(R.id.elegant_text__food_detail)
    TextView elegantTextView;
    @BindView(R.id.toolbar__food_detail)
    Toolbar toolbar;
    @BindView(R.id.food_image__food_detail)
    ImageView foodImage;
    @BindView(R.id.desctiption__food_detail)
    TextView description;

    private String number;
    private ElegantNumberButton elegantNumberButton;
    double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);

        //
        Food food = (Food) getIntent().getSerializableExtra("food");


        Glide.with(getApplicationContext())
                .asBitmap()
                .load(food.getPictureLink())
                .into(foodImage);
        description.setText(food.getFoodDesctiption());


        //
        //toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        elegantNumberButton = (ElegantNumberButton) findViewById(R.id.elegant_button__food_detail);
        elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = elegantNumberButton.getNumber();
            }
        });




   /*
    elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
      @Override
      public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
          int quantity = Integer.parseInt(newValue);
          totalPrice = quantity * 500.0;
          elegantTextView.setText(""+totalPrice);
         }
    });*/


    }
}
