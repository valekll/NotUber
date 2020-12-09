package edu.uga.cs.notuber;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DriverMissionActivity extends AppCompatActivity {
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_mission);

        // Add the back button in the ActionBar of this activity.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button confirmButton = (Button)findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent confirmIntent = new Intent(DriverMissionActivity.this, LandingMainActivity.class);
                startActivity(confirmIntent);
            }
        });

    } //onCreate()
    /**
     * Back button in toolbar
     * @param item the menu item
     * @return the item selected action
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Implement Back button listener method.
        if(item.getItemId() == android.R.id.home ) {
            onBackPressed();
            return true;
        } //if
        return super.onOptionsItemSelected( item );
    } //onOptionsItemSelected()
} //DriverMissionActivity