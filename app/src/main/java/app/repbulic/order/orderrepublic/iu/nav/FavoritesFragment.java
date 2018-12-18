package app.repbulic.order.orderrepublic.iu.nav;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.FavoritesController;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritesFragment extends Fragment {

    @BindView(R.id.favorites_list)
    ListView favoritesList;
    private String userId;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //sent userId from main activity
        userId = getArguments().getString("userId");
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

        FavoritesController.readFavorites(userId, favoritesList, getContext());

    }
}
