package com.itheima.googleplaydemo.widgest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Const;
import com.itheima.googleplaydemo.bean.DetailBean;
import com.itheima.googleplaydemo.utils.AnimationUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/3/30.
 */

public class AppSecurityView extends LinearLayout {
    @BindView(R.id.tab_img_container)
    LinearLayout mTabImgContainer;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    @BindView(R.id.iv_arrow)
    ImageView mIvArrow;
    private static final String TAG = "AppSecurityView";

    private boolean isOpen = false;

    public AppSecurityView(Context context) {
        this(context, null);
    }

    public AppSecurityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.security_view, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(DetailBean bean) {
        List<DetailBean.SafeBean> safe = bean.getSafe();
        for (int i = 0; i < safe.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            addTopImage(safe, i, imageView);
            mTabImgContainer.addView(imageView);

            LinearLayout linearLayout = new LinearLayout(getContext());
            addBottomLinear(safe, i, linearLayout);

            mLlContainer.addView(linearLayout);
        }
    }

    //将顶部的tab图片加进view中
    private void addTopImage(List<DetailBean.SafeBean> safe, int i, ImageView imageView) {
        String url = Const.HTTP_IMAGINE_URL + safe.get(i).getSafeUrl();
        Glide.with(getContext()).load(url).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(imageView);
    }

    //将下面的LinearLayout添加进view中
    private void addBottomLinear(List<DetailBean.SafeBean> safe, int i, LinearLayout linearLayout) {
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        ImageView bottomImage = new ImageView(getContext());
        bottomImage.setPadding(0, getResources().getDimensionPixelOffset(R.dimen.middle_size),0, 0);
        String bottomUrl = Const.HTTP_IMAGINE_URL + safe.get(i).getSafeDesUrl();
        Glide.with(getContext()).load(bottomUrl).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(bottomImage);
        linearLayout.addView(bottomImage);
        TextView textView = new TextView(getContext());
        textView.setPadding(0, getResources().getDimensionPixelOffset(R.dimen.middle_size), 0, 0);
        textView.setText(safe.get(i).getSafeDes());
        if(safe.get(i).getSafeDesColor() != 0) {
            textView.setTextColor(getResources().getColor(R.color.app_detail_safe_warning));
        }else {
            textView.setTextColor(getResources().getColor(R.color.app_detail_safe_normal));
        }
        linearLayout.addView(textView);
    }

    @OnClick(R.id.iv_arrow)
    public void onClick() {
        toggle();

        isOpen = !isOpen;
    }

    private void toggle() {

        int measureSpecWid = MeasureSpec.makeMeasureSpec(getResources().getDisplayMetrics().widthPixels, MeasureSpec.UNSPECIFIED);
        int measureSpecHei = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        mLlContainer.measure(measureSpecWid, measureSpecHei);
        if (isOpen) {
            AnimationUtil.isOpen(mLlContainer.getMeasuredHeight(),0,mLlContainer);
            AnimationUtil.isRotation(0,-180,mIvArrow);
        } else {
            AnimationUtil.isOpen(0,mLlContainer.getMeasuredHeight(),mLlContainer);
            AnimationUtil.isRotation(-180,0,mIvArrow);
        }
    }
}
