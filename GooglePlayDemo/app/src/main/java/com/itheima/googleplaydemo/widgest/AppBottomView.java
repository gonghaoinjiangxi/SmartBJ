package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.DetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/31.
 */

public class AppBottomView extends LinearLayout {
    @BindView(R.id.btn_collect)
    Button mBtnCollect;
    @BindView(R.id.btn_downLoad)
    Button mBtnDownLoad;
    @BindView(R.id.btn_share)
    Button mBtnShare;

    public AppBottomView(Context context) {
        this(context, null);
    }

    public AppBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.bottom_view, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(DetailBean bean) {

    }
}
