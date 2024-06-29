package com.fp.foodshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.fp.foodshop.databinding.ActivitySignUpBinding;

public class SignUpActivity extends BaseActivity {
    ActivitySignUpBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setLogInNav();
        setVariable();
    }

    private void setVariable() {
        binding.btnSignup.setOnClickListener(v -> {
            String email =binding.userEdtSign.getText().toString();
            String password = binding.passEdtSign.getText().toString();
            if (password.length() < 6){
                Toast.makeText(SignUpActivity.this,"Password is too weak",Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email,password).
                    addOnCompleteListener(SignUpActivity.this, task ->{
                        if (task.isSuccessful()){
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(SignUpActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }

    private void setLogInNav() {
        binding.loginNav2.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        });
    }
}