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

public class Productadapter extends RecyclerView.Adapter<Productadapter.ItemViewHolder>  {

    List<Products> productsList;
    Context context;

    public Productadapter(Context context, List<Products> productsLis) {
        this.productsList = productsLis;
        this.context = context;
    }



    @NonNull
    @Override
    public Productadapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Productadapter.ItemViewHolder holder, int position) {
        final Products products=productsList.get(position);
        Picasso.get().load(Url.base_url_image+productsList.get(position).getProductImage()).into(holder.imgImage);
        holder.tvDescription.setText(products.getProductDescription());
        holder.tvRate.setText("Rs" + products.getPrice());

    }

    @Override
    public int getItemCount() {
        return productsList.size();

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgImage;
        TextView tvDescription, tvRate;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.imgProduct);
            tvDescription = itemView.findViewById(R.id.tvProductDesc);
            tvRate = itemView.findViewById(R.id.tvProductPrice);
        }
    }
}
