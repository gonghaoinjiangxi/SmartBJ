package com.itheima.smartbj02.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public class HomeTabPager extends TabPager{
    private static final String TAG = "HomeTabPager";
    public HomeTabPager(Context context) {
        this(context,null);
    }

    public HomeTabPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void init() {
        super.init();
        mIvMenu.setVisibility(GONE);
        mTvTitle.setText("首页");
    }
}
