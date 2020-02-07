package com.example.onlinenurserystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.adapter.Productadapter;
import com.example.onlinenurserystore.api.ProductApi;
import com.example.onlinenurserystore.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView itemImage;
    String categotyid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView=findViewById(R.id.PlantRecyclerView);
        itemImage=findViewById(R.id.imgItem);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            categotyid=bundle.getString("categoryid");

        }

        ProductApi productApi= Url.getInstance().create(ProductApi.class);
        Call<List<Products>> listCall=productApi.getProductByID(categotyid);
        listCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.code()==200){
                    Toast.makeText(CategoryActivity.this, "Toast"+ response.code(),Toast.LENGTH_SHORT).show();

                }
                Productadapter productadapter=new Productadapter(CategoryActivity.this, response.body());
                recyclerView.setAdapter(productadapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(CategoryActivity.this, 2));
                productadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Error" + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();


            }
        });

    }
}
