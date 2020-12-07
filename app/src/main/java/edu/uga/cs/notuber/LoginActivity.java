package edu.uga.cs.notuber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The login screen for the ride sharing app. Also the initial landing page.
 */
public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private FirebaseUser currUser;

    private EditText usernameBox;
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
        usernameBox = (EditText)findViewById(R.id.emailBox);
        passwordBox = (EditText)findViewById(R.id.passwordBox);

        loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginPressed(view);
            } //onClick()
        });

        signUpLinkText = (TextView)findViewById(R.id.signUpLinkText);
        signUpLinkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(view.getContext(), SignUpActivity.class);
                //auto populate sign up intent with user input values
                signUpIntent.putExtra("USERNAME", usernameBox.getText().toString());
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
        String username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        if(username != null && password != null) {
        } //if
    } //onLoginPressed

    /**
     * Logins into the app and sends user into landing activity.
     * @param myUser the user to login to the activity
     * @throws NullPointerException when a user has not been logged in
     */
    private void loginToLanding(FirebaseUser myUser) throws NullPointerException {
        if(myUser == null)
            throw new NullPointerException("FirebaseUser myUser may not be null.");
        Intent landingIntent = new Intent(this, LandingActivity.class);
        startActivity(landingIntent);
    } //loginToLanding

} //LoginActivity