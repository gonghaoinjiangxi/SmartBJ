package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by 龚浩 on 2017/3/27.
 */

public abstract class BaseListFragment extends BaseFragment {


    @Override
    public void startLoadData() {

    }

    //返回一个listview
    @Override
    public View getContentView() {
        ListView listView =  new ListView(getContext());
        listView.setAdapter(onCreateAdapter());
        listView.setOnItemClickListener(mOnItemClickListener);
        return listView;
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onListClick(position);
        }
    };

    private void onListClick(int position) {
    }

    abstract ListAdapter onCreateAdapter() ;
}
