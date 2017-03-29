package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public abstract class BaseLoadMoreListAdapter<T> extends BaseListAdapter<T> {

    private static final int TYPE_NOMAL = 0;
    private static final int TYPE_PROGRESS = 1;
    private List<T> mDataList;


    @Override
    public int getCount() {
        if(mDataList != null) {
            return mDataList.size() + 1;
        }
        return 0;
    }

    public BaseLoadMoreListAdapter(Context context, List<T> dataList) {
        super(context, dataList);
        mDataList = dataList;
    }

    @Override
    abstract View onBindView(ViewHolder view, int position) ;

    @Override
    abstract View onCreateViewHolder(int position) ;

    @Override
    public int getItemViewType(int position) {
        if(position == getCount() - 1) {
            return TYPE_PROGRESS;
        }else {
            return TYPE_NOMAL;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
