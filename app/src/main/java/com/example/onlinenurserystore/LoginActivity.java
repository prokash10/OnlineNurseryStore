package com.example.onlinenurserystore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinenurserystore.bll.LoginBll;
import com.example.onlinenurserystore.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etemail,etpassword;
    private TextView tvCR;
    private Button btnLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail=findViewById(R.id.etem);
        etpassword=findViewById(R.id.etp);
        tvCR=findViewById(R.id.tvCR);
        btnLog=findViewById(R.id.btnLog);


      tvCR.setOnClickListener(this);
//        btnLog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//
//        });
         btnLog.setOnClickListener(new View.OnClickListener() {
           @Override
         public void onClick(View v) {
           login();
        }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if (v==tvCR){
            Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
            Pair[] pairs=new Pair[1];
            pairs[0]=new Pair<View,String>(tvCR,"tvCr");
            ActivityOptions activityOptions=ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
            startActivity(intent,activityOptions.toBundle());
        }

    }

    private void login() {
        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();

        LoginBll loginBLL = new LoginBll();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(email, password)) {

            SaveUsernamePassword();

            Toast.makeText(this, "Login Succssful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etemail.requestFocus();
        }


    }
    private void SaveUsernamePassword() {
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username", etemail.getText().toString().trim());
        editor.putString("password", etpassword.getText().toString().trim());
        editor.commit();
    }
}
