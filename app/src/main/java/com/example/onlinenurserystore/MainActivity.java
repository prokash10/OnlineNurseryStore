package com.example.onlinenurserystore;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.onlinenurserystore.ui.account.AccountFragment;
import com.example.onlinenurserystore.ui.cart.CartFragment;
import com.example.onlinenurserystore.ui.home.HomeFragment;
import com.example.onlinenurserystore.ui.order.OrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView =findViewById(R.id.nav_view);
        Bundle bundle=getIntent().getExtras();


        if(bundle!=null) {
            String name = bundle.getString("name");
            if (name.equals("home")) {
                bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
                openFragment(new HomeFragment());


            } else if (name.equals("account")) {
                bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
                bottomNavigationView.setSelectedItemId(R.id.navigation_account);
                openFragment(new AccountFragment());

            } else if (name.equals("cart")){
                bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
                bottomNavigationView.setSelectedItemId(R.id.navigation_cart);
                openFragment(new CartFragment());

            }

        }else{

            bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
            openFragment(new HomeFragment());

        }






    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            openFragment(new HomeFragment());
                            return true;
                        case R.id.navigation_message:
                            openFragment(new OrderFragment());
                            return true;
                        case R.id.navigation_cart:
                            openFragment(new CartFragment());
                            return true;
                        case R.id.navigation_account:
                            openFragment(new AccountFragment());
                            return true;

                    }
                    return false;
                }
            };

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }



}
