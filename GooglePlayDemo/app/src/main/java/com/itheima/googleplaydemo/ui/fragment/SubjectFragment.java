package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;

import com.itheima.googleplaydemo.bean.CategoryItemBean;
import com.itheima.googleplaydemo.net.GoogleRetrofit;
import com.itheima.googleplaydemo.utils.ToastUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class SubjectFragment extends BaseFragment {

    private List<CategoryItemBean> mDataList;

    @Override
    public void startLoadData() {
        Call<List<CategoryItemBean>> listSubject = GoogleRetrofit.getInstance().getApi().listSubject(0);
        listSubject.enqueue(new Callback<List<CategoryItemBean>>() {
            @Override
            public void onResponse(Call<List<CategoryItemBean>> call, Response<List<CategoryItemBean>> response) {
                mDataList = response.body();
                ToastUtils.makeText(getContext(),mDataList.get(4).getTitle());
                OnSuccess();
            }

            @Override
            public void onFailure(Call<List<CategoryItemBean>> call, Throwable t) {
                onFailer();
            }
        });
    }

    @Override
    public View getContentView() {
        return null;
    }
}
