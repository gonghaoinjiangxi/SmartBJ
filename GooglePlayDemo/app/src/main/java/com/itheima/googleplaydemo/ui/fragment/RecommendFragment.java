package com.itheima.googleplaydemo.ui.fragment;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.itheima.googleplaydemo.adapter.RecommendAdapter;
import com.itheima.googleplaydemo.net.Api;
import com.itheima.googleplaydemo.net.GoogleRetrofit;
import com.itheima.googleplaydemo.widgest.StellarMap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class RecommendFragment extends BaseFragment {

    private Api mApi;
    private List<String> mData;

    @Override
    public void startLoadData() {
        mApi = GoogleRetrofit.getInstance().getApi();
        Call<List<String>> listCall = mApi.listRecommend();
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                mData = response.body();
                OnSuccess();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                onFailer();
                TabLayout layout = new TabLayout(getContext());
            }
        });
    }

    @Override
    public View getContentView() {
        RecommendAdapter adapter = new RecommendAdapter(getContext(), mData);
        StellarMap map = new StellarMap(getContext());
        map.setAdapter(adapter);
        map.setGroup(0,false);
        map.setRegularity(15,30);
        return map;
    }
}
