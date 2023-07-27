package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2500; // Delay in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if the user is already logged in
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                    // User is logged in, open MainActivity
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    // User is not logged in, open Register activity
                    startActivity(new Intent(getApplicationContext(), Register.class));
                }

                // Close the SplashScreen activity to prevent going back to it
                finish();
            }
        }, SPLASH_DELAY);
    }
}
