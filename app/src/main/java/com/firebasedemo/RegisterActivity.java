package com.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Button loginBtn, registerBtn;
    private EditText emailTxt, passwordTxt;
    private final FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginNowBtn);
        emailTxt = findViewById(R.id.emailRegisterTxt);
        passwordTxt = findViewById(R.id.passwordRegisterTxt);

        loginBtn.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        });

        registerBtn.setOnClickListener(v -> createUser());
    }

    public void createUser(){
        String email = emailTxt.getText().toString().trim(),
                password = passwordTxt.getText().toString();
        boolean hasErrors = false;

        if (email.isEmpty()) {
            hasErrors = false;
            emailTxt.setError("Email cannot be empty");
        }
        if (password.isEmpty()) {
            hasErrors = false;
            emailTxt.setError("Password cannot be empty");
        }

        if (hasErrors) return;

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                showToast("Registered successfully", Toast.LENGTH_SHORT);
                startActivity(new Intent(this, LoginActivity.class));
            } else
                showToast("Registration ended unsuccessfully", Toast.LENGTH_SHORT);
        });
    }

    public void showToast(String message, Integer length) {
        Toast.makeText(this, message, length).show();
    }

}