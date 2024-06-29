package com.fp.foodshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.fp.foodshop.Activity.DetailActivity;
import com.fp.foodshop.Model.Foods;
import com.fp.foodshop.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.viewholder> {

    ArrayList<Foods> listFood;
    Context context;

    public ListFoodAdapter(ArrayList<Foods> listFood) {
        this.listFood = listFood;
    }

    @NonNull
    @Override
    public ListFoodAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_listfood,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFoodAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(listFood.get(position).getTitle());
        holder.timeTxt.setText(listFood.get(position).getTimeValue()+"min");
        holder.rateTxt.setText(String.valueOf(listFood.get(position).getStar()));
        String priceText = context.getString(R.string.price_placeholder, String.valueOf(listFood.get(position).getPrice()));
        holder.priceTxt.setText(priceText);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object", (Serializable) listFood.get(position));
            context.startActivity(intent);
        });
        Glide.with(context)
                .load(listFood.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.Img);
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,timeTxt,priceTxt,rateTxt;
        ImageView Img;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            rateTxt = itemView.findViewById(R.id.rateTxt);
            Img = itemView.findViewById(R.id.Img);
        }
    }


}
