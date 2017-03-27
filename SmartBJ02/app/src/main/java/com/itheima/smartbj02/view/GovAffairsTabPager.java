package com.itheima.smartbj02.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public class GovAffairsTabPager extends TabPager{
    public GovAffairsTabPager(Context context) {
        this(context,null);
    }

    public GovAffairsTabPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init() {
        super.init();
        mTvTitle.setText("政务");
    }

    public void setData(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
