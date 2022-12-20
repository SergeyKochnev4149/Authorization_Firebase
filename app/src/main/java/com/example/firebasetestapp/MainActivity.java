package com.example.firebasetestapp;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
    private TextView error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            setContentView(R.layout.activity_successful_authorization);
        } else {
            setContentView(R.layout.activity_sign_in);
            editTextEmail = findViewById(R.id.editTextTextEmailAddress);
            editTextPassword = findViewById(R.id.editTextTextPassword);
            error = findViewById(R.id.error);
        }
    }



    public void signIn(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();


        if (!(email.equals("") && password.equals(""))) {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, (OnCompleteListener<AuthResult>) task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            setContentView(R.layout.activity_successful_authorization);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            if (hasConnection(this))
                                error.setText("Incorrect email or password");
                            else {
                                error.setText("");
                                Toast.makeText(MainActivity.this, "No internet connection.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else
            error.setText("Email or password is empty");
    }



    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        setContentView(R.layout.activity_sign_in);
    }



    public static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
