package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.itheima.googleplaydemo.R;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public class downLoadView extends LinearLayout {


    public downLoadView(Context context) {
        this(context,null);
    }

    public downLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.down_load_view,this);
    }
}
