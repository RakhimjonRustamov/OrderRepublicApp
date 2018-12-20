package app.repbulic.order.orderrepublic.iu.owner.owner_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.repbulic.order.orderrepublic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoneOrdersFragment extends Fragment {


    public DoneOrdersFragment() {
        // Required empty public constructor
    }

    public static DoneOrdersFragment newInstance() {
        DoneOrdersFragment fragment = new DoneOrdersFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_done_orders, container, false);
    }

}
