package com.itheima.googleplaydemo.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.adapter.MainAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.slide_menu)
    NavigationView mSlideMenu;
    @BindView(R.id.draw_layout)
    DrawerLayout mDrawLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_indicator)
    TabLayout mTabIndicator;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mTitles ;
    private MainAdapter mAdapter;

    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        super.init();
        initToolBar();
        initMainContent();
    }

    private void initMainContent() {
        mTitles = getResources().getStringArray(R.array.main_titles);

        mAdapter = new MainAdapter(mTitles, getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mTabIndicator.setupWithViewPager(mViewPager);
    }

    private void initToolBar() {
        setSupportActionBar(mToolBar);
        // ActionBar bar = getActionBar();
        ActionBar actionBar = getSupportActionBar();
        /*actionBar.setIcon(R.drawable.icon);
        actionBar.setDisplayShowHomeEnabled(true);*/


        actionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawLayout, R.string.open, R.string.close);
        mSlideMenu.setNavigationItemSelectedListener(mListener);
        mDrawerToggle.syncState();
        mDrawLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //内部实现了两者间的联动
                mDrawerToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private NavigationView.OnNavigationItemSelectedListener mListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // mSlideMenu.setCheckedItem(item.getItemId());
            return true;
        }
    };

}
