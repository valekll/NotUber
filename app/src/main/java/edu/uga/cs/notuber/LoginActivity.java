package edu.uga.cs.notuber;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The login screen for the ride sharing app. Also the initial landing page.
 */
public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private FirebaseUser currUser;

    private EditText emailBox;
    private EditText passwordBox;
    private Button loginButton;
    private TextView signUpLinkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init Firebase auth
        myAuth = FirebaseAuth.getInstance();

        //init ui elements
        emailBox = (EditText)findViewById(R.id.emailBox);
        passwordBox = (EditText)findViewById(R.id.passwordBox);

        /////////////////////////////////////////////////////////////////////////////////
        //////////////TEMP CODE//////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////
        Button bypassButton = (Button)findViewById(R.id.bypassButton);
        bypassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent landingIntent = new Intent(LoginActivity.this, LandingMainActivity.class);
                startActivity(landingIntent);
            }
        });
        /////////////////////////////////////////////////////////////////////////////////

        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginPressed(view);
            } //onClick()
        });

        //takes to sign up page
        signUpLinkText = (TextView)findViewById(R.id.signUpLinkText);
        signUpLinkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(view.getContext(), SignUpActivity.class);
                //auto populate sign up intent with user input values
                signUpIntent.putExtra("EMAIL", emailBox.getText().toString());
                signUpIntent.putExtra("PASSWORD", passwordBox.getText().toString());
                startActivity(signUpIntent);
            } //onClick()
        });
    } //onCreate()

    /**
     * Logs in the app if a user already is logged in.
     */
    @Override
    protected void onStart() {
        super.onStart();
        currUser = myAuth.getCurrentUser();
        if(currUser != null) {
            loginToLanding(currUser);
        } //if
    } //onStart()

    /**
     * Tries to log in user.
     * @param view the button view
     */
    private void onLoginPressed(View view) {
        String email = emailBox.getText().toString();
        String password = passwordBox.getText().toString();
        if(email != null && password != null && !email.equals("") && !password.equals("")) {
            //try to log in
            myAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Log.d("Turtle", "Sign in attempt: success");
                                currUser = myAuth.getCurrentUser();
                                loginToLanding(currUser);
                            } //if
                            //login fails
                            else {
                                Log.d("Turtle", "Sign in attempt: fail");
                                loginFail();
                            } //else
                        } //onComplete()
                    });
        } //if
        else {
            loginFail();
        } //else
    } //onLoginPressed

    /**
     * Logins into the app and sends user into landing activity.
     * @param myUser the user to login to the activity
     * @throws NullPointerException when a user has not been logged in
     */
    private void loginToLanding(FirebaseUser myUser) throws NullPointerException {
        if(myUser == null)
            throw new NullPointerException("FirebaseUser myUser may not be null.");
        Intent landingIntent = new Intent(this, LandingMainActivity.class);
        startActivity(landingIntent);
    } //loginToLanding

    /**
     * Displays a toast and fails the login.
     */
    private void loginFail() {
        //make toast
        Toast myMessage = Toast.makeText(LoginActivity.this,
                "Invalid Credentials", Toast.LENGTH_SHORT);
        //set toast colors to be more visible
        View messageView = myMessage.getView();
        messageView.setBackgroundColor(Color.GRAY);
        TextView messageTextView = (TextView)myMessage.getView()
                .findViewById(android.R.id.message);
        messageTextView.setTextColor(Color.WHITE);
        myMessage.show();
    } //loginFail()
} //LoginActivity