package com.example.onlinenurserystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.api.UserAPI;
import com.example.onlinenurserystore.model.Users;
import com.example.onlinenurserystore.ui.dashboard.DashboardFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    EditText etFullNameUpdate,etUserNameupdate,etEmailUpdate,etPhoneNoUpdate;
    Button btnUpdate;
    TextView tvid;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        back = findViewById(R.id.profileback);
        btnUpdate = findViewById(R.id.btnUpdate);
        etFullNameUpdate = findViewById(R.id.etUpdateFullName);
        etUserNameupdate = findViewById(R.id.etUpdateUserName);
        etEmailUpdate = findViewById(R.id.etUpdateemail);
        etPhoneNoUpdate = findViewById(R.id.etUpdatePhone);
        tvid = findViewById(R.id.tvId);

        UserAPI usersAPI = Url.getInstance().create(UserAPI.class);
        final Call<Users> usersCall = usersAPI.getUserDetails(Url.token);

        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    etFullNameUpdate.setText(response.body().getName());
                    etUserNameupdate.setText(response.body().getUserName());
                    etEmailUpdate.setText(response.body().getEmail());
//                  etPasswordUpdate.setText(response.body().getPassword());
                    etPhoneNoUpdate.setText(response.body().getPhoneNumber());
                    return;
                }
                else {
                    Toast.makeText(EditProfileActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FullName = etFullNameUpdate.getText().toString();
                String UserName = etUserNameupdate.getText().toString();
                String Email = etEmailUpdate.getText().toString();
                String PhoneNo = etPhoneNoUpdate.getText().toString();
                Users users = new Users(FullName,UserName,Email,PhoneNo);
                UserAPI usersAPI1 = Url.getInstance().create(UserAPI.class);
                final Call<Users> usersCall1 = usersAPI1.UpdateDetails(Url.token, users);

                usersCall1.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(EditProfileActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(EditProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(EditProfileActivity.this, ProductDetailsActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    }

