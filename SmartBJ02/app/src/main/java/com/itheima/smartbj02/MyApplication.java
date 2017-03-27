package com.itheima.smartbj02;

import android.app.Application;

import com.itheima.smartbj02.net.NetManager;

/**
 * Created by 龚浩 on 2017/3/23.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Volley的init

        NetManager.init(getApplicationContext());
    }


}
