package app.repbulic.order.orderrepublic.iu.main.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import app.repbulic.order.orderrepublic.R;
import butterknife.ButterKnife;

public class FoodDetailActivity extends AppCompatActivity {
  private Toolbar toolbar;
  /*@BindView(R.id.elegant_text) TextView elegantTextView;
*/  private String number;
  private ElegantNumberButton elegantNumberButton;
  double totalPrice;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    setContentView(R.layout.activity_food_detail);
    toolbar =findViewById(R.id.toolbar);

    setSupportActionBar(toolbar);
    if(getSupportActionBar()!=null){
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    elegantNumberButton = (ElegantNumberButton) findViewById(R.id.elegant_button);
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
