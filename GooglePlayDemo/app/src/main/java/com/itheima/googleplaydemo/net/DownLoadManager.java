package com.itheima.googleplaydemo.net;

/**
 * Created by 龚浩 on 2017/4/1.
 */

public class DownLoadManager {
    private static DownLoadManager sDownLoadManager;

    //私有化构造,防止外部类通过构造创建本类对象
    private DownLoadManager() {}


    public static DownLoadManager getInstant() {
        if(sDownLoadManager == null) {
            synchronized (DownLoadManager.class) {
                if(sDownLoadManager == null) {
                    sDownLoadManager = new DownLoadManager();
                }
            }
        }
        return sDownLoadManager;
    }




}
