package com.itheima.smartbj02.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.itheima.smartbj02.R;
import com.itheima.smartbj02.Utils.NetUtils;
import com.itheima.smartbj02.Utils.ToastUtils;
import com.itheima.smartbj02.app.Const;
import com.itheima.smartbj02.bean.CategoriesBean;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by 龚浩 on 2017/3/22.
 */
public class ContentView extends LinearLayout {
    private static final String[] CONTENT = new String[]{"Recent", "Artists", "Albums", "Songs", "Playlists", "Genres"};
    private MyAdapter mAdapter;
    private CategoriesBean.DataBean mListData;
    private TabPageIndicator mIndicator;
    private PullToRefeshView mView;

    public ContentView(Context context) {
        this(context, null);
    }

    public ContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.view_content_view, this);
        init();
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.view_content_view);
//        init();
//    }

    private void init() {


        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MyAdapter();
        pager.setAdapter(mAdapter);

        mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(pager);

        //开始网络链接
        ToastUtils.makeText(getContext(), "开始链接");
        NetUtils<CategoriesBean> netUtil = new NetUtils<CategoriesBean>();
        String url = Const.URL_HTTP + "/categories.json";
        netUtil.sentRequest(url, CategoriesBean.class);
        netUtil.setOnCompleteListener(new NetUtils.OnResponeCompleteListener<CategoriesBean>() {
            @Override
            public void OnSuccess(CategoriesBean response) {
                CategoriesBean bean = response;
                mListData = bean.getData().get(0);
                mAdapter.notifyDataSetChanged();
                mIndicator.notifyDataSetChanged();
            }

            @Override
            public void OnError(VolleyError error) {
                ToastUtils.makeText(getContext(), "error" + error);
            }
        });
    }


    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mListData != null) {
                return mListData.getChildren().size();
            }
            return 0;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //添加一个上下可以刷新的界面
            mView = new PullToRefeshView(getContext());
            mView.setData(mListData.getChildren().get(position));
            container.addView(mView);
            return mView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mListData.getChildren().get(position).getTitle();
        }


    }

}
