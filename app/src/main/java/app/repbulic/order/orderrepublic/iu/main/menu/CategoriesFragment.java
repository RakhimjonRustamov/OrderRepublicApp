package app.repbulic.order.orderrepublic.iu.main.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import app.repbulic.order.orderrepublic.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesFragment extends Fragment implements View.OnClickListener {
  @BindView(R.id.imageSlider)
  SliderLayout sliderLayout;
  @BindView(R.id.category_pizza__menu)
  CardView categoryPizza;
  @BindView(R.id.category_burgers__menu)
  CardView categoryBurgers;
  @BindView(R.id.category_coffee__menu)
  CardView categoryCoffee;
  @BindView(R.id.category_deserts__menu)
  CardView categoryDeserts;
  @BindView(R.id.category_national__menu)
  CardView categoryNationalFood;
  @BindView(R.id.category_sushi__menu)
  CardView categorySushi;
  @BindView(R.id.category_drinks__menu)
  CardView categoryDrinks;
  @BindView(R.id.category_entree__menu)
  CardView categoryEntree;
  @BindView(R.id.category_fish_sea_food__menu)
  CardView categorySeafood;


  private String choosenCategory;

  public CategoriesFragment() {
  }

  public static CategoriesFragment newInstance() {
    CategoriesFragment fragment = new CategoriesFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    //setUpListeners();
    sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
    sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :
    setSliderViews();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_categories, container, false);
    ButterKnife.bind(this, view);
    setUpListeners();
    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.category_national__menu:
        choosenCategory="national";
        break;
      case R.id.category_burgers__menu:
        choosenCategory="burgers";
        break;
      case R.id.category_pizza__menu:
        choosenCategory="pizza";
        break;
      case R.id.category_coffee__menu:
        choosenCategory="coffee";
        break;
      case R.id.category_drinks__menu:
        choosenCategory="drinks";
        break;
      case R.id.category_sushi__menu:
        choosenCategory="sushi";
        break;
      case R.id.category_fish_sea_food__menu:
        choosenCategory="fish";
        break;
      case R.id.category_entree__menu:
        choosenCategory="entree";
        break;
    }
    Intent intent = new Intent(getContext(), MenuActivity.class);
    intent.putExtra("category_name", choosenCategory);
    startActivity(intent);
  }

  private void setSliderViews() {
    for (int i = 0; i <= 3; i++) {
      SliderView sliderView = new SliderView(getContext());
      switch (i) {
        case 0:
          sliderView.setImageUrl("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
          break;
        case 1:
          sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
          break;
        case 2:
          sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
          break;
        case 3:
          sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
          break;
      }

      sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
      sliderView.setDescription("setDescription " + (i + 1));
      final int finalI = i;
      sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
        @Override
        public void onSliderClick(SliderView sliderView) {
          Toast.makeText(getActivity(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
        }
      });
      sliderLayout.addSliderView(sliderView);
    }
  }

  private void setUpListeners() {
    categoryPizza.setOnClickListener(this);
    categoryBurgers.setOnClickListener(this);
    categoryCoffee.setOnClickListener(this);
    categoryDeserts.setOnClickListener(this);
    categoryNationalFood.setOnClickListener(this);
    categorySeafood.setOnClickListener(this);
    categorySushi.setOnClickListener(this);
    categoryEntree.setOnClickListener(this);
    categoryDrinks.setOnClickListener(this);
  }


}
