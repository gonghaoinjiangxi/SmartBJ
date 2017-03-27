package com.itheima.smartbj02.UI.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.smartbj02.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public class SlidingMenuFragment extends Fragment {

    @BindView(R.id.lv_slid)
    ListView mLvSlid;
    private MyAdapter mAdapter;
    private String[] items = {"新闻","专题","组图","互动"};
    private int currentSelected = 0;
    private boolean isFirst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slid_menu, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mAdapter = new MyAdapter();
        mLvSlid.setAdapter(mAdapter);
        mLvSlid.setOnItemClickListener(mItemClickListener);
    }


    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(mSlidingMenuClicklistener != null) {
                mSlidingMenuClicklistener.OnItemClick(position);
            }
            if(position != currentSelected) {
                view.setEnabled(true);
                View childAt = parent.getChildAt(currentSelected);
                childAt.setEnabled(false);
                currentSelected = position;
            }


        }
    };

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null) {
                convertView = View.inflate(getContext(),R.layout.item_listview_menu,null);

                holder = new ViewHolder();
                holder.tvItem = (TextView) convertView.findViewById(R.id.tv_item_menu);

                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            if(position == 0) {
                holder.tvItem.setEnabled(true);
            }else {
                holder.tvItem.setEnabled(false);
            }

            holder.tvItem.setText(items[position]);

            return convertView;
        }
    }

    public static class  ViewHolder {
        TextView tvItem;
    }

    private OnSlidingMenuClicklistener mSlidingMenuClicklistener ;

    public void setSlidingMenuClick(OnSlidingMenuClicklistener clicklistener) {
        mSlidingMenuClicklistener = clicklistener;
    }

    public interface OnSlidingMenuClicklistener {
        void OnItemClick(int position);
    }
}
