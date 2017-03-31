package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.DetailBean;
import com.itheima.googleplaydemo.utils.AnimationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/3/30.
 */

public class AppSummaryView extends LinearLayout {

    private static final int MAX_LINES = 7;
    @BindView(R.id.app_desc)
    TextView mAppDesc;
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.iv_arrow)
    ImageView mIvArrow;
    private boolean isOpen;
    private int mHeight;
    private static final String TAG = "AppSummaryView";
    private int measureHeight;

    public AppSummaryView(Context context) {
        this(context, null);
    }

    public AppSummaryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.app_summary, this);
        ButterKnife.bind(this, this);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mHeight = mAppDesc.getMeasuredHeight();
                int lineCount = mAppDesc.getLineCount();
                if (lineCount > MAX_LINES) {
                    //初始化行
                    mAppDesc.setLines(MAX_LINES);
                }else {
                    mAppDesc.setLines(lineCount);
                }
                mAppDesc.measure(0,0);
                final int finalHaight =  mAppDesc.getMeasuredHeight();
                measureHeight = finalHaight;
            }
        });



    }

    public void bindView(DetailBean bean) {
        mAppDesc.setText(bean.getDes());
        mAppName.setText(bean.getName());

    }

    @OnClick(R.id.iv_arrow)
    public void onClick() {
        toggle();
        isOpen = !isOpen;
    }

    private void toggle() {

        if(isOpen) {
            Log.d(TAG, "toggle: ==="+measureHeight);
            AnimationUtil.isOpen(mHeight,measureHeight,mAppDesc);
            AnimationUtil.isRotation(0,-180,mIvArrow);
        }else {

            AnimationUtil.isOpen(measureHeight,mHeight,mAppDesc);
            AnimationUtil.isRotation(-180,0,mIvArrow);
        }
    }
}
