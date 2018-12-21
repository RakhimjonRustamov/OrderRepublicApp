package app.repbulic.order.orderrepublic.iu.owner.owner_fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.adapters.RecyclerViewAdapter;
import app.repbulic.order.orderrepublic.controllers.FoodController;
import app.repbulic.order.orderrepublic.models.Food;


public class PendingOrdersFragment extends Fragment {


    private RecyclerView pendingOrdersRecyclerView;
    private String restName;
    public PendingOrdersFragment() {
        // Required empty public constructor
    }

    public static PendingOrdersFragment newInstance() {
        PendingOrdersFragment fragment = new PendingOrdersFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restName = getArguments().getString("restName");
        //The binding process in a fragment is a little bit different, because its view is inflated manually.
        View root = inflater.inflate(R.layout.fragment_pending_orders, container, false);

        pendingOrdersRecyclerView = root.findViewById(R.id.pending_orders__owner);

        pendingOrdersRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false));

        return root;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Food> foods = new ArrayList<>();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), foods, "");
        pendingOrdersRecyclerView.setAdapter(adapter);

        FoodController.readFoodsByRestaurant(restName, pendingOrdersRecyclerView, getContext());

    }

}
