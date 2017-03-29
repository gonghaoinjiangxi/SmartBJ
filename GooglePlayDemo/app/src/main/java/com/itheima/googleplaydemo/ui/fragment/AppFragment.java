package com.itheima.googleplaydemo.ui.fragment;

import com.itheima.googleplaydemo.bean.AppListItemBean;
import com.itheima.googleplaydemo.net.GoogleRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class AppFragment extends BaseAppListFragment {


    private List<AppListItemBean> mDataList;

    @Override
    public void startLoadData() {
        super.startLoadData();
        Call<List<AppListItemBean>> listApp = GoogleRetrofit.getInstance().getApi().listApp(0);
        listApp.enqueue(new Callback<List<AppListItemBean>>() {
            @Override
            public void onResponse(Call<List<AppListItemBean>> call, Response<List<AppListItemBean>> response) {
                OnSuccess();
                getDataList().addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<AppListItemBean>> call, Throwable t) {
                onFailer();
            }
        });
    }

    @Override
    void loadMoreData() {
        Call<List<AppListItemBean>> listApp = GoogleRetrofit.getInstance().getApi().listApp(getDataList().size());
        listApp.enqueue(new Callback<List<AppListItemBean>>() {
            @Override
            public void onResponse(Call<List<AppListItemBean>> call, Response<List<AppListItemBean>> response) {
                getDataList().addAll(response.body());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AppListItemBean>> call, Throwable t) {

            }
        });
    }
}
