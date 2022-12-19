package com.example.firebasetestapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class SuccessfulAuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_authorization);
    }

    public void logout(View view){
        Intent gotoSingIn = new Intent(SuccessfulAuthorizationActivity.this, SignInActivity.class);
        startActivity(gotoSingIn);
        onStop();
    }
}