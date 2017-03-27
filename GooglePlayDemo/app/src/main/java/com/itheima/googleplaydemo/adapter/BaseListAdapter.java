package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 龚浩 on 2017/3/27.
 */

public abstract class BaseListAdapter extends android.widget.BaseAdapter {

    public Context getContext() {
        return mContext;
    }

    private Context mContext;

    public List getDataList() {
        return mDataList;
    }

    private List mDataList;

    public BaseListAdapter(Context context, List dataList) {

        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        if (mDataList != null) {
            return mDataList.size();
        }
        return 0;

    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            //返回一个与view对象绑定的holder对象
            holder = new ViewHolder(onCreateViewHolder(position));
            convertView = holder.mView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        convertView = onBindView(holder, position);

        return convertView;
    }

    abstract View onBindView(ViewHolder view, int position);

    abstract View onCreateViewHolder(int position);

    public class ViewHolder {
        View mView;

        public ViewHolder(View root) {
            mView = root;
        }
    }
}
