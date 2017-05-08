package fr.unice.polytech.jcancela.tobeortohave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import fr.unice.polytech.jcancela.tobeortohave.fragment.FidelityCardFragment;
import fr.unice.polytech.jcancela.tobeortohave.fragment.GiftCardsFragment;
import fr.unice.polytech.jcancela.tobeortohave.fragment.HomeFragment;
import fr.unice.polytech.jcancela.tobeortohave.fragment.ProductsFragment;
import fr.unice.polytech.jcancela.tobeortohave.fragment.SponsoringFragment;
import fr.unice.polytech.jcancela.tobeortohave.fragment.gmaps.StoresFragment;
import fr.unice.polytech.jcancela.tobeortohave.fragment.twitter.EmbeddedTwitterTimelineFragment;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //User
        Bundle bundle = getIntent().getExtras();
        String userEmail = bundle.getString("email");
        View headerView = navigationView.getHeaderView(0);
        TextView userEmailTextView = (TextView) headerView.findViewById(R.id.emailTextView);
        userEmailTextView.setText(userEmail);


        //TweetsMainScreen
        setFragment(new HomeFragment());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
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
        if (id == R.id.logout) {
            Intent i = new Intent(MenuActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();

        } else if (id == R.id.nav_news) {
            fragment = new EmbeddedTwitterTimelineFragment();

        } else if (id == R.id.nav_products) {
            fragment = new ProductsFragment();

        } else if (id == R.id.nav_stores) {
            fragment = new StoresFragment();

        } else if (id == R.id.nav_fidelity_card) {
            fragment = new FidelityCardFragment();

        } else if (id == R.id.nav_sponsoring_program) {
            fragment = new SponsoringFragment();

        } else if (id == R.id.nav_gift_cards) {
            fragment = new GiftCardsFragment();

        }

        if (fragment != null) {
            setFragment(fragment);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
