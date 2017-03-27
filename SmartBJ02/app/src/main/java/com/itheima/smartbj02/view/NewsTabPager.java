package com.itheima.smartbj02.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public class NewsTabPager extends TabPager{

    private View mView;

    public NewsTabPager(Context context) {
        this(context,null);
    }

    public NewsTabPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init() {
        super.init();
        mTvTitle.setText("新闻中心");
        setData(0);
    }

    public void setData(int position) {
        mView = null;
        switch (position) {
           case 0:
               mContentFram.removeAllViews();
                mView = getNewsView();
                break;
            case 1:
                mView = new TextView(getContext());
                break;
            case 2:
                mContentFram.removeAllViews();
                mListType.setVisibility(VISIBLE);
                mView = getImgView();
                break;
            case 3:
                break;
        }

        mContentFram.addView(mView);
    }

    private ContentView getNewsView() {
        ContentView view = new ContentView(getContext());
        return view;

    }


    public View getImgView() {
        final ImgView view = new ImgView(getContext());
        mListType.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setData();
            }
        });
        return view;
    }




}
