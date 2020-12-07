package edu.uga.cs.notuber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private FirebaseUser currUser;

    private DatabaseReference myDbRef;

    private NotUberUser myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        myAuth = FirebaseAuth.getInstance();
        currUser = myAuth.getCurrentUser();
        Log.d("Titanium", "\"" + currUser.getUid() + "\"");
        myDbRef = FirebaseDatabase.getInstance().getReference("users/" + currUser.getUid());
        myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myUser = snapshot.getValue(NotUberUser.class);
                if(myUser != null) {
                    Log.d("Titanium", myUser.toString());
                    TextView myTV = (TextView) findViewById(R.id.testTextView);
                    myTV.setText(myUser.toString());
                }
            } //onDataChange()

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Titanium", "User data read failed.");
            }
        });

    }
}