package com.example.primaryfolder.cookbuddy.activities;

import android.content.Intent;
import android.media.MediaCas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.fragments.HomeFragment;
import com.example.primaryfolder.cookbuddy.fragments.MappingFragment;
import com.example.primaryfolder.cookbuddy.fragments.MessagingFragment;
import com.example.primaryfolder.cookbuddy.fragments.NearbyStoresFragment;
import com.example.primaryfolder.cookbuddy.fragments.ProfileFragment;
import com.example.primaryfolder.cookbuddy.fragments.ShoppingListFragment;
import com.example.primaryfolder.cookbuddy.fragments.ViewRecipesFragment;
import com.example.primaryfolder.cookbuddy.net_utils.Const;
import com.example.primaryfolder.cookbuddy.other.SessionManager;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // instantiate variables
    private View navHeader;

    // session manager class
    public SessionManager uSession;
    public static String userName;
    public static int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // session class instance
        uSession = new SessionManager(getApplicationContext()); // create new session manager
        uSession.checkLogin(); // check to make sure user is logged in
        HashMap<String, String> user = uSession.getUserDetails(); // get user data from session
        userName = user.get(SessionManager.KEY_NAME); // the name of the user
        String email = user.get(SessionManager.KEY_EMAIL); // the email of the user
        userID = Integer.parseInt(user.get(SessionManager.KEY_ID));

        // the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // the floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddRecipe.class);
                startActivity(i);
            }
        });

        // the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // the view of the nav bar
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);

        // loading profile image
        ImageView profileImage = navHeader.findViewById(R.id.profileImage);
        Glide.with(this).load(Const.PROFILE_URL)
                .apply(RequestOptions.circleCropTransform())
                .thumbnail(0.5f)
                .into(profileImage);

        // loading background image
        ImageView navBackground = navHeader.findViewById(R.id.img_header_bg);
        Glide.with(this).load(Const.BACKGROUND_URL)
                .thumbnail(0.5f)
                .into(navBackground);

        // select Home by default
        navigationView.setCheckedItem(R.id.nav_home);
        Fragment fragment = new HomeFragment();
        displaySelectedFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) { // Home
            fragment = new HomeFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_view_recipes) { // View Recipes
            fragment = (Fragment) new ViewRecipesFragment();
            displaySelectedFragment(fragment);
        }
        else if (id == R.id.nav_shopping_list) { // Shopping List
            fragment = (Fragment) new ShoppingListFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_nearby_stores) { // Nearby Stores
            fragment = (Fragment) new MappingFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_messaging) { // Messaging
            fragment = new MessagingFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_profile) { // Profile
            fragment = new ProfileFragment();
            displaySelectedFragment(fragment);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Loads the specified fragment to the frame
     *
     * @param fragment
     */
    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
