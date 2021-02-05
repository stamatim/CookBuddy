package com.example.primaryfolder.cookbuddy.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.primaryfolder.cookbuddy.R;

public class UserProfile extends AppCompatActivity {

    // The button to sign out
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize the button
        btnSignOut = (Button) findViewById(R.id.SignOut);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO

                // Takes the user back to the sign in page
                Intent i = new Intent(UserProfile.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }
}