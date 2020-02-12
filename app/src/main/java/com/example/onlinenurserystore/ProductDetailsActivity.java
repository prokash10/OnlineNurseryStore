package com.example.onlinenurserystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlinenurserystore.Url.Url;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    private TextView tvTitle, tvDescription, tvPrice;
    private ImageView imgPlant;
    int id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvTitle=findViewById(R.id.tvtitle);
        tvDescription=findViewById(R.id.tvDescription);
        tvPrice=findViewById(R.id.tvPrice);
        imgPlant =findViewById(R.id.imgplants);

        Intent intent=getIntent();
        final String Name=intent.getExtras().getString("name");
        final float Price=intent.getExtras().getFloat("price");
        final String img=intent.getExtras().getString("image");
        final String description=intent.getExtras().getString("description");
        final String Plantid=intent.getExtras().getString("id");

        tvTitle.setText(Name);
        tvPrice.setText("Rs"+Price);
        tvDescription.setText(description);
        Picasso.get().load(Url.base_url_image+img).into(imgPlant);

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
