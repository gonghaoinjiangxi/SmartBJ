package com.itheima.contentview.com.itheima.contentview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.contentview.R;

import java.util.ArrayList;

/**
 * Created by 龚浩 on 2017/3/12.
 */

public class MyView extends RelativeLayout {

    private MyAdapter mMyAdapter;
    private ImageView mIvArrow;
    private ArrayList<String> mListData;
    private EditText mEt;
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //创建popuwindow
            final PopupWindow pop = new PopupWindow(mEt.getWidth(), 500);

            pop.setOutsideTouchable(true);
            pop.setFocusable(true);

            ListView listView = (ListView) View.inflate(getContext(), R.layout.list_view, null);

            listView.setBackgroundResource(R.mipmap.listview_background);

            mMyAdapter = new MyAdapter();
            listView.setAdapter(mMyAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mEt.setText(mListData.get(position));
                    mEt.setSelection(mListData.get(position).length());
                    pop.dismiss();
                }
            });

            pop.setContentView(listView);

            pop.showAsDropDown(mEt);
        }
    };

    public MyView(Context context) {

        this(context, null);
    }


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.my_view, this);

        initView();

        initData();

        initListener();
    }

    private void initListener() {
        mIvArrow.setOnClickListener(mClickListener);
    }

    private void initData() {
        mListData = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            mListData.add(String.valueOf(i));
        }
    }

    private void initView() {
        mIvArrow = (ImageView) findViewById(R.id.iv_arrow);
        mEt = (EditText) findViewById(R.id.et);
    }

    static class ViewHolder {
        TextView mTvNum;
        ImageView mIvDel;

        public ViewHolder(View root) {
            mTvNum = (TextView) root.findViewById(R.id.tv_num);
            mIvDel = (ImageView) root.findViewById(R.id.iv_dele);
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_list_view, null);
                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.mTvNum.setText(mListData.get(position));
            holder.mIvDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListData.remove(mListData.get(position));
                    mMyAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


    }

}
