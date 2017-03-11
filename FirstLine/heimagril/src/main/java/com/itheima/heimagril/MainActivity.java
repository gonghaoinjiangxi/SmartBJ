package com.itheima.heimagril;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itheima.heimagril.com.itheima.heimagril.bean.GrilsBean;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private ListView mLv;
    private static final String TAG = "MainActivity";
    Gson mGson = new Gson();
    List<GrilsBean.ResultsBean> mListData = new ArrayList<>();
    private MyAdapter mAdapter;
    private int count;
    private boolean isLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        initData();
        initListener();
    }

    private void initListener() {
        //设置listview的滑动监听

        mLv.setOnScrollListener(this);
    }

    private void initData() {


        count = 1;
        //创建适配器,并且设置到listview
        mAdapter = new MyAdapter();
        LoadImage();


    }

    private void LoadImage() {

        //开启线程实现网络请求链接
        new Thread(new Runnable() {
            @Override
            public void run() {
                count++;
                Log.d(TAG, "LoadImage: count=" + count);
                isLoading = true;
                String url = "http://gank.io/api/data/福利/10/" + count;
                Request request = new Request.Builder().get().url(url).build();
                OkHttpClient client = new OkHttpClient();
                try {
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String body = response.body().string();
                            GrilsBean bean = mGson.fromJson(body, GrilsBean.class);
                            final List<GrilsBean.ResultsBean> list = bean.getResults();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mListData.addAll(list);
                                    mAdapter.notifyDataSetChanged();
                                    isLoading = false;
                                }
                            });
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLv.setAdapter(mAdapter);
                    }
                });
            }
        }).start();

    }

    private void initView() {

        mLv = (ListView) findViewById(R.id.list_view);

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == mListData.size() - 1 && !isLoading) {
            LoadImage();
            Log.d(TAG, "onScroll: count=" + count);
            mLv.smoothScrollToPosition(mListData.size());
        }
    }

    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {

            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_listview, null);

                holder = new ViewHolder();

                holder.ivGril = (ImageView) convertView.findViewById(R.id.iv_gril);
                holder.tvDesc = (TextView) convertView.findViewById(R.id.tv_desc);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            GrilsBean.ResultsBean bean = mListData.get(position);
            Picasso.with(MainActivity.this).load(bean.getUrl()).into(holder.ivGril);

            return convertView;
        }
    }

    static class ViewHolder {
        TextView tvDesc;
        ImageView ivGril;
    }
}
