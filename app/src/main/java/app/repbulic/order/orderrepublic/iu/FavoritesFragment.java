package app.repbulic.order.orderrepublic.iu;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.repbulic.order.orderrepublic.MainActivity;
import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.adapters.FoodAdapter;
import app.repbulic.order.orderrepublic.controllers.FavoritesController;
import app.repbulic.order.orderrepublic.controllers.FoodController;
import app.repbulic.order.orderrepublic.models.Food;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritesFragment extends Fragment {
    @BindView(R.id.favorites_list)
    ListView favoritesList;
   // private ArrayList<String> favFoodIds;

    private String userId ="-LTq1uzUTmvBLkR1H-Cq";

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //The binding process in a fragment is a little bit different, because its view is inflated manually.
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        //So we have to specify WHERE we gonna find the views with @Bind annotation
        ButterKnife.bind(this, root);

        //Additionally if you have to use findViewByID() but you are tired to casting every view, you can use Butterknife.findViewBiId();
        favoritesList = ButterKnife.findById(root, R.id.favorites_list);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //favFoodIds = new ArrayList<>();
        FavoritesController.readFavorites(userId, favoritesList, getContext());

    }
}
