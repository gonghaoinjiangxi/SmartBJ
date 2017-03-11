package com.itheima.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEt;
    private ImageView mIv;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();

    }


    private void initListener() {
        //集中管理监听
        mIv.setOnClickListener(this);
    }

    private void initData() {
        //数初始化
    }

    private void initView() {
        //控件初始化
        mEt = (EditText) findViewById(R.id.et);
        mIv = (ImageView) findViewById(R.id.iv);


    }

    @Override
    public void onClick(View v) {
        if (v == mIv) {
            //创建弹窗
            PopupWindow pop = new PopupWindow();

            View mView = View.inflate(this, R.layout.pop_listview, null);

            pop.setContentView(mView);

            ListView lvPop = (ListView) mView.findViewById(R.id.lv_pop);

            mMyAdapter = new MyAdapter();

            lvPop.setAdapter(mMyAdapter);

        }
    }

    static class ViewHolder {
        ImageView ivIcon;
        TextView tvNum;
        ImageView ivDel;
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_listview, null);
                holder = new ViewHolder();
                holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_Icon);
            }

            return convertView;
        }
    }
}
