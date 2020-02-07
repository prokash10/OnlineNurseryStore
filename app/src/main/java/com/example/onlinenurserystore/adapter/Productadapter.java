package com.example.onlinenurserystore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinenurserystore.R;
import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.model.Products;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Productadapter extends RecyclerView.Adapter<Productadapter.ItemViewHolder> {

    List<Products> productsList;
    Context context;

    public Productadapter(Context context, List<Products> productsList) {
        this.productsList = productsList;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final Products products = productsList.get(position);
        holder.tvPlantname.setText(products.getProductName());
        holder.tvRate.setText("Rs" + products.getPrice());
        Picasso.get().load(Url.base_url_image + productsList.get(position).getProductImage()).into(holder.imgplant);

    }

    @Override
    public int getItemCount() {
        return productsList.size(); }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgplant;
        TextView tvPlantname, tvRate;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgplant = itemView.findViewById(R.id.imgItem);
            tvPlantname = itemView.findViewById(R.id.tvItemName);
            tvRate = itemView.findViewById(R.id.tvItemPrice);
        }
    }


}
