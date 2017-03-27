package com.itheima.smartbj02.UI;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.itheima.smartbj02.MainActivity;
import com.itheima.smartbj02.R;
import com.itheima.smartbj02.Utils.SPUtils;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/3/22.
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.btn_guide)
    Button mBtnGuide;
    @BindView(R.id.indicator)
    CirclePageIndicator mIndicator;
    private int[] imgs = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private Context mContext = this;

    @Override
    public int getLayOutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void init() {
        super.init();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == imgs.length - 1) {
                mBtnGuide.setVisibility(View.VISIBLE);
            } else {
                mBtnGuide.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(mContext);
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    @OnClick(R.id.btn_guide)
    public void onClick() {
        SPUtils.saveBoolean(mContext,true);
        finish();
        navigateTo(MainActivity.class);
    }


}
