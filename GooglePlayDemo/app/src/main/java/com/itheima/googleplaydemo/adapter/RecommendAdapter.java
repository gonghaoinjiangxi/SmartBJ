package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplaydemo.widgest.StellarMap;

import java.util.List;

/**
 * Created by 龚浩 on 2017/3/27.
 */

public class RecommendAdapter implements StellarMap.Adapter {

    private Context mContext;
    private List<String> mData;
    public static final int PAGE_SIZE = 15;

    public RecommendAdapter(Context context, List<String> data){
        mContext = context;
        mData = data;
    };

    @Override
    public int getGroupCount() {
        if(mData != null) {
            if(mData.size() % PAGE_SIZE != 0) {
                return (mData.size() / PAGE_SIZE) + 1;
            }else {
                return mData.size() / PAGE_SIZE;
            }
        }
        return 0;
    }

    @Override
    public int getCount(int group) {
        if(mData.size() % PAGE_SIZE != 0 ) {
            if(group == getGroupCount() - 1) {
                return mData.size() % PAGE_SIZE;
            }
        }
        return PAGE_SIZE;
    }

    @Override
    public View getView(int group, int position, View convertView) {


        TextView view = new TextView(mContext);
        int count = group * PAGE_SIZE + position;
        view.setText(mData.get(count));
        return view;
    }

    @Override
    public int getNextGroupOnPan(int group, float degree) {
        return 0;
    }

    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
        if(isZoomIn) {
            return (group + 1) % getGroupCount();
        }else {
            return (group - 1 + getGroupCount()) % getGroupCount();
        }
    }
}
