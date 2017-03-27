package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.itheima.googleplaydemo.R;

/**
 * Created by 龚浩 on 2017/3/27.
 */

public class CategoryRowItem extends LinearLayout {

    public CategoryRowItem(Context context) {
        this(context,null);
    }

    public CategoryRowItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.row_item,this);
    }
}
