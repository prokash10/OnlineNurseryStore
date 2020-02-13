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

    ImageButton Back, Search;
    TextView etSearch;
    RecyclerView recyclerView;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Back = findViewById(R.id.backS);
        Search = findViewById(R.id.searchS);
        etSearch = findViewById(R.id.etsearch);
        recyclerView = findViewById(R.id.recyclerViewSearch);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductApi foodAPI = Url.getInstance().create(ProductApi.class);

                Call<List<Products>>listCall = foodAPI.searchProduct(etSearch.getText().toString());

                listCall.enqueue(new Callback<List<Products>>() {
                    @Override
                    public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(SearchActivity.this, "Toast " + response.code(), Toast.LENGTH_SHORT).show();
                        }

                        SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this, response.body());
                        recyclerView.setAdapter(searchAdapter);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                        searchAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Products>> call, Throwable t) {
                        Toast.makeText(SearchActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
