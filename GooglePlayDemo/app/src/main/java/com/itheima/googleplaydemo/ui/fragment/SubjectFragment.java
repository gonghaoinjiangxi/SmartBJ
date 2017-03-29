package com.itheima.googleplaydemo.ui.fragment;

import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.itheima.googleplaydemo.adapter.SubjectAdapter;
import com.itheima.googleplaydemo.bean.SubjectItemBean;
import com.itheima.googleplaydemo.net.GoogleRetrofit;
import com.itheima.googleplaydemo.utils.ToastUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class SubjectFragment extends BaseLoadMoreListFragment {

    private List<SubjectItemBean> mDataList;

    @Override
    public void startLoadData() {
        Call<List<SubjectItemBean>> listSubject = GoogleRetrofit.getInstance().getApi().listSubject(0);
        listSubject.enqueue(new Callback<List<SubjectItemBean>>() {
            @Override
            public void onResponse(Call<List<SubjectItemBean>> call, Response<List<SubjectItemBean>> response) {
                mDataList = response.body();
                ToastUtils.makeText(getContext(),mDataList.get(1).getDes());
                OnSuccess();
            }

            @Override
            public void onFailure(Call<List<SubjectItemBean>> call, Throwable t) {
                onFailer();
            }
        });
    }

    @Override
    void loadMoreData() {
        Call<List<SubjectItemBean>> listSubject = GoogleRetrofit.getInstance().getApi().listSubject(mDataList.size());
        listSubject.enqueue(new Callback<List<SubjectItemBean>>() {
            @Override
            public void onResponse(Call<List<SubjectItemBean>> call, Response<List<SubjectItemBean>> response) {
                mDataList.addAll(response.body());
                BaseAdapter adapter = (BaseAdapter) getAdapter();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SubjectItemBean>> call, Throwable t) {
                ToastUtils.makeText(getContext(),"数据请求失败");
            }
        });

    }

    @Override
    ListAdapter onCreateAdapter() {
        return new SubjectAdapter(getContext(),mDataList);
    }
}
