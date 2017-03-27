package com.itheima.smartbj02.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.smartbj02.R;
import com.itheima.smartbj02.event.MySlidingMenuClickEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public class TabPager extends LinearLayout {
    @BindView(R.id.iv_menu)
    ImageView mIvMenu;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.content_fram)
    FrameLayout mContentFram;
    @BindView(R.id.switch_photos)
    ImageView mListType;

    public TabPager(Context context) {
        this(context, null);
    }

    public TabPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.tab_pager, this);
        ButterKnife.bind(this, this);
        init();
    }


    public void init() {

    }

    private OnMenuClickListener mMenuClickListener;

    public void setOnMenuClickListener(OnMenuClickListener listener) {
        mMenuClickListener = listener;
    }

    @OnClick(R.id.iv_menu)
    public void onClick() {
        if (mMenuClickListener != null) {
            onPublishEventOnMainThread();
        }
    }

//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void onPostingEvent(MySlidingMenuClickEvent event) {
//        Log.d(TAG, "onPostingEvent: " + Thread.currentThread().getName());
//    }

    public void onPublishEventOnMainThread() {
        MySlidingMenuClickEvent event = new MySlidingMenuClickEvent("msg from publisher main thread");
        EventBus.getDefault().post(event);
    }

    public void setData(int position) {
    }

    public interface OnMenuClickListener {
        void onMenuClick();
    }

}
