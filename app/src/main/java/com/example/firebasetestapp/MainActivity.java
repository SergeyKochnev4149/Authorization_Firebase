package com.example.firebasetestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent goToSignIn = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(goToSignIn);
    }
}