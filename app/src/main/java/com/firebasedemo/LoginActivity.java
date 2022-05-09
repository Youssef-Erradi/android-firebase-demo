package com.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button registerBtn, loginBtn;
    private EditText emailTxt, passwordTxt;
    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerBtn = findViewById(R.id.registerNowBtn);
        loginBtn = findViewById(R.id.loginBtn);
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);

        registerBtn.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        });

        loginBtn.setOnClickListener(v -> loginUser());
    }

    public void loginUser() {
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

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                showToast("Logged in successfully", Toast.LENGTH_SHORT);
                startActivity(new Intent(this, MainActivity.class));
            } else
                showToast("Error in Login operation", Toast.LENGTH_SHORT);
        });

    }

    public void showToast(String message, Integer length) {
        Toast.makeText(this, message, length).show();
    }
}