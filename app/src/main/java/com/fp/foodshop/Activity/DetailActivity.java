package com.fp.foodshop.Activity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.fp.foodshop.Helper.ManagmentCart;
import com.fp.foodshop.Model.Foods;
import com.fp.foodshop.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    private ManagmentCart managmentCart;
    ActivityDetailBinding binding;
    private Foods object;
    private int num = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart = new ManagmentCart(this);
        binding.backBtn.setOnClickListener(v -> finish());

        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.pic);

        binding.priceTxt.setText("$"+ object.getPrice());
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.ratingBar.setRating((float) object.getStar());
        binding.totalPrices.setText((num * object.getPrice()+ "$"));

        binding.plusTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num+1;
                binding.textView19.setText(num+" ");
                binding.totalPrices.setText(("$"+ num* object.getPrice()));
            }
        });
        binding.minusTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 1){
                    num = num -1 ;
                    binding.textView19.setText(num + "");
                    binding.totalPrices.setText(("$"+ num* object.getPrice()));
                }
            }
        });
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(num);
                managmentCart.insertFood(object);
            }
        });



    }


    private void getIntentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }
}