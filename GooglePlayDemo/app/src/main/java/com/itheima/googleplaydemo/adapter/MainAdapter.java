package com.itheima.googleplaydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 龚浩 on 2017/3/26.
 */
public class MainAdapter extends FragmentPagerAdapter{

    private String[] mDataList;

    public MainAdapter(String[] dataList, FragmentManager fm) {
        super(fm);

        mDataList = dataList;
    }

    @Override
    public Fragment getItem(int position) {
        return new Fragment();
    }

    @Override
    public int getCount() {
        return mDataList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataList[position];
    }
}
