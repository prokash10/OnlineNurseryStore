package com.example.onlinenurserystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.onlinenurserystore.ui.account.AccountFragment;
import com.example.onlinenurserystore.ui.home.HomeFragment;

public class MenuActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.navigation_home, new HomeFragment()).commit();
                return true;
            case R.id.navigation_message:
                FragmentManager FM= getSupportFragmentManager();
                FM.beginTransaction().replace(R.id.navigation_message, new AccountFragment()).commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


