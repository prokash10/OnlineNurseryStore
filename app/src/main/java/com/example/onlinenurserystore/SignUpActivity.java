package com.example.onlinenurserystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.api.UserAPI;
import com.example.onlinenurserystore.broadcast.CreateChannel;
import com.example.onlinenurserystore.model.Users;
import com.example.onlinenurserystore.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManagerCompat;

    EditText etFullName, etUserName, etEmail, etPhoneNo, etPassword;
    ImageButton btnSignUp;
    Button btnLogin;
    CheckBox chkterms;
    int id=1;

    boolean isChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();

        etFullName = findViewById(R.id.FullNameSF);
        etUserName = findViewById(R.id.UserNameSF);
        etEmail = findViewById(R.id.emailSF);
        etPhoneNo = findViewById(R.id.PhoneNoSF);
        etPassword = findViewById(R.id.passwordSF);
        btnSignUp = findViewById(R.id.SignUpSF);
        btnLogin = findViewById(R.id.LoginSF);
        chkterms = findViewById(R.id.checkboxSF);

        chkterms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = true;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //final Bundle bundle = getIntent().getExtras();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etFullName.getText())) {
                    etFullName.setError("Enter Name first");
                    return;
                } else if (TextUtils.isEmpty(etUserName.getText())) {
                    etUserName.setError("Enter UserName first");
                    return;
                } else if (TextUtils.isEmpty(etEmail.getText())) {
                    etEmail.setError("Enter Email first");
                    return;
                } else if (TextUtils.isEmpty(etPhoneNo.getText())) {
                    etPhoneNo.setError("Enter PhoneNumber first");
                    return;
                } else if (TextUtils.isEmpty(etPassword.getText())) {
                    etPassword.setError("Enter Password first");
                    return;
                } else if (isChecked == false) {
                    chkterms.setError("Mandatory Field");
                    return;
                }


                //String phoneNumber = bundle.getString("PhoneNo");

                String FullName = etFullName.getText().toString();
                String UserName = etUserName.getText().toString();
                String Email = etEmail.getText().toString();
                String PhoneNo = etPhoneNo.getText().toString();
                String Password = etPassword.getText().toString();

                Users users = new Users(FullName, UserName, Email, PhoneNo, Password);

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
                        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                DisplayNotification();
            }
        });

    }
    public void DisplayNotification() {

        Notification notification = new NotificationCompat.Builder(this,CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle("Registered")
                .setContentText("Registered Successfully")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }
}
