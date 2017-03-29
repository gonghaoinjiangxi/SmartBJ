package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Const;
import com.itheima.googleplaydemo.bean.SubjectItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public class SubjectItemView extends LinearLayout {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;

    public SubjectItemView(Context context) {
        this(context, null);
    }

    public SubjectItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.subject_view, this);
        ButterKnife.bind(this,this);
    }

    public void setData(SubjectItemBean bean) {
        mTvDesc.setText(bean.getDes());
        String url = Const.HTTP_IMAGINE_URL + bean.getUrl();
        Glide.with(getContext()).load(url).into(mIvIcon);
    }
}
