package app.repbulic.order.orderrepublic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import app.repbulic.order.orderrepublic.iu.main.MenuFragment;
import app.repbulic.order.orderrepublic.iu.main.OrdersFragment;
import app.repbulic.order.orderrepublic.iu.nav.AboutActivity;
import app.repbulic.order.orderrepublic.iu.nav.FavoritesActivity;
import app.repbulic.order.orderrepublic.iu.nav.ProfileActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.auth.FirebaseAuth;
import app.repbulic.order.orderrepublic.authentication.LoginActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment= MenuFragment.newInstance();
        int id = item.getItemId();

        if (id == R.id.nav_favorites) {
            startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        } else if (id == R.id.nav_orders) {
            fragment= OrdersFragment.newInstance();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.place_holder, fragment) .commit();
        return true;
    }
}
