package app.repbulic.order.orderrepublic.iu.owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OwnerSideFoodDetail extends AppCompatActivity {
    @BindView(R.id.elegant_text__food_detail_owner_side)
    TextView elegantTextView;
    @BindView(R.id.toolbar__food_detail_owner_side)
    Toolbar toolbar;
    @BindView(R.id.food_image__food_detail_owner_side)
    ImageView foodImage;
    @BindView(R.id.desctiption__food_detail_owner_side)
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_side_food_detail);
        ButterKnife.bind(this);

        Food food = (Food) getIntent().getSerializableExtra("food");
//        food.logger();
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(food.getPictureLink())
                .into(foodImage);
        description.setText(food.getFoodDesctiption());


        //
        //toolbar = findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }

//        elegantNumberButton = (ElegantNumberButton) findViewById(R.id.elegant_button__food_detail);
//        elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = elegantNumberButton.getNumber();
//            }
//        });

    }
}
