package com.itheima.drawview2.com.itheima.drawview2.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.itheima.drawview2.R;

/**
 * Created by 龚浩 on 2017/3/12.
 */

public class MyVeiw extends View {

    private Bitmap mToggleBackGround;
    private Bitmap mToggle;
    private Paint mPaint;
    private boolean isOpen;
    private OnTaggleChanged mOnTaggleChanged;

    public MyVeiw(Context context) {
        this(context, null);
    }

    public MyVeiw(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mToggleBackGround = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_background);
        mToggle = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_button);

        //创建画笔对象
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(mToggleBackGround.getWidth(), mToggleBackGround.getHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        draw(canvas);
    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(mToggleBackGround, 0, 0, mPaint);

        if (isOpen) {
            canvas.drawBitmap(mToggle, 0, 0, mPaint);
            isOpen = false;
        } else {
            canvas.drawBitmap(mToggle, mToggleBackGround.getWidth() - mToggle.getWidth(), 0, mPaint);
            isOpen = true;
        }
        mOnTaggleChanged.isChanged(isOpen);

    }

    public void setOnClickListener() {
        invalidate();
    }

    public void setOnTaggleChangedListener(OnTaggleChanged taggleChangedListener) {
        mOnTaggleChanged = taggleChangedListener;
    }

    public interface OnTaggleChanged {
        void isChanged(boolean isOpen);
    }

}
