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

public class BestFoodAdapter extends RecyclerView.Adapter<BestFoodAdapter.viewholder> {
    ArrayList<Foods> items;
    Context context;

    public BestFoodAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BestFoodAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_deal,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestFoodAdapter.viewholder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object", (Serializable) items.get(position));
            context.startActivity(intent);

        });
        holder.titleTxt.setText(items.get(position).getTitle());
        String priceText = context.getString(R.string.price_placeholder, String.valueOf(items.get(position).getPrice()));
        holder.priceTxt.setText(priceText);
        double starValue = items.get(position).getStar();
        holder.textView9.setText(String.format("%.2f", starValue));

        Glide.with(context)
                .load(items.get(position))
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.imageView4);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView priceTxt,titleTxt,timeTxt,textView9;
        ImageView imageView4;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt =itemView.findViewById(R.id.titleTxt);
            priceTxt =itemView.findViewById(R.id.priceTxt);
            timeTxt =itemView.findViewById(R.id.timeTxt);
            textView9 =itemView.findViewById(R.id.textView9);
            imageView4 = itemView.findViewById(R.id.imageView4);


        }
    }
}