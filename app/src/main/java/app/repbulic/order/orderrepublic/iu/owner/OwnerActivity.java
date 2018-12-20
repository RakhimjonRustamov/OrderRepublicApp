package app.repbulic.order.orderrepublic.iu.owner;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import app.repbulic.order.orderrepublic.R;
import app.repbulic.order.orderrepublic.iu.owner.owner_fragments.DoneOrdersFragment;
import app.repbulic.order.orderrepublic.iu.owner.owner_fragments.OwnerMenuFragment;
import app.repbulic.order.orderrepublic.iu.owner.owner_fragments.PendingOrdersFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OwnerActivity extends AppCompatActivity  {

    @BindView(R.id.bottom_navigation_view__owner)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.toolbar__owner)
    Toolbar toolbar;

    private String userId ="-LTq1uzUTmvBLkR1H-Cq";
    private String restName ="Chopar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        ButterKnife.bind(this);
        setUpApp();
        if (savedInstanceState == null) {
            bottomNavigation.setSelectedItemId(R.id.action_menu_change); // change to whichever id should be default
        }
    }



    private void setUpApp() {
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_done:
                        fragment = DoneOrdersFragment.newInstance();
                        break;
                    case R.id.action_menu_change:
                        fragment = OwnerMenuFragment.newInstance();
                        break;
                    case R.id.action_pending:
                        fragment = PendingOrdersFragment.newInstance();
                        break;
                }
                Bundle data = new Bundle();
                data.putString("userId", userId);
                data.putString("restName", restName);
                fragment.setArguments(data);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.place_holder__owner, fragment).commit();
                return true;
            }
        });
    }
}
