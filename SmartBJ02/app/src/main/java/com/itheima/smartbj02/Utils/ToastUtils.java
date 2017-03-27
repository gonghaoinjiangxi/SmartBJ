package com.itheima.smartbj02.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public class ToastUtils {
    public static void makeText(Context context,String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
