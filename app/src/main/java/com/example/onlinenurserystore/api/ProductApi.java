package com.example.onlinenurserystore.api;

import com.example.onlinenurserystore.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApi {
    @GET("product/list")
    Call<List<Products>> getProduct();

    @GET("product/getByCategory/{id}")
    Call<List<Products>> getProductByID(@Path("id") String CategoryID);
    @GET("product/search/{plantName}")
    Call<List<Products>> searchProduct(@Path("plantName") String ProductName);
}
