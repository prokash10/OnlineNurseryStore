package com.example.onlinenurserystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.api.UserAPI;
import com.example.onlinenurserystore.model.Users;
import com.example.onlinenurserystore.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText Name,Email,Password;
    Button btnSignup;

    Button btnback;
    boolean ischecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name = findViewById(R.id.etFullName);
        Email = findViewById(R.id.etemail);
        Password = findViewById(R.id.etpassword);
        btnSignup = findViewById(R.id.btnsubmit);

        btnback=findViewById(R.id.btnbak);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });

        final Bundle bundle = getIntent().getExtras();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Name.getText().toString()))
                {
                    Name.setError("Mandatory Field");
                    return;
                }
                else if (TextUtils.isEmpty(Password.getText().toString())){
                    Password.setError("Mandatory Field");
                    return;
                }
                else if (TextUtils.isEmpty(Email.getText().toString())){
                    Email.setError("Mandatory Field");
                    return;
                }
                else if (ischecked = false){
                    btnSignup.setError("Mandatory Field");
                    return;
                }

                String PhoneNo = bundle.getString("PhoneNo");

                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                Users users=new Users(name,email,password,PhoneNo);

                UserAPI usersAPI = Url.getInstance().create(UserAPI.class);
                Call<SignUpResponse> signUpCall = usersAPI.registerUser(users);

                signUpCall.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(SignUpActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
