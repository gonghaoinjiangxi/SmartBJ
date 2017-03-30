package com.itheima.googleplaydemo.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.itheima.googleplaydemo.app.Const;
import com.itheima.googleplaydemo.bean.DetailBean;
import com.itheima.googleplaydemo.net.GoogleRetrofit;
import com.itheima.googleplaydemo.widgest.AppDetailsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/3/30.
 */

public class AppDetailContent extends BaseFragment {

    private DetailBean mBean;

    @Override
    public void startLoadData() {
        Intent intent = getActivity().getIntent();
        String packageName = intent.getStringExtra(Const.PACAKAGE_NAME);
        Call<DetailBean> listDetail = GoogleRetrofit.getInstance().getApi().listDetail(packageName);
        listDetail.enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                mBean = response.body();
                OnSuccess();
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {

            }
        });
    }

    @Override
    public View getContentView() {
        AppDetailsView view = new AppDetailsView(getContext());
        view.bindView(mBean);
        return view;
    }
}
