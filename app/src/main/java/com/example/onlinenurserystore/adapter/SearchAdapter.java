package com.example.onlinenurserystore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinenurserystore.ProductDetailsActivity;
import com.example.onlinenurserystore.R;
import com.example.onlinenurserystore.model.Products;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<Products>productsList;

    public SearchAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }


    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, final int position) {

        final Products products=productsList.get(position);
        holder.Name.setText(products.getProductname());
        holder.Price.setText("RS"+products.getPrice());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("name",productsList.get(position).getProductname());
                intent.putExtra("price",productsList.get(position).getPrice());
                intent.putExtra("image",productsList.get(position).getProductimage());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() { return productsList.size();}
        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView linearLayout;
            private TextView Name;
            private TextView Price;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                Name=itemView.findViewById(R.id.tvItemName);
                Price=itemView.findViewById(R.id.tvItemPrice);
                linearLayout=itemView.findViewById(R.id.linearlayout);
            }
    }
}
