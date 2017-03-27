package com.itheima.smartbj02.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.itheima.smartbj02.R;
import com.itheima.smartbj02.Utils.NetUtils;
import com.itheima.smartbj02.Utils.SPUtils;
import com.itheima.smartbj02.Utils.ToastUtils;
import com.itheima.smartbj02.app.Const;
import com.itheima.smartbj02.bean.CategoriesBean;
import com.itheima.smartbj02.bean.NewsListBean;
import com.itheima.smartbj02.net.NetManager;
import com.leon.loopviewpagerlib.FunBanner;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/23.
 */

public class PullToRefeshView extends PullToRefreshListView {

    private PullToRefreshAdapter mAdapter;
    private int[] imageResIds = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5};
    private FrameLayout mFrameLayout;
    private String[] mTitles;
    private String[] mImgRes;
    private CategoriesBean.DataBean.ChildrenBean mDateBean;
    private List<NewsListBean.DataBean.NewsBean> mDataList;

    public PullToRefeshView(Context context) {
        this(context, null);
    }

    public PullToRefeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        mAdapter = new PullToRefreshAdapter();
        setAdapter(mAdapter);
        setMode(PullToRefreshBase.Mode.BOTH);
        setOnItemClickListener(mOnItemClickListener);
        setOnRefreshListener(new OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                startTask(mDateBean);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                String more = mBean.getData().getMore();
                if(more.length() > 0) {
                    String url = Const.URL_HTTP + more;
                    NetUtils<NewsListBean> netUtils = new NetUtils<>();
                    netUtils.sentRequest(url,NewsListBean.class);
                    netUtils.setOnCompleteListener(mCompleteListener);
                }else {
                    ToastUtils.makeText(getContext(),"没有更多数据了");
                    post(new Runnable() {
                        @Override
                        public void run() {
                            onRefreshComplete();
                        }
                    });
                }


            }
        });
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SPUtils.savePosition(getContext(),String.valueOf(position-2),true);
            Intent intent = new Intent(getContext(), NewsDetails.class);
            intent.putExtra("load", mDataList.get(position).getUrl());
            getContext().startActivity(intent);
            mAdapter.notifyDataSetChanged();
        }
    };

    public void setLooper(String[] titles, String[] ImgRes) {

        if (mFrameLayout != null) {
            mRefreshableView.removeHeaderView(mFrameLayout);
        }
        //设置轮播头
        mFrameLayout = new FrameLayout(getContext());
        FunBanner.Builder builder = new FunBanner.Builder(getContext());
        FunBanner funBanner = builder.setEnableAutoLoop(true)
                .setImageUrls(ImgRes)
                .setTitles(titles)
                .setDotSelectedColor(Color.GREEN)
                .setHeightWidthRatio(0.5556f)
                .setLoopInterval(5000)
                .setEnableAutoLoop(true)
                .setIndicatorBackgroundColor(Color.GRAY)
                .build();
        mFrameLayout.addView(funBanner);

        mRefreshableView.addHeaderView(mFrameLayout, null, false);
    }

    private NewsListBean mBean;

    public void setData(CategoriesBean.DataBean.ChildrenBean bean) {
        NetUtils<NewsListBean> netUtils = startTask(bean);
        netUtils.setOnCompleteListener(mCompleteListener);
    }

    @NonNull
    private NetUtils<NewsListBean> startTask(CategoriesBean.DataBean.ChildrenBean bean) {
        mDateBean = bean;
        NetUtils<NewsListBean> netUtils = new NetUtils<NewsListBean>();
        String url = Const.URL_HTTP + mDateBean.getUrl();
        netUtils.sentRequest(url, NewsListBean.class);
        netUtils.setOnCompleteListener(mCompleteListener);
        return netUtils;
    }

    private NetUtils.OnResponeCompleteListener<NewsListBean> mCompleteListener = new NetUtils.OnResponeCompleteListener<NewsListBean>() {
        @Override
        public void OnSuccess(NewsListBean response) {
            mBean = response;
            if(mDataList == null) {
                mDataList = response.getData().getNews();
            }else {
                mDataList.addAll(response.getData().getNews());
            }
            setRes(mBean);
            setLooper(mTitles,mImgRes);
            mAdapter.notifyDataSetChanged();
            onRefreshComplete();
        }

        @Override
        public void OnError(VolleyError error) {

        }
    };

    public void setRes(NewsListBean res) {
        mTitles = new String[res.getData().getTopnews().size()];
        mImgRes = new String[res.getData().getTopnews().size()];
        for (int i = 0; i < res.getData().getTopnews().size() ; i++) {
            mTitles[i] =  res.getData().getTopnews().get(i).getTitle();
            mImgRes[i] = res.getData().getTopnews().get(i).getTopimage();
        }
    }


    public class PullToRefreshAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mBean != null) {
                return mDataList.size();
            }
            return 0;
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
                convertView = View.inflate(getContext(), R.layout.item_pulltofresh_list, null);

                holder = new ViewHolder();

                holder.ivIcon = (NetworkImageView) convertView.findViewById(R.id.iv_icon);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            if(SPUtils.getPosition(getContext(),String.valueOf(position),false)) {
                holder.tvTitle.setTextColor(Color.GRAY);
            }else {
                holder.tvTitle.setTextColor(Color.BLACK);
            }

            holder.tvTitle.setText(mDataList.get(position).getTitle());
            holder.tvDate.setText(mDataList.get(position).getPubdate());

            holder.ivIcon.setImageUrl(mDataList.get(position).getListimage(), new ImageLoader(NetManager.mQueue, NetManager.getImgLoder()));


            return convertView;
        }
    }

    static class ViewHolder {
        NetworkImageView ivIcon;
        TextView tvTitle;
        TextView tvDate;
    }
}
