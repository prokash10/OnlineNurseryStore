package com.example.onlinenurserystore.ui.account;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinenurserystore.R;
import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.api.UserAPI;
import com.example.onlinenurserystore.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private TextView username, number;
    private Button btnmap;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        username = root.findViewById(R.id.username);
        number = root.findViewById(R.id.number);
        btnmap=root.findViewById(R.id.map);

//        btnmap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MapsActivity.class, AccountFragment.this);
//                startActivity(intent);
//
//            }
//        });

        UserAPI userApi= Url.getInstance().create(UserAPI.class);
        final Call<Users> userCall =userApi.getUserDetails(Url.token);

        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    username.setText(response.body().getEmail());
                    number.setText(response.body().getPhoneNumber());
                    return;
                }
                else {
                    Toast.makeText(getActivity(), "Error ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }



}

