package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;

import com.itheima.googleplaydemo.bean.SubjectItemBean;
import com.itheima.googleplaydemo.widgest.LoadMoreProBarView;
import com.itheima.googleplaydemo.widgest.SubjectItemView;

import java.util.List;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public class SubjectAdapter extends BaseLoadMoreListAdapter<SubjectItemBean> {
    private static final String TAG = "SubjectAdapter";

    public SubjectAdapter(Context context, List<SubjectItemBean> dataList) {
        super(context, dataList);
    }

    @Override
    View onBindView(ViewHolder view, int position) {
        if(position != getCount() - 1) {
            SubjectItemView itemView = (SubjectItemView) view.mView;
            itemView.setData(getDataList().get(position));
            return itemView;
        }
        return new LoadMoreProBarView(getContext());
    }

    @Override
    View onCreateViewHolder(int position) {
        if(position == getDataList().size() - 1) {
            return new LoadMoreProBarView(getContext());
        }
        return new SubjectItemView(getContext());
    }
}
