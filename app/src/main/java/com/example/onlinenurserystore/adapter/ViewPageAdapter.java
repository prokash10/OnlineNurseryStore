package com.example.onlinenurserystore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.onlinenurserystore.R;
import com.example.onlinenurserystore.model.ScreenItems;

import java.util.List;

public class ViewPageAdapter extends PagerAdapter {
    Context pContext;
    List<ScreenItems> pListScreen;

    public ViewPageAdapter(Context mContext, List<ScreenItems> mListScreen) {
        this.pContext = mContext;
        this.pListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        title.setText(pListScreen.get(position).getTitle());
        description.setText(pListScreen.get(position).getDescription());
        imgSlide.setImageResource(pListScreen.get(position).getScreenimg());

        container.addView(layoutScreen);

        return layoutScreen;

    }

    @Override
    public int getCount() {
        return pListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);

    }

}
