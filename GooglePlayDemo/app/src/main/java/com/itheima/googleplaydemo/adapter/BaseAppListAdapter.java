package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;

import com.itheima.googleplaydemo.bean.AppListItemBean;

import java.util.List;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public class BaseAppListAdapter extends BaseLoadMoreListAdapter<AppListItemBean> {

    public BaseAppListAdapter(Context context, List<AppListItemBean> dataList) {
        super(context, dataList);
    }

    @Override
    View onBindView(ViewHolder view, int position) {
        return null;
    }

    @Override
    View onCreateViewHolder(int position) {
        return null;
    }
}
