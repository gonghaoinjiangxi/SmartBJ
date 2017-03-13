package com.itheima.drawview.com.itheima.drawview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.itheima.drawview.R;

/**
 * Created by 龚浩 on 2017/3/12.
 */

public class MyView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Path mPath;
    private RectF mRectF;
    private int mProgress;


    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //将图片资源转换为一个bitmap对象

        mPath = new Path();

        //BitmapFactory.decodeResource(getResources(),R.mipmap.3,8);
        //  BitmapFactory.Options options = new BitmapFactory.Options();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.six);
        //创建画笔对象
        mPaint = new Paint();
        //去锯齿
        mPaint.setAntiAlias(true);
        //设置画成空心的
        mPaint.setStyle(Paint.Style.STROKE);
        //加粗边界
        mPaint.setStrokeWidth(5);

        mRectF = new RectF(0, 0, 400, 400);

        int mProgress = 80;
    }

    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //布局
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    //画
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       /*画直线
        int startX = 10 ,startY = 200;
        int endX = 390,endY = 200;
        canvas.drawLine(startX,startY,endX,endY,mPaint);*/

        /*画圆
        int cX = 200 ,cY = 200;
        int radius = 200;
        canvas.drawCircle(cX,cY,radius,mPaint);*/




/*

        //path路径,指定画的路径
        mPath.moveTo(200,0);
        mPath.lineTo(0,400);
        mPath.lineTo(400,400);
        mPath.lineTo(200,0);

        //画出路径
        canvas.drawPath(mPath,mPaint);

        //剪裁出路径
        canvas.clipPath(mPath);
        //画图
        canvas.drawBitmap(mBitmap,10,10,mPaint);
*/

        int startAngle = -90;
        int endAngle = (int) (mProgress * 1.0 * 360 / 100);
        boolean useCenter = false;

        canvas.drawArc(mRectF, startAngle, endAngle, useCenter, mPaint);

    }

    public void setprogress(int progress) {
        mProgress = progress;

    }
}
