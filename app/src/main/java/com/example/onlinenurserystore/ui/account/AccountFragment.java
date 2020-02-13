package com.example.onlinenurserystore.ui.account;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinenurserystore.EditProfileActivity;
import com.example.onlinenurserystore.LoginActivity;
import com.example.onlinenurserystore.MainActivity;
import com.example.onlinenurserystore.MapsActivity;
import com.example.onlinenurserystore.R;
import com.example.onlinenurserystore.SignUpActivity;
import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.api.UserAPI;
import com.example.onlinenurserystore.bll.LogoutBll;
import com.example.onlinenurserystore.model.Users;
import com.example.onlinenurserystore.strictmode.StrictModeClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private Button btnLogOut, btnmap;
    TextView Username, PhoneNo;
    RelativeLayout relativeLayout, relativeLayoutprofile, reltivelayoutmap;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Inflate the layout for this fragment
        relativeLayout = view.findViewById(R.id.relativelayoutOut);
        relativeLayoutprofile = view.findViewById(R.id.profilelayout);
        relativeLayout=view.findViewById(R.id.relativelayoutOut1);
        Username = view.findViewById(R.id.username);
        PhoneNo = view.findViewById(R.id.PhoneNoP);
        btnLogOut = view.findViewById(R.id.Logout);
        btnmap=view.findViewById(R.id.btnmap);

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        relativeLayoutprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        UserAPI usersAPI = Url.getInstance().create(UserAPI.class);
        final Call<Users> usersCall = usersAPI.getUserDetails(Url.token);

        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    Username.setText(response.body().getUserName());
                    PhoneNo.setText(response.body().getPhoneNo());
                    return;
                }
                else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    public void logout(){
        String token= Url.token;

        LogoutBll logoutBLL = new LogoutBll();
        StrictModeClass.StrictMode();
        if (logoutBLL.logout(token)){
            getActivity().finish();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "Logout succecsful", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getContext(), "Logout process failed! Please try again", Toast.LENGTH_SHORT).show();
        }
    }

}
