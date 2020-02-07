package com.example.onlinenurserystore.api;

import com.example.onlinenurserystore.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    @GET("product/list")
    Call<List<Products>> getProduct();
}
