package com.itheima.smartbj35.UI;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.itheima.smartbj35.BaseActivity;
import com.itheima.smartbj35.MainActivity;
import com.itheima.smartbj35.R;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/3/16.
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.vp_container_guide)
    ViewPager mVpContainerGuide;
    @BindView(R.id.btn_start_guide)
    Button mBtnStartGuide;
    @BindView(R.id.indicator)
    CirclePageIndicator mIndicator;

    private int[] mImags = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    Context mContext = this;

    @Override
    public int getLayOutResourse() {
        return R.layout.activity_guide;
    }

    @Override
    public void init() {
        super.init();

        //进行页面切换
        mVpContainerGuide.setAdapter(mPagerAdapter);
        mVpContainerGuide.addOnPageChangeListener(mOnPageChangeListener);

        //进行viewpagerindicate配置
        mIndicator.setViewPager(mVpContainerGuide);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == mImags.length - 1) {
                mBtnStartGuide.setVisibility(View.VISIBLE);
            } else {
                mBtnStartGuide.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mImags.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            //初始化viewpager
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mImags[position]);
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //移除当前的view
            container.removeView((View) object);
            //super.destroyItem(container, position, object);
        }
    };


    @OnClick(R.id.btn_start_guide)
    public void onClick() {
        navigateTo(MainActivity.class);
    }


}
