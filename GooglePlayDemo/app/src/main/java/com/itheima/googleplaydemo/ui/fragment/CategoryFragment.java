package com.itheima.googleplaydemo.ui.fragment;

import android.widget.ListAdapter;

import com.itheima.googleplaydemo.adapter.CategoryAdapter;
import com.itheima.googleplaydemo.bean.CategoryItemBean;
import com.itheima.googleplaydemo.net.GoogleRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class CategoryFragment extends BaseListFragment {

    private List<CategoryItemBean> mDataList;

    @Override
    public void startLoadData() {
        Call<List<CategoryItemBean>> listCall = GoogleRetrofit.getInstance().getApi().listCategory();
        listCall.enqueue(new Callback<List<CategoryItemBean>>() {
            @Override
            public void onResponse(Call<List<CategoryItemBean>> call, Response<List<CategoryItemBean>> response) {
                mDataList = response.body();
                OnSuccess();
            }

            @Override
            public void onFailure(Call<List<CategoryItemBean>> call, Throwable t) {
                onFailer();
            }
        });
    }


    @Override
    ListAdapter onCreateAdapter() {
        CategoryAdapter adapter = new CategoryAdapter(getContext(), mDataList);
        return adapter;
    }
}
