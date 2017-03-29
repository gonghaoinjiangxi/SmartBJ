package com.itheima.googleplaydemo.ui.fragment;

import android.util.Log;
import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.bean.AppListItemBean;
import com.itheima.googleplaydemo.net.GoogleRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class GameFragment extends BaseAppListFragment {

    private static final String TAG = "GameFragment";
    private BaseAdapter mAdapter;

    @Override
    public void startLoadData() {
        super.startLoadData();
        Call<List<AppListItemBean>> listGame = GoogleRetrofit.getInstance().getApi().listGame(0);
        listGame.enqueue(new Callback<List<AppListItemBean>>() {
            @Override
            public void onResponse(Call<List<AppListItemBean>> call, Response<List<AppListItemBean>> response) {
                OnSuccess();
                Log.d(TAG, "onResponse: ==============================" +getDataList().size());
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
        Call<List<AppListItemBean>> listGame = GoogleRetrofit.getInstance().getApi().listGame(getDataList().size());
        listGame.enqueue(new Callback<List<AppListItemBean>>() {
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
