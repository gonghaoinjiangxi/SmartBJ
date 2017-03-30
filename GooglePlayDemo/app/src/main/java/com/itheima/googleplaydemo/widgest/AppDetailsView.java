package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.DetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/30.
 */

public class AppDetailsView extends LinearLayout {
    @BindView(R.id.app_infos)
    AppDetaislInfo mAppInfos;

    public AppDetailsView(Context context) {
        this(context, null);
    }

    public AppDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.details_view, this);
        ButterKnife.bind(this,this);
    }

    public void bindView(DetailBean bean) {
        mAppInfos.bindView(bean);

    }
}
