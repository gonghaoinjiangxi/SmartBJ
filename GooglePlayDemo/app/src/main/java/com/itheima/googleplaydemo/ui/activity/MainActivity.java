package com.itheima.googleplaydemo.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.adapter.MainAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    private static final int PERMISSION_REQUEST_CODE = 202;
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
        //初始化高危权限,6.0之后,权限要进行动态申请
        initPermission();
    }

    private void initPermission() {
        int result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_DENIED) {
          /*  Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
*/
            Toast.makeText(this,"向用户请求权限",Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE) {
            if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this,"用户拒绝了权限请求",Toast.LENGTH_SHORT).show();
            }
        }
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
