package edu.uga.cs.notuber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView phoneTextView;
    private TextView usernameTextView;
    private TextView mDisplayDate;

    private Button submitButton;

    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNum;

    private int[] dob;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //grab username from last screen
        email = "";
        password = "";
        if(savedInstanceState != null) {
            email = savedInstanceState.getString("EMAIL");
            password = savedInstanceState.getString("PASSWORD");
        } //if

        //init ui elements
        usernameTextView = (TextView)findViewById(R.id.username);
        firstNameTextView = (TextView)findViewById(R.id.firstName);
        lastNameTextView = (TextView)findViewById(R.id.lastName);
        emailTextView = (TextView)findViewById(R.id.email);
        if(email == null || !email.equals(""))
            emailTextView.setText(email);
        passwordTextView = (TextView)findViewById(R.id.password);
        if(password == null || !password.equals(""))
            passwordTextView.setText(password);
        phoneTextView = (TextView)findViewById(R.id.phoneNumber);

        //init date of birth box
        mDisplayDate = (TextView) findViewById(R.id.birthday);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            } //onClick
        });
        //get date of birth box to set date
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                dob = new int[]{month, day, year};
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            } //onDateSet
        };

        submitButton = (Button)findViewById(R.id.submitButton);

    } //onCreate()
} //SignUpActivity