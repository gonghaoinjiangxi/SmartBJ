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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/27.
 */

public class CategoryRowItem extends LinearLayout {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public CategoryRowItem(Context context) {
        this(context, null);
    }

    public CategoryRowItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.row_item, this);
        ButterKnife.bind(this,this);
    }

    public void setData(String name1, String url) {
        mTvTitle.setText(name1);
        String imgUrl = Const.HTTP_URL + "image?name=" +url;
        Glide.with(getContext()).load(imgUrl).centerCrop().placeholder(R.drawable.ic_default).into(mIvIcon);
    }
}
