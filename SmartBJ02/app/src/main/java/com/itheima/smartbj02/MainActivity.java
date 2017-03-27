package com.itheima.smartbj02;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.itheima.smartbj02.UI.Fragment.MainFragment;
import com.itheima.smartbj02.UI.Fragment.SlidingMenuFragment;
import com.itheima.smartbj02.Utils.ToastUtils;
import com.itheima.smartbj02.event.MySlidingMenuClickEvent;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends SlidingFragmentActivity {

    private SlidingMenuFragment mFrag;
    private MainFragment mMainFragment;
    private SlidingMenu mSm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fram);
        setMainView();
        setLeftView();
        setMenuAttr();
        init();

        EventBus.getDefault().register(this);

    }

    private void init() {
        mMainFragment.setOnTabPagerChanged(new MainFragment.OnTabPagerChangedListener() {
            @Override
            public void isSliding(boolean isSliding) {
                if(isSliding) {
                    mSm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                }else {
                    mSm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void OnMenuClick() {
               // mSm.toggle();
            }
        });

        //设置侧滑菜单的点击监听事件
        mFrag.setSlidingMenuClick(new SlidingMenuFragment.OnSlidingMenuClicklistener() {
            @Override
            public void OnItemClick(int position) {
                mMainFragment.setData(position);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(MySlidingMenuClickEvent event) {
        ToastUtils.makeText(this,"success");
        mSm.toggle();
    }

    private void setMenuAttr() {
        // customize the SlidingMenu
        mSm = getSlidingMenu();
        mSm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//侧滑菜单偏移量
        getSlidingMenu().setTouchModeAbove(SlidingMenu.LEFT);//设置左皆可拉出侧滑菜单
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
    }

    private void setMainView() {
        mMainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mMainFragment)
                .commit();
    }

    private void setLeftView() {
        setBehindContentView(R.layout.menu_frame);//设置左边侧滑菜单的布局
        if (mFrag == null) {
            FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
            mFrag = new SlidingMenuFragment();
            t.replace(R.id.menu_frame, mFrag);
            t.commit();
        } else {
            mFrag = (SlidingMenuFragment) this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
