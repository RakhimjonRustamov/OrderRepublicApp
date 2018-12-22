package app.repbulic.order.orderrepublic.iu.main.orders;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.repbulic.order.orderrepublic.R;

public class OrdersFragment extends Fragment {
  public OrdersFragment() {
    // Required empty public constructor
  }

  // TODO: Rename and change types and number of parameters
  public static OrdersFragment newInstance() {
    OrdersFragment fragment = new OrdersFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Toast.makeText(getActivity(), "INSIDE ORDERS FRAGMENT", Toast.LENGTH_LONG).show();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_orders, container, false);
  }

  public void onButtonPressed(Uri uri) {
  }


}
