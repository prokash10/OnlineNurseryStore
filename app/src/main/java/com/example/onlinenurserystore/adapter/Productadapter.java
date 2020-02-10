package com.example.onlinenurserystore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinenurserystore.ProductDetailsActivity;
import com.example.onlinenurserystore.R;
import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.model.Products;
import com.squareup.picasso.Picasso;
import com.example.onlinenurserystore.CategoryActivity;

import java.util.List;

public class Productadapter extends RecyclerView.Adapter<Productadapter.ItemViewHolder> {
    private Context context;
    private List<Products>productsList;

    public Productadapter(Context context, List<Products>productsList){
        this.context=context;
        this.productsList=productsList;
    }


    @NonNull
    @Override
    public Productadapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,final int position) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant,null);
        return new ItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Productadapter.ItemViewHolder holder, final int position) {
        final Products product=productsList.get(position);
        holder.tvItemName.setText(product.getProductname());
        holder.tvItemPrice.setText("Rs"+product.getPrice());
        Picasso.get().load(Url.base_url_image+productsList.get(position).getProductimage()).into(holder.imgItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("id",productsList.get(position).get_id());
                intent.putExtra("name",productsList.get(position).getProductname());
                intent.putExtra("price",productsList.get(position).getPrice());
                intent.putExtra("description",productsList.get(position).getProductdescription());
                intent.putExtra("image",productsList.get(position).getProductimage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
       return productsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView tvItemName, tvItemPrice;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgItem=itemView.findViewById(R.id.imgItem);
            this.tvItemName=itemView.findViewById(R.id.tvItemName);
            this.tvItemPrice=itemView.findViewById(R.id.tvItemPrice);
        }
    }
}
