package com.fp.foodshop.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fp.foodshop.Adapter.ListFoodAdapter;
import com.fp.foodshop.Model.Foods;
import com.fp.foodshop.R;
import com.fp.foodshop.databinding.ActivityDetailBinding;
import com.fp.foodshop.databinding.ActivityListFoodBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFoodActivity extends BaseActivity {
    ActivityListFoodBinding binding;
    private RecyclerView.Adapter adapterListFood;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initIntentExtra();
        initList();
    }

    private void initList() {
        DatabaseReference myRef= database.getReference("Foods");
        binding.listFoodBar.setVisibility(View.VISIBLE);
        ArrayList<Foods> foods = new ArrayList<>();

        Query query;

        if (isSearch){
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else {
            query = myRef.orderByChild("categoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()){
                        foods.add(issue.getValue(Foods.class));
                    }
                    if (!foods.isEmpty()){
                        binding.listFoodView.setLayoutManager(new GridLayoutManager(ListFoodActivity.this,2));
                        adapterListFood = new ListFoodAdapter(foods);
                        binding.listFoodView.setAdapter(adapterListFood);
                    }
                    binding.listFoodBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId",0);
        categoryName = getIntent().getStringExtra("categoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch =getIntent().getBooleanExtra("isSearch",false);

        binding.listFoodTitle.setText(categoryName);
        binding.listFoodBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}