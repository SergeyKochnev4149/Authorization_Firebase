package com.example.firebasetestapp;



import static android.content.ContentValues.TAG;
import static com.example.firebasetestapp.MainActivity.hasConnection;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
    private TextView error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        mAuth = FirebaseAuth.getInstance();
        error = findViewById(R.id.error);


    }

    public void signIn(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();


        if (!(email.equals("") && password.equals(""))) {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignInActivity.this, (OnCompleteListener<AuthResult>) task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            goToSuccessfulAuthorizationActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            if (hasConnection(this))
                                error.setText("Incorrect email or password");
                            else {
                                error.setText("");
                                Toast.makeText(SignInActivity.this, "No internet connection.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            error.setText("Email or password is empty");
        }
    }

    private void goToSuccessfulAuthorizationActivity() {
        Intent successfulAuthorizationActivity = new Intent(SignInActivity.this, SuccessfulAuthorizationActivity.class);
        startActivity(successfulAuthorizationActivity);
        onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            goToSuccessfulAuthorizationActivity();
        }
    }




}
