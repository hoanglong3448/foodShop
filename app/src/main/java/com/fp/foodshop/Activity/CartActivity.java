package com.fp.foodshop.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fp.foodshop.Helper.ManagmentCart;
import com.fp.foodshop.R;
import com.fp.foodshop.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);

        setVariable();
        calculateCart();

    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round(managmentCart.getTotalFee() * percentTax * 100.0) / 100;

        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee() *100) /100;


        binding.textView27.setText("$" + itemTotal);
        binding.textView29.setText("$" + tax);
        binding.textView28.setText("$" + delivery);
        binding.textView31.setText("$" + total);
    }

    private void setVariable() {
        binding.imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}