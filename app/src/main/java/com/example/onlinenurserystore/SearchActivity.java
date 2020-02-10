package com.example.onlinenurserystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.adapter.SearchAdapter;
import com.example.onlinenurserystore.api.ProductApi;
import com.example.onlinenurserystore.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

        private ImageButton imgBack, imgSearch;
        TextView tvSearch;
        RecyclerView recyclerView;
        String name ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        imgBack=findViewById(R.id.imgBack);
        imgSearch=findViewById(R.id.imgSearch);
        tvSearch=findViewById(R.id.tvSearch);
        recyclerView=findViewById(R.id.recyclerViewSearch);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductApi medicineApi= Url.getInstance().create(ProductApi.class);

                Call<List<Products>> voidCall=medicineApi.searchProduct(tvSearch.getText().toString());

                voidCall.enqueue(new Callback<List<Products>>() {
                    @Override
                    public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SearchActivity.this, "Toast"+response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        SearchAdapter searchAdapter=new SearchAdapter(getApplicationContext(),response.body());
                        recyclerView.setAdapter(searchAdapter);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        searchAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Products>> call, Throwable t) {
                        Toast.makeText(SearchActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
