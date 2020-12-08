package edu.uga.cs.notuber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple activity to show destinations utilizing fragments.
 */
public class LandingMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String DEBUG_TAG = "Destinations";

    private FirebaseAuth myAuth;
    private Button logoutButton;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_main);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.nav_view);

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


        myAuth = FirebaseAuth.getInstance();
        logoutButton = (Button)findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAuth.signOut();
                Intent loginIntent = new Intent(LandingMainActivity.this,
                        LoginActivity.class);
                startActivity(loginIntent);
            } //onClick()
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
