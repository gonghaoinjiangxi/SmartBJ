package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Const;
import com.itheima.googleplaydemo.bean.DetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/30.
 */

public class AppDetaislInfo extends LinearLayout {
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.download_count)
    TextView mDownloadCount;
    @BindView(R.id.version_count)
    TextView mVersionCount;
    @BindView(R.id.app_date)
    TextView mAppDate;
    @BindView(R.id.app_size)
    TextView mAppSize;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;

    public AppDetaislInfo(Context context) {
        this(context, null);
    }

    public AppDetaislInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.info_app_details, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(DetailBean bean) {
        String url = Const.HTTP_IMAGINE_URL + bean.getIconUrl();
        Glide.with(getContext()).load(url).into(mIvIcon);

        mVersionCount.setText(String.format(getResources().getString(R.string.version_code), bean.getVersion()));
        mAppDate.setText(String.format(getResources().getString(R.string.time), bean.getDate()));
        mAppSize.setText(String.format(getResources().getString(R.string.app_size), Formatter.formatFileSize(getContext(), bean.getSize())));
        mDownloadCount.setText(String.format(getResources().getString(R.string.download_count), bean.getDownloadNum()));
        mAppName.setText(bean.getName());
        mRatingBar.setRating(bean.getStars());


    }
}
