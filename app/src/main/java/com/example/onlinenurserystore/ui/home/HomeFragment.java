package com.example.onlinenurserystore.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.onlinenurserystore.R;
import com.example.onlinenurserystore.Url.Url;
import com.example.onlinenurserystore.adapter.CategoryAdapter;
import com.example.onlinenurserystore.adapter.Productadapter;
import com.example.onlinenurserystore.adapter.SliderAdapter;
import com.example.onlinenurserystore.api.CategoryApi;
import com.example.onlinenurserystore.api.ProductApi;
import com.example.onlinenurserystore.model.Category;
import com.example.onlinenurserystore.model.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    private static int currentPage=0;
    private static int NUM_PAGES=0;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    ImageView imgCategory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.categoryRecyclerView);
        imgCategory = view.findViewById(R.id.imgCategory);
        viewPager = view.findViewById(R.id.ViewPager);
        SliderAdapter sliderAdapter = new SliderAdapter(getActivity());


        //image slider
        viewPager.setAdapter(sliderAdapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 3000);

        getCategory();
        getProduct();
        return view;
    }

    public void getCategory(){
        CategoryApi categoryApi = Url.getInstance().create(CategoryApi.class);
        Call<List<Category>> listCall =categoryApi.getCategory();
        listCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Toast" + response.code(), Toast.LENGTH_SHORT).show();
                }
                CategoryAdapter categoryAdapter =new CategoryAdapter(getActivity(),response.body());
                recyclerView.setAdapter(categoryAdapter);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getProduct(){
        ProductApi medicineApi = Url.getInstance().create(ProductApi.class);
        Call<List<Products>> listCall =medicineApi.getProduct();
        listCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Toast" + response.code(), Toast.LENGTH_SHORT).show();
                }
                Productadapter itemAdapter =new Productadapter(getActivity(),response.body());
                recyclerView.setAdapter(itemAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }






}
