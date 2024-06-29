package com.fp.foodshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fp.foodshop.Adapter.BestFoodAdapter;
import com.fp.foodshop.Adapter.CategoryAdapter;
import com.fp.foodshop.Model.Category;
import com.fp.foodshop.Model.Foods;
import com.fp.foodshop.Model.Location;
import com.fp.foodshop.Model.Price;
import com.fp.foodshop.Model.Time;
import com.fp.foodshop.R;
import com.fp.foodshop.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding; // Declare binding as a class variable
    RecyclerView.Adapter bestFoodAdapter; // Corrected naming convention for adapter

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initlogOut();
        initPrice();
        initLoc();
        initTime();
        initbestFood();
        initCategory();
    }

    private void initCategory() {
        DatabaseReference myRef =database.getReference("Category");
        binding.progressCats.setVisibility(View.VISIBLE);
        ArrayList<Category> categoryList = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()){
                        categoryList.add(issue.getValue(Category.class));
                    }
                }
                if (categoryList.size() > 0){
                    binding.recyclerViewCats.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                    RecyclerView.Adapter adapter = new CategoryAdapter(categoryList);
                    binding.recyclerViewCats.setAdapter(adapter);
                }
                binding.progressCats.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initTime() {
        DatabaseReference myRef = database.getReference("Time");
        ArrayList<Time> time = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        time.add(issue.getValue(Time.class));
                    }
                    ArrayAdapter<Time> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, time);
                    adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                    binding.spinnerTime.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }

    private void initPrice() {
        DatabaseReference myRef = database.getReference("Price");
        ArrayList<Price> price = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        price.add(issue.getValue(Price.class));
                    }
                    ArrayAdapter<Price> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, price);
                    adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                    binding.spinnerPrice.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }

    private void initLoc() {
        DatabaseReference myRef = database.getReference("Location");
        ArrayList<Location> loc = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        loc.add(issue.getValue(Location.class));
                    }
                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, loc);
                    adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                    binding.spinnerLoc.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }

    private void initbestFood() {
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressPopular.setVisibility(View.VISIBLE);
        ArrayList<Foods> items = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        items.add(issue.getValue(Foods.class));
                    }
                    if (!items.isEmpty()) {
                        binding.recyclerViewPopular.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        bestFoodAdapter = new BestFoodAdapter(items);
                        binding.recyclerViewPopular.setAdapter(bestFoodAdapter);
                    }
                }
                binding.progressPopular.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });
    }

    private void initlogOut() {
        binding.imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish(); // Finish current activity after logout
            }
        });
        binding.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });

        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.edtSearch.getText().toString().trim(); // Trim spaces from search text
                if (!text.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, ListFoodActivity.class);
                    intent.putExtra("text", text);
                    intent.putExtra("isSearch", true);
                    startActivity(intent);
                }
            }
        });
    }
}