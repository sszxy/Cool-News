package com.example.xinwin;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 张翔宇 on 2018/4/8.
 */

public class Myviewpageradapter extends PagerAdapter{
    List<View> viewList;
    List<String> tablist;
    public Myviewpageradapter(List<View> views, List<String> tablists){
        viewList=views;
        tablist=tablists;
    }
    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=viewList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position);
    }
}
