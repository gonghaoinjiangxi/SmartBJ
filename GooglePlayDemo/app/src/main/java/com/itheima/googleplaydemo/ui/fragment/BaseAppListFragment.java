package com.itheima.googleplaydemo.ui.fragment;

import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.adapter.BaseAppListAdapter;
import com.itheima.googleplaydemo.bean.AppListItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public abstract class BaseAppListFragment extends BaseLoadMoreListFragment {

    private static final String TAG = "BaseAppListFragment";

    @Override
    public void initListView() {
        super.initListView();
        getListView().setDivider(null);
    }

    public List<AppListItemBean> getDataList() {

        return mDataList;
    }

    private List<AppListItemBean> mDataList = new ArrayList<>();

    @Override
    abstract void loadMoreData() ;

    @Override
    BaseAdapter onCreateAdapter() {
       // LogUtils.logd(TAG,"===========================:"+mDataList.size());
        return new BaseAppListAdapter(getContext() , mDataList);
    }
}