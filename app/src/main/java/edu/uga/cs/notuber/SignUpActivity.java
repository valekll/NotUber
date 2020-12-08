package edu.uga.cs.notuber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private FirebaseUser currUser;

    private DatabaseReference myDb;

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView confirmPasswordTextView;
    private TextView phoneTextView;
    private TextView usernameTextView;
    private TextView mDisplayDate;

    private Button submitButton;

    private String email;
    private String username;
    private String password;
    private String confirmPass;
    private String firstName;
    private String lastName;
    private String phoneNum;

    private int[] dob;
    private boolean dateSet;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //grab username from last screen
        email = "";
        password = "";
        dateSet = false;
        myAuth = FirebaseAuth.getInstance();
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
        confirmPasswordTextView = (TextView)findViewById(R.id.confirmPassword);
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
                dateSet = true;
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            } //onDateSet
        };

        submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(checkSignUpRequirements());
            } //onClick()
        });
    } //onCreate()

    /**
     * Signs the user up for a Not Uber account
     * @param checkSignUpRequirements makes sure form's requirements are met
     */
    private void signUp(boolean checkSignUpRequirements) {
        //if(checkSignUpRequirements) {
            myAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Log.d("Turtle", "Sign up attempt: success");
                                currUser = myAuth.getCurrentUser();
                                NotUberUser myUser = new NotUberUser(email, currUser.getUid(),
                                        username, firstName, lastName, phoneNum, dob[1], dob[0], dob[2]);
                                myDb = FirebaseDatabase.getInstance().getReference();
                                myDb.child("users").child(currUser.getUid()).setValue(myUser);
                                Intent landingIntent = new Intent(SignUpActivity.this,
                                        LandingMainActivity.class);
                                startActivity(landingIntent);
                            } //if
                            else {
                                Log.d("Turtle", "Sign up attempt: fail");
                            } //else
                        } //onComplete()
                    });
        //} //if
    } //signUp()

    /**
     * Checks to make sure sign up requirements are made.
     * TODO: Checks for fields for user account creation
     */
    private boolean checkSignUpRequirements() {
        if(checkEmptyFields()) {
            if(checkUsername()) {
                if(checkPassword()) {
                    if(checkDate()) {
                        return true;
                    } //if
                } //if
            } //if
        }//if
        return false;
    } //checkSignUpRequirements

    /**
     * Checks for date compliance
     * @return whether it checks out or not
     */
    private boolean checkDate() {
        return dateSet;
    } //checkDate()

    /**
     * Checks for password compliance
     * @return whether it checks out or not
     */
    private boolean checkPassword() {
        password = passwordTextView.getText().toString();
        if(!password.equals(confirmPasswordTextView.getText().toString())) {
            //make toast
            Toast myMessage = Toast.makeText(SignUpActivity.this,
                    "Passwords do not match.", Toast.LENGTH_SHORT);
            //set toast colors to be more visible
            View messageView = myMessage.getView();
            messageView.setBackgroundColor(Color.GRAY);
            TextView messageTextView = (TextView)myMessage.getView()
                    .findViewById(android.R.id.message);
            messageTextView.setTextColor(Color.WHITE);
            myMessage.show();
            return false;
        } //if
        return true;
    } //checkPassword

    /**
     * Checks for username compliance
     * @return whether it checks out or not
     */
    private boolean checkUsername() {
        username = usernameTextView.getText().toString();
        if(username.length() < 5) {
            //make toast
            Toast myMessage = Toast.makeText(SignUpActivity.this,
                    "Username must be 5 or more characters long.", Toast.LENGTH_SHORT);
            //set toast colors to be more visible
            View messageView = myMessage.getView();
            messageView.setBackgroundColor(Color.GRAY);
            TextView messageTextView = (TextView)myMessage.getView()
                    .findViewById(android.R.id.message);
            messageTextView.setTextColor(Color.WHITE);
            myMessage.show();
            return false;
        } //if
        else {
            //check for non alphaNumerics
            Pattern nonAlphaNumeric = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher myMatcher = nonAlphaNumeric.matcher(username);
            if(myMatcher.find()) {
                //make toast
                Toast myMessage = Toast.makeText(SignUpActivity.this,
                        "Username must only contain letters and numbers.", Toast.LENGTH_SHORT);
                //set toast colors to be more visible
                View messageView = myMessage.getView();
                messageView.setBackgroundColor(Color.GRAY);
                TextView messageTextView = (TextView)myMessage.getView()
                        .findViewById(android.R.id.message);
                messageTextView.setTextColor(Color.WHITE);
                myMessage.show();
                return false;
            }
        }
        return true;
    } //checkUsername()

    /**
     * Checks for empty fields compliance
     * @return whether it checks out or not
     */
    private boolean checkEmptyFields() {
        username = usernameTextView.getText().toString();
        password = passwordTextView.getText().toString();
        confirmPass = confirmPasswordTextView.getText().toString();
        firstName = firstNameTextView.getText().toString();
        lastName = lastNameTextView.getText().toString();
        email = emailTextView.getText().toString();
        phoneNum = phoneTextView.getText().toString();
        if(username.equals("") || password.equals("") || confirmPass.equals("") ||
                firstName.equals("") || lastName.equals("") ||
                email.equals("") || phoneNum.equals("")) {
            //make toast
            Toast myMessage = Toast.makeText(SignUpActivity.this,
                    "Please finish filling out the form.", Toast.LENGTH_SHORT);
            //set toast colors to be more visible
            View messageView = myMessage.getView();
            messageView.setBackgroundColor(Color.GRAY);
            TextView messageTextView = (TextView)myMessage.getView()
                    .findViewById(android.R.id.message);
            messageTextView.setTextColor(Color.WHITE);
            myMessage.show();
            return false;
        } //if
        return true;
    } //checkPassword

} //SignUpActivity