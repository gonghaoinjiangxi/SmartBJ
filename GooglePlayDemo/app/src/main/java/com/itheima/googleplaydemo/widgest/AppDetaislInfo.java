package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.itheima.googleplaydemo.R;

/**
 * Created by 龚浩 on 2017/3/30.
 */

public class AppDetaislInfo extends LinearLayout {
    public AppDetaislInfo(Context context) {
        this(context,null);
    }

    public AppDetaislInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.info_app_details,this);
    }
}
