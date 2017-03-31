package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by 龚浩 on 2017/3/31.
 */

public class SmartButton extends Button {

    private Paint mPaint;
    private float mRight;

    public SmartButton(Context context) {
        this(context,null);
    }

    public SmartButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
      canvas.drawRect(0,0,mRight,getMeasuredHeight(),mPaint);
       super.onDraw(canvas);
    }

    public void setProgress(int progress) {
        mRight  = progress * getMeasuredWidth() / 100 ;
        mRight = 100;
        invalidate();
    }
}
