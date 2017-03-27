package com.itheima.smartbj02.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.itheima.smartbj02.R;
import com.itheima.smartbj02.Utils.NetUtils;
import com.itheima.smartbj02.Utils.ToastUtils;
import com.itheima.smartbj02.app.Const;
import com.itheima.smartbj02.bean.CategoriesBean;
import com.itheima.smartbj02.bean.NewsListBean;
import com.itheima.smartbj02.net.NetManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/25.
 */
public class ImgView extends FrameLayout {

    @BindView(R.id.lv_img)
    ListView mLvImg;
    @BindView(R.id.gv_img)
    GridView mGvImg;
    private CategoriesBean mBean;
    private List<NewsListBean.DataBean.NewsBean> mListData;
    private NetUtils.OnResponeCompleteListener<NewsListBean> mCompleteListener1 = new NetUtils.OnResponeCompleteListener<NewsListBean>() {


        @Override
        public void OnSuccess(NewsListBean response) {
            mListData = response.getData().getNews();
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void OnError(VolleyError error) {

        }
    };
    private MyAdapter mAdapter;
    private boolean isList = true;

    public ImgView(Context context) {
        this(context, null);
    }

    public ImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(getContext(), R.layout.img_pager, this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        NetUtils<CategoriesBean> utils = new NetUtils<>();
        String url = Const.URL_HTTP + "/categories.json";
        startTask(utils, url);
        mAdapter = new MyAdapter();
        mGvImg.setAdapter(mAdapter);
        mLvImg.setAdapter(mAdapter);
    }

    private void startTask(NetUtils<CategoriesBean> utils, String url) {
        utils.sentRequest(url, CategoriesBean.class);
        utils.setOnCompleteListener(mCompleteListener);
    }

    private NetUtils.OnResponeCompleteListener<CategoriesBean> mCompleteListener = new NetUtils.OnResponeCompleteListener<CategoriesBean>() {

        @Override
        public void OnSuccess(CategoriesBean response) {
            mBean = response;
            String url = Const.URL_HTTP+ mBean.getData().get(2).getUrl();
            NetUtils<NewsListBean> netUtils = new NetUtils<>();
            netUtils.sentRequest(url,NewsListBean.class);
            netUtils.setOnCompleteListener(mCompleteListener1);
        }

        @Override
        public void OnError(VolleyError error) {

        }
    };

    public void setData() {
        isList = !isList;
        setImg();
    }

    private void setImg() {
        if(isList) {
            ToastUtils.makeText(getContext(),isList+"list");
            mLvImg.setVisibility(VISIBLE);
            mGvImg.setVisibility(GONE);
        }else{
            ToastUtils.makeText(getContext(),isList+"grid");
            mGvImg.setVisibility(VISIBLE);
            mLvImg.setVisibility(GONE);
        }

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mListData != null) {
                return mListData.size();
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
                convertView = View.inflate(getContext(), R.layout.item_view, null);

                holder = new ViewHolder();
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.ivIcon = (NetworkImageView) convertView.findViewById(R.id.iv_img);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivIcon.setImageUrl(mListData.get(position).getListimage(), new ImageLoader(NetManager.mQueue, NetManager.getImgLoder()));
            holder.tvTitle.setText(mListData.get(position).getTitle());
            return convertView;
        }
    }

    static class ViewHolder {
        NetworkImageView ivIcon;
        TextView tvTitle;
    }

}
