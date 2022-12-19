package com.example.firebasetestapp;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class SignInActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


    }

    public void signIn(View view) {
        goToSuccessfulAuthorizationActivity();
    }

    private void goToSuccessfulAuthorizationActivity() {
        Intent successfulAuthorizationActivity = new Intent(SignInActivity.this, SuccessfulAuthorizationActivity.class);
        startActivity(successfulAuthorizationActivity);
        onStop();
    }




}
