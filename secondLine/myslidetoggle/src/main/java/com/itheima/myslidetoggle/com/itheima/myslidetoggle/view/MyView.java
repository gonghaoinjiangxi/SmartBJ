package com.itheima.myslidetoggle.com.itheima.myslidetoggle.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.itheima.myslidetoggle.R;

import static android.content.ContentValues.TAG;

/**
 * Created by 龚浩 on 2017/3/13.
 */

public class MyView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;
    private int mWidth;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_button);
        mWidth = mBitmap.getWidth();

        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(widthMeasureSpec,mBitmap.getHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap,0,0,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                float downX = event.getX();

                if(downX <= mWidth/2 || downX > mWidth) {
                    return  false;
                }

                scrollTo((int) -(downX - mWidth/2),0);

                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                if(moveX >= mWidth/2 && moveX <=  getWidth()-mWidth/2) {

                    scrollTo((int) -(moveX - mWidth / 2), 0);
                }

                break;
            case MotionEvent.ACTION_UP:
                int upX = (int) event.getX();

                Log.d(TAG, "onTouchEvent: upx:"+upX+" getWidth() - mWidth/2:"+ (getWidth() - mWidth/2));

                if(upX >= (getWidth() - mWidth/2)) {
                    if(mUnLockListener != null) {
                        mUnLockListener.UnLock();
                    }
                }
                 scrollTo(0,0);
                break;
        }



        return true;
    }

    private  OnUnLockListener mUnLockListener;

    public void setOnUnLockListener(OnUnLockListener listener) {
        mUnLockListener = listener;
    }

    public interface  OnUnLockListener {
        void UnLock();
    }
}
