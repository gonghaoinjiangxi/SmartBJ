package com.itheima.googleplaydemo.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.itheima.googleplaydemo.utils.ToastUtils;

/**
 * Created by 龚浩 on 2017/3/27.
 */

public abstract class BaseListFragment extends BaseFragment {
    private static final String TAG = "BaseListFragment";


    private View mHeaderView;

    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    private BaseAdapter mAdapter;

    public ListView getListView() {
        return mListView;
    }

    private ListView mListView;

    @Override
    public void startLoadData() {

    }


    //返回一个listview
    @Override
    public View getContentView() {
        mListView = new ListView(getContext());
        mAdapter = onCreateAdapter();
        Log.d(TAG, "getContentView: "+mAdapter.getCount());
        mListView.setAdapter(mAdapter);
        mHeaderView = getHeaderView();
        if(mHeaderView != null) {
            mListView.addHeaderView(mHeaderView);
        }
        initListView();
        mListView.setOnItemClickListener(mOnItemClickListener);
        return mListView;
    }

    protected  View getHeaderView() {
        return null;
    };


    public void initListView() {
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            onListClick(position - mListView.getHeaderViewsCount());
        }
    };

    void onListClick(int position) {
        ToastUtils.makeText(getContext(),"hhhhhhh");
    }

    abstract BaseAdapter onCreateAdapter() ;
}
