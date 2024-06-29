package com.fp.foodshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.fp.foodshop.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSignupNav();
        setLogIn();
    }

    private void setLogIn() {
        String email = binding.userEdt.getText().toString();
        String password = binding.passEdt.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(LoginActivity.this, task -> {
                if (task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this,"Authentication failed", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(LoginActivity.this,"Email or Password cannot be empty",Toast.LENGTH_SHORT).show();
        }
    }

    private void setSignupNav() {
        binding.signupNav2.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
    }
}