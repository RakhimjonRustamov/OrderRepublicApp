package app.repbulic.order.orderrepublic.iu.owner.owner_fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.FoodController;
import app.repbulic.order.orderrepublic.adapters.RecyclerViewAdapter;
import app.repbulic.order.orderrepublic.iu.owner.CreateFood;
import app.repbulic.order.orderrepublic.models.Food;


public class OwnerMenuFragment extends Fragment {

    private Button addFoodBtn;
    private RecyclerView menuRecyclerView;
    private String userId;
    private String restName;

    public OwnerMenuFragment() {
        // Required empty public constructor
    }

    public static OwnerMenuFragment newInstance() {
        OwnerMenuFragment fragment = new OwnerMenuFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //sent userId from main activity
        userId = getArguments().getString("userId");
        restName = getArguments().getString("restName");
        //The binding process in a fragment is a little bit different, because its view is inflated manually.
        View root = inflater.inflate(R.layout.fragment_owner_menu, container, false);

        addFoodBtn=root.findViewById(R.id.add_new_food_btn);
        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createFoodActivity = new Intent(getContext(), CreateFood.class);
                createFoodActivity.putExtra("restName", restName);
                createFoodActivity.putExtra("restLogoLink", "https://i0.wp.com/choparpizza.uz/wp-content/uploads/2017/06/chopar.png");
                startActivity(createFoodActivity);
            }
        });
        menuRecyclerView = root.findViewById(R.id.menu__owner);

        menuRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));

        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Food> foods = new ArrayList<>();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), foods, "");
        menuRecyclerView.setAdapter(adapter);

        FoodController.readFoodsByRestaurant(restName, menuRecyclerView, getContext());

    }

}
