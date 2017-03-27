package com.itheima.smartbj02.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public class SettingTabPager extends TabPager{
    public SettingTabPager(Context context) {
        this(context,null);
    }

    public SettingTabPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init() {
        super.init();
        mTvTitle.setText("设置");
        mIvMenu.setVisibility(GONE);
    }
}
