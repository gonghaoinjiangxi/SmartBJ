package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.itheima.googleplaydemo.R;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public class AppListItemView extends RelativeLayout {
    public AppListItemView(Context context) {
        this(context,null);
    }

    public AppListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.app_item,this);
    }
}
