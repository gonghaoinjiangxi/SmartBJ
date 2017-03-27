package com.itheima.googleplaydemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.itheima.googleplaydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public abstract class BaseFragment extends Fragment {

    @BindView(R.id.pro_bar)
    ProgressBar mProBar;
    @BindView(R.id.btn_refresh)
    Button mBtnRefresh;
    @BindView(R.id.ll_error)
    LinearLayout mLlError;
    @BindView(R.id.content_main)
    FrameLayout mContentMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.base_frag, null);
        ButterKnife.bind(this, root);
        startLoadData();
        return root;
    }


    public abstract void startLoadData() ;

    //当链接网络数据成功时
    public void OnSuccess() {
        mProBar.setVisibility(View.GONE);
        mContentMain.addView(getContentView());
    }

    //当链接网络数据失败时
    public void onFailer() {
        mProBar.setVisibility(View.GONE);
        mLlError.setVisibility(View.VISIBLE);
    }

    public abstract View getContentView();


    @OnClick(R.id.btn_refresh)
    public void onClick() {
        mProBar.setVisibility(View.VISIBLE);
        mLlError.setVisibility(View.GONE);
        startLoadData();
    }
}
