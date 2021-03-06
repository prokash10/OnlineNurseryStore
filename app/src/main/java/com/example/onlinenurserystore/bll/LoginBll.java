package com.example.onlinenurserystore.bll;

import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.api.UserAPI;
import com.example.onlinenurserystore.serverresponse.SignUpResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {

    boolean isSuccess = false;

    public boolean checkUser(String Email, String Password) {

        UserAPI usersAPI = Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.checkUser(Email, Password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
            loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                isSuccess = true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}



