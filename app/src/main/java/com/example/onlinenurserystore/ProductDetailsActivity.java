package com.example.onlinenurserystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.onlinenurserystore.ui.home.HomeFragment;

public class ProductDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.cart:
                intent=new Intent(this, MainActivity.class);
                intent.putExtra("name","cart");
                startActivity(intent);
                return true;
            case R.id.Home:
                intent=new Intent(this, MainActivity.class);
                intent.putExtra("name","home");
                startActivity(intent);
                return true;
            case R.id.Account:
                intent=new Intent(this, MainActivity.class);
                intent.putExtra("name","account");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
