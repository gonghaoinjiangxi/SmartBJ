package com.itheima.googleplaydemo.ui.fragment;

import android.widget.AbsListView;
import android.widget.ListAdapter;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public abstract class BaseLoadMoreListFragment extends BaseListFragment {


    @Override
    public void initListView() {
        super.initListView();
        getListView().setOnScrollListener(mListener);
    }

    private AbsListView.OnScrollListener mListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if(scrollState == SCROLL_STATE_IDLE) {
                if(view.getLastVisiblePosition() == getLoadPosition()) {
                    loadMoreData();
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };

    abstract void loadMoreData() ;

     protected int getLoadPosition() {
         return getAdapter().getCount() - 1;
     };

    @Override
    abstract  ListAdapter onCreateAdapter() ;
}
