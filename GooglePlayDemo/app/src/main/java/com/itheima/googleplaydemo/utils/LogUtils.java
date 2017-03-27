package com.itheima.googleplaydemo.utils;

import android.util.Log;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class LogUtils {

    public static boolean isLog = true;

    public static void logd(String tag,String msg) {
        if(isLog) {
            Log.d(tag, msg);
        }
    }
}
