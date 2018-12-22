package app.repbulic.order.orderrepublic.iu.main.orders;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.repbulic.order.orderrepublic.MainActivity;
import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.controllers.OrderController;
import butterknife.ButterKnife;

public class OrdersFragment extends Fragment {

  private RecyclerView ordersRecyclerView;

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

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_orders, container, false);
    ordersRecyclerView= view.findViewById(R.id.orders__orders_fragment);

    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    OrderController.readPlacedOrders(MainActivity.userId, ordersRecyclerView, getContext());
  }
}
