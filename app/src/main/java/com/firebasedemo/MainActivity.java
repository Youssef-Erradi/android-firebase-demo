package com.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.logoutBtn).setOnClickListener(v -> {
            auth.signOut();
            startLoginActivity();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( auth.getCurrentUser() == null)
            startLoginActivity();
    }

    public void startLoginActivity(){
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
}