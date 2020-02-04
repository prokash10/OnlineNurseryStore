package com.example.onlinenurserystore.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.onlinenurserystore.R;
import com.example.onlinenurserystore.adapter.CategoryAdapter;
import com.example.onlinenurserystore.adapter.SliderAdapter;
import com.example.onlinenurserystore.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    private static int currentPage=0;
    private static int NUM_PAGES=0;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    ImageView imgCategory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView=view.findViewById(R.id.categoryRecyclerView);
        imgCategory=view.findViewById(R.id.imgCategory);
        viewPager=view.findViewById(R.id.ViewPager);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        SliderAdapter sliderAdapter=new SliderAdapter(getActivity());

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


        List<Category> categoryList=new ArrayList<>();
        categoryList.add(new Category(R.drawable.desert,"Desert Plants"));
        categoryList.add(new Category(R.drawable.flowers,"Flowers"));
        categoryList.add(new Category(R.drawable.water,"Water plants"));
        categoryList.add(new Category(R.drawable.office,"PharPlants for Offices"));
        categoryList.add(new Category(R.drawable.indoor,"Indore plant"));
        categoryList.add(new Category(R.drawable.potplant,"Pot plants"));

        final CategoryAdapter categoryAdapter=new CategoryAdapter(getContext(),categoryList);
        recyclerView.setAdapter(categoryAdapter);


        return view;
    }


}
