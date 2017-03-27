package com.itheima.smartbj02.UI.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.itheima.smartbj02.R;
import com.itheima.smartbj02.view.GovAffairsTabPager;
import com.itheima.smartbj02.view.HomeTabPager;
import com.itheima.smartbj02.view.NewsTabPager;
import com.itheima.smartbj02.view.SettingTabPager;
import com.itheima.smartbj02.view.SmartServicesTabPager;
import com.itheima.smartbj02.view.TabPager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/22.
 */
public class MainFragment extends Fragment {
    @BindView(R.id.content_main)
    FrameLayout mContentMain;
    @BindView(R.id.tab_container)
    RadioGroup mTabContainer;
    private TabPager mPager;
    private SparseArray<TabPager> mTabPagerCache = new SparseArray<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_main, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mTabContainer.setOnCheckedChangeListener(mOnCheckedChangeListener);
        mTabContainer.check(R.id.tab_home);
}

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            setTabCheck(checkedId);

            if (mOnTabPagerChangedListener != null) {
                if (checkedId == R.id.tab_home || checkedId == R.id.tab_settings) {
                    mOnTabPagerChangedListener.isSliding(false);
                } else {
                    mOnTabPagerChangedListener.isSliding(true);
                }
            }

            if (mPager != null) {
                mPager.setOnMenuClickListener(new TabPager.OnMenuClickListener() {
                    @Override
                    public void onMenuClick() {
                        if (mOnTabPagerChangedListener != null) {
                            mOnTabPagerChangedListener.OnMenuClick();
                        }
                    }
                });
            }
        }
    };

    private OnTabPagerChangedListener mOnTabPagerChangedListener;

    public void setOnTabPagerChanged(OnTabPagerChangedListener listener) {
        mOnTabPagerChangedListener = listener;
    }

    public void setData(int position) {
        if (mPager != null) {
            mPager.setData(position);
        }
    }

    public interface OnTabPagerChangedListener {
        void isSliding(boolean isSliding);

        void OnMenuClick();
    }

    private void setTabCheck(int id) {
        mPager = getPager(id);
       /* switch (id) {
            case R.id.tab_home:

                break;
            case R.id.tab_news_center:

                break;
            case R.id.tab_smart_service:

                break;
            case R.id.tab_gov_affairs:

                break;
            case R.id.tab_settings:

                break;
        }*/
        mContentMain.removeAllViews();
        mContentMain.addView(mPager);
    }

    private TabPager getPager(int id) {

        TabPager pager = null;

        if (mTabPagerCache.get(id) != null) {
            return mTabPagerCache.get(id);
        }

        switch (id) {
            case R.id.tab_home:
                pager = new HomeTabPager(getContext());
                break;
            case R.id.tab_news_center:
                pager = new NewsTabPager(getContext());
                break;
            case R.id.tab_smart_service:
                pager = new SmartServicesTabPager(getContext());
                break;
            case R.id.tab_gov_affairs:
                pager = new GovAffairsTabPager(getContext());
                break;
            case R.id.tab_settings:
                pager = new SettingTabPager(getContext());
                break;
        }
        mTabPagerCache.put(id, pager);


        return pager;
    }


}
