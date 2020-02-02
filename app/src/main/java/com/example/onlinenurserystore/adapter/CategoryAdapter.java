package com.example.onlinenurserystore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinenurserystore.R;
import com.example.onlinenurserystore.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryAdapterViewHolder>  {

    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.categoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.categoryAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount()  {
        return categoryList.size();
    }

    public class categoryAdapterViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryImg;
        private TextView categoryName;
        public categoryAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryImg=itemView.findViewById(R.id.imgCategory);
            this.categoryName=itemView.findViewById(R.id.tvcategoryName);
        }

        private void setCategoryImg(){

        }

        private void setCategory(String name){
            categoryName.setText(name);

        }
    }
}

