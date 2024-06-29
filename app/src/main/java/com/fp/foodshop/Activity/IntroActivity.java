package com.fp.foodshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fp.foodshop.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUserNav();

    }

    private void setUserNav() {
        binding.loginNav.setOnClickListener(v -> {
        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
        }else{
            startActivity(new Intent(IntroActivity.this, LoginActivity.class));
        }
            Log.d("IntroActivity", "Login TextView clicked");
        });

        binding.signupNav.setOnClickListener(v -> {
            startActivity(new Intent(IntroActivity.this, SignUpActivity.class));
        });
    }
}