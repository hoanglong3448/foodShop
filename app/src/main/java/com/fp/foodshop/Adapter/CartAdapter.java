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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.fp.foodshop.Helper.ChangeNumberItemsListener;
import com.fp.foodshop.Helper.ManagmentCart;
import com.fp.foodshop.Model.Foods;
import com.fp.foodshop.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {
    ArrayList<Foods> list;
    private ManagmentCart  managmentCart;
    ChangeNumberItemsListener  changeNumberItemsListener;

    public CartAdapter(ArrayList<Foods> list, ChangeNumberItemsListener changeNumberItemsListener, Context context) {
        this.list = list;
        this.changeNumberItemsListener = changeNumberItemsListener;
        managmentCart = new ManagmentCart(context);
    }

    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText("$"+list.get(position).getNumberInCart()*list.get(position).getPrice());
        holder.total.setText(list.get(position).getNumberInCart()+"*$" +(list.get(position).getNumberInCart()));
        holder.quantity.setText((list.get(position).getNumberInCart()*list.get(position).getPrice())+"");

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.imgCart);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.plusNumberItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCart.minusNumberItem(list, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title,price,total,minus,plus,quantity;
        ImageView imgCart;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleCart);
            price = itemView.findViewById(R.id.priceCart);
            total = itemView.findViewById(R.id.totalCart);
            minus = itemView.findViewById(R.id.minusCart);
            plus = itemView.findViewById(R.id.plusCart);
            quantity = itemView.findViewById(R.id.numCart);
            imgCart = itemView.findViewById(R.id.imageView8);


        }
    }
}
