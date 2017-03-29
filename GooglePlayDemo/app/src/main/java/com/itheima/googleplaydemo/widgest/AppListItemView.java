package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Const;
import com.itheima.googleplaydemo.bean.AppListItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public class AppListItemView extends RelativeLayout {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.app_rating_bar)
    RatingBar mAppRatingBar;
    @BindView(R.id.app_size)
    TextView mAppSize;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;

    public AppListItemView(Context context) {
        this(context, null);
    }

    public AppListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.app_item, this);
        ButterKnife.bind(this, this);
    }

    public void setData(AppListItemBean bean) {
        mAppName.setText(bean.getName());
        mAppRatingBar.setRating((float) bean.getStars());
        mAppSize.setText(Formatter.formatFileSize(getContext(),bean.getSize()));
        String url = Const.HTTP_IMAGINE_URL + bean.getIconUrl();
        Glide.with(getContext()).load(url).into(mIvIcon);
        mTvDesc.setText(bean.getDes());
    }
}
