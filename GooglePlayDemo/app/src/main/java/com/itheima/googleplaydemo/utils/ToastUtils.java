package com.itheima.googleplaydemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class ToastUtils {
    private static boolean isTrue = true;
    public static void makeText(Context context, String msg) {
        if(isTrue) {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
