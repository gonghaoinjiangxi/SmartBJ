package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;

import com.itheima.googleplaydemo.bean.AppListItemBean;
import com.itheima.googleplaydemo.widgest.AppListItemView;

import java.util.List;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public class BaseAppListAdapter extends BaseLoadMoreListAdapter<AppListItemBean> {

    public BaseAppListAdapter(Context context, List<AppListItemBean> dataList) {
        super(context, dataList);
    }

    @Override
    View onCreateView(ViewHolder view, int position) {
        AppListItemView itemView = (AppListItemView) view.mView;
        itemView.setData(getDataList().get(position));
        return itemView;
    }


    @Override
    View onCreateViewHolder(int position) {
        return new AppListItemView(getContext());
    }
}
