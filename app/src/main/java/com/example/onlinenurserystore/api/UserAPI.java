package com.example.onlinenurserystore.api;


import com.example.onlinenurserystore.model.Users;
import com.example.onlinenurserystore.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("user/signup")
    Call<SignUpResponse> registerUser(@Body Users users);

    @FormUrlEncoded
    @POST("user/login")
    Call<SignUpResponse> checkUser(@Field("Email") String Email, @Field("Password") String Password);
}
