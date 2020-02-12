package com.example.onlinenurserystore.bll;

import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.api.UserAPI;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LogoutBll {
    boolean isSuccess=false;
    public boolean logout(String token){
        UserAPI usersApi= Url.getInstance().create(UserAPI.class);
        Call<Void> voidCall=usersApi.logOut(token);
        try {
            Response<Void> response= voidCall.execute();
            if (response.code()==200){
                Url.token=("Bearer ");
                isSuccess=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
