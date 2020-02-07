package com.example.onlinenurserystore.api;

import com.example.onlinenurserystore.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApi {

    @GET("category/list")
    Call<List<Category>>getCategory();
}
