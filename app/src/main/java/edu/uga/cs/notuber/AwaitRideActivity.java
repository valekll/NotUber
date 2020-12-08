package edu.uga.cs.notuber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AwaitRideActivity extends AppCompatActivity {

    private TextView rideIdTextView;
    private String rideId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_await_ride);

        rideId = getIntent().getExtras().getString(AddRideActivity.RIDEID);
        rideIdTextView = (TextView)findViewById(R.id.rideIdTextView);
        rideIdTextView.setText(rideId);
    } //onCreate()
} //AwaitRideActivity