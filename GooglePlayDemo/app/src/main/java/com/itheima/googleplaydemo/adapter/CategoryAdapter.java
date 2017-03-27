package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;

import com.itheima.googleplaydemo.bean.CategoryItemBean;
import com.itheima.googleplaydemo.widgest.CategoryItemView;

import java.util.List;

/**
 * Created by 龚浩 on 2017/3/27.
 */

public class CategoryAdapter extends BaseListAdapter {
    private List<CategoryItemBean> mDataList;

    public CategoryAdapter(Context context, List<CategoryItemBean> dataList) {
        super(context, dataList);
        mDataList = dataList;
    }

    @Override
    View onBindView(ViewHolder holder, int position) {
        CategoryItemView view = (CategoryItemView) holder.mView;
        view.setData(mDataList.get(position));
        return view;
    }

    @Override
    View onCreateViewHolder(int position) {
        CategoryItemView view = new CategoryItemView(getContext());
        return view;
    }
}
