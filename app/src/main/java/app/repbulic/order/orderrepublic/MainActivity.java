package app.repbulic.order.orderrepublic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import app.repbulic.order.orderrepublic.authentication.LoginActivity;
import app.repbulic.order.orderrepublic.iu.main.RestaurantsFragment;
import app.repbulic.order.orderrepublic.iu.main.menu.CategoriesFragment;
import app.repbulic.order.orderrepublic.iu.main.menu.MenuActivity;
import app.repbulic.order.orderrepublic.iu.main.orders.CartOrdersActivity;
import app.repbulic.order.orderrepublic.iu.main.orders.OrdersFragment;
import app.repbulic.order.orderrepublic.iu.nav.FavoritesFragment;
import app.repbulic.order.orderrepublic.iu.nav.ProfileFragment;
import app.repbulic.order.orderrepublic.models.User;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.cart__main)
    ImageView clickableCartIcon;

    private FragmentManager fragmentManager;
    private Fragment fragment;
    public static String userEmail = "mail@mail.ru";
    public static String userId = "-LTq1uzUTmvBLkR1H-Cq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        User u = (User) getIntent().getSerializableExtra("user");

        if(u!=null) {
            userId = u.getUserId();
            userEmail = u.getEmail();
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        fragmentManager = getSupportFragmentManager();
        navigationView.setNavigationItemSelectedListener(this);
        clickableCartIcon.setOnClickListener(this);
        setUpApp();
        if (savedInstanceState == null) {
            bottomNavigation.setSelectedItemId(R.id.action_menu); // change to whichever id should be default
        }

    }

    private void setUpApp() {
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_order:
                        fragment = OrdersFragment.newInstance();
                        break;
                    case R.id.action_menu:
                        fragment = CategoriesFragment.newInstance();
                        break;
                    case R.id.action_restaurant:
                        fragment = RestaurantsFragment.newInstance();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.place_holder, fragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        fragment = CategoriesFragment.newInstance();
        int id = item.getItemId();
        Bundle data = new Bundle();
        data.putString("userId", userId);
        if (id == R.id.nav_favorites) {
            fragment = new FavoritesFragment();
            fragment.setArguments(data);
        } else if (id == R.id.nav_profile) {
            fragment = new ProfileFragment();
            fragment.setArguments(data);
        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        fragmentManager.beginTransaction().replace(R.id.place_holder, fragment).commit();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart__main:
                Intent intent = new Intent(this, CartOrdersActivity.class);
                startActivity(intent);
                break;
        }
    }
}
