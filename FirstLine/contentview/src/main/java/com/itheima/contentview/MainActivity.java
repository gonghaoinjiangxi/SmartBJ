package com.itheima.contentview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvArrow;
    private EditText mEt;
    private Context mContext;
    private List<String> mListData;
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //创建popuwindow
            final PopupWindow pop = new PopupWindow(mEt.getWidth(), 500);

            pop.setOutsideTouchable(true);
            pop.setFocusable(true);


            ListView listView = (ListView) View.inflate(mContext, R.layout.list_view, null);
            listView.setBackgroundResource(R.mipmap.listview_background);

            MyAdapter myAdapter = new MyAdapter();
            listView.setAdapter(myAdapter);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

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
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_list_view, null);
                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.mTvNum.setText(mListData.get(position));
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
