package com.example.onlinenurserystore.bll;

import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.api.UserAPI;
import com.example.onlinenurserystore.serverresponse.SignUpResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {

    boolean isSuccess = false;

    public boolean checkUser(String UserName, String Password) {

        UserAPI usersAPI = Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.checkUser(UserName, Password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful()){

                    Url.token += loginResponse.body().getToken();
                    // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                    isSuccess = true;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}



