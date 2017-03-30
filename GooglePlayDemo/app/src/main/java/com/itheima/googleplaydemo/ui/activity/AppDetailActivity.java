package com.itheima.googleplaydemo.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.itheima.googleplaydemo.R;

import butterknife.BindView;

/**
 * Created by 龚浩 on 2017/3/30.
 */
public class AppDetailActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;


    @Override
    public void init() {
        super.init();
        initActionBar();
    }



    private void initActionBar() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_detail));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected int getResId() {
        return R.layout.activity_detail;
    }


}
