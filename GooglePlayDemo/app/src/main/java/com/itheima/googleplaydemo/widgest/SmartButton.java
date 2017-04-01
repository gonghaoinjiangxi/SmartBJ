package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.DetailBean;
import com.itheima.googleplaydemo.bean.DownLoadInfo;
import com.itheima.googleplaydemo.net.DownLoadManager;

/**
 * Created by 龚浩 on 2017/3/31.
 */

public class SmartButton extends Button {
    private static final String TAG = "SmartButton";

    private Paint mPaint;
    private float mRight;
    private Context mContext;
    private int mMax;

    public SmartButton(Context context) {
        this(context, null);
    }

    public SmartButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, mRight, getMeasuredHeight(), mPaint);
        super.onDraw(canvas);
    }
    public void  setMax(int max) {
        mMax = max;
    }

    public void setProgress(int progress) {
        float rate = (float) ((progress*1.0) / mMax);
        setText((int)(100*rate) + "%");
        mRight = rate * getMeasuredWidth();
        Log.d(TAG, "setProgress: ======"+mRight);
        invalidate();
    }

    public void SyncStatus(DetailBean bean) {
        DownLoadInfo info = DownLoadManager.getInstant().initDownloadInfo(mContext,bean);
        info.getStatus();
        updateStatus(info);
    }

    private void updateStatus(DownLoadInfo info) {

        switch (info.getStatus()) {
            case DownLoadManager.STATE_UN_DOWNLOAD:
                setText(getResources().getString(R.string.download));

                break;
            case DownLoadManager.STATE_DOWNLOADING:

                break;
            case DownLoadManager.STATE_PAUSE:
                setText(getResources().getString(R.string.continue_download));
                break;
            case DownLoadManager.STATE_WAITING:
                setText(getResources().getString(R.string.waiting));
                break;
            case DownLoadManager.STATE_FAILED:
                setText(getResources().getString(R.string.retry));
                break;
            case DownLoadManager.STATE_DOWNLOADED:
                setText(getResources().getString(R.string.install));
                break;
            case DownLoadManager.STATE_INSTALLED:
                setText(getResources().getString(R.string.open));
                break;

        }
    }
}
