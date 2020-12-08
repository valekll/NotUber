package edu.uga.cs.notuber;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple activity to show destinations utilizing fragments.
 */
public class LandingMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String DEBUG_TAG = "Destinations";

    private FirebaseAuth myAuth;
    private FirebaseUser currUser;
    private Button logoutButton;
    private ClipData.Item logout;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private TextView usernameTextView;
    private TextView ridepointsTextView;

    private MenuItem logoutItem;
    private Menu navMenu;

    /**
     * OnCreate for the main landing page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_main);
        myAuth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        navMenu = navigationView.getMenu();

        usernameTextView = findViewById(R.id.usernameTextView);
        ridepointsTextView = findViewById(R.id.ridePointsTextView);

        logoutItem = navMenu.findItem(R.id.logout);

        logoutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick (MenuItem menuItem) {
                    myAuth.signOut();
                    Intent loginIntent = new Intent(LandingMainActivity.this,
                            LoginActivity.class);
                    startActivity(loginIntent);
                return true;
            }
        });

        drawerLayout = findViewById(R.id.drawer_Layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        Log.d( DEBUG_TAG, "LandingActivity.onCreate(): savedInstanceState: " + savedInstanceState );

        // this call will create the UI based on the screen in portrait orientation.
        // /res/layout/activity_country_main.xml will be used;
        // in landscape orientation /res/layout-land/activity_country_main.xml will be used
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(LandingMainActivity.this,
                        AddRideActivity.class);
                startActivity(addIntent);
            }
        });
    } //onCreate()



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

} //LandingActivityMain
