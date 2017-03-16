package com.itheima.drawmyviewgroup.com.itheima.drawmyviewgroup.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import static android.content.ContentValues.TAG;

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
        View child = getChildAt(0);
        //获取到布局文件里面的宽和高
        LayoutParams params = child.getLayoutParams();
        int height = params.height;
        int width = params.width;
        //将布局文件里的宽和高转换成32位形式(前两位代表模式,后30位代表值)
        int chileWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        child.measure(chileWidthMeasureSpec,childHeightMeasureSpec);
        Log.d(TAG, "onMeasure: at:width"+child.getMeasuredWidth()+"at:height:"+child.getMeasuredHeight());

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View at = getChildAt(0);
        at.layout(0,0,at.getMeasuredWidth(),at.getMeasuredHeight());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
