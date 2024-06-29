package com.fp.foodshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fp.foodshop.Model.Category;
import com.fp.foodshop.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    ArrayList<Category> category;
    Context context;

    public CategoryAdapter(ArrayList<Category> category) {
        this.category = category;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position) {
        holder.catTxt.setText(category.get(position).getName());

        switch (position){
            case 0 : {
                holder.imgCat.setBackgroundResource(R.drawable.cat_0_background);
            }
            case 1 : {
                holder.imgCat.setBackgroundResource(R.drawable.cat_1_background);
            }
            case 2 : {
                holder.imgCat.setBackgroundResource(R.drawable.cat_2_background);
            }
            case 3 : {
                holder.imgCat.setBackgroundResource(R.drawable.cat_3_background);
            }
            case 4 : {
                holder.imgCat.setBackgroundResource(R.drawable.cat_4_background);
            }
            case 5 : {
                holder.imgCat.setBackgroundResource(R.drawable.cat_5_background);
            }
            case 6 : {
                holder.imgCat.setBackgroundResource(R.drawable.cat_6_background);
            }
            case 7 : {
                holder.imgCat.setBackgroundResource(R.drawable.cat_7_background);
            }
        }
        int drawableResourceId = context.getResources().getIdentifier(category.get(position).getImagePath(),
                "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.imgCat);
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView catTxt;
        ImageView imgCat;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            catTxt = itemView.findViewById(R.id.catTxt);
            imgCat = itemView.findViewById(R.id.imgCat);

        }
    }
}
