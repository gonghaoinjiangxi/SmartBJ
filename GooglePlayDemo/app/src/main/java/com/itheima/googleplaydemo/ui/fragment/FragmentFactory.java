package com.itheima.googleplaydemo.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class FragmentFactory {

    private static FragmentFactory sFragmentFactory;
    private static final int TAB_HOME  = 0;
    private static final int TAB_APP  = 1;
    private static final int TAB_GAME  = 2;
    private static final int TAB_SUBJECT  = 3;
    private static final int TAB_RECOMMEND  = 4;
    private static final int TAB_CATEGROEY  = 5;
    private static final int TAB_HOT = 6;


    private FragmentFactory() {};

    public static FragmentFactory getInstant() {
        if(sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if(sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }
        return sFragmentFactory;
    }


    //根据不同的位置创建不同的fragment
    public static Fragment getFragment(int position) {
        switch (position) {
            case TAB_HOME:
                return new HomeFragment();
            case TAB_APP:
                return new AppFragment();
            case TAB_GAME:
                return new GameFragment();
            case TAB_SUBJECT:
                return new SubjectFragment();
            case TAB_RECOMMEND:
                return new RecommendFragment();
            case TAB_CATEGROEY:
                return new CategoryFragment();
            case TAB_HOT:
                return new HotFragment();
        }
        return null;
    }
}
