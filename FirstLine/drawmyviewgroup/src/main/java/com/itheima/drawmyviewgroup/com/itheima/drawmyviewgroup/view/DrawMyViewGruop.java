package com.itheima.drawmyviewgroup.com.itheima.drawmyviewgroup.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by 龚浩 on 2017/3/13.
 */

public class DrawMyViewGruop extends ViewGroup {
    public DrawMyViewGruop(Context context) {
        this(context, null);
    }

    public DrawMyViewGruop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
