package com.itheima.smartbj02.Utils;

import android.content.Context;

import com.itheima.smartbj02.app.Const;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public class SPUtils {
    public static void saveBoolean(Context context, boolean save) {
        context.getSharedPreferences(Const.GUIDE_FILE_NAME, Context.MODE_PRIVATE).edit().putBoolean(Const.GUIDE_BTN_KEY, save).commit();
    }

    public static boolean getBoolean (Context context,boolean defult) {
      return context.getSharedPreferences(Const.GUIDE_FILE_NAME, Context.MODE_PRIVATE).getBoolean(Const.GUIDE_BTN_KEY, defult);
    }

    public static void savePosition(Context context,String key,boolean isClick) {
        context.getSharedPreferences(Const.GUIDE_FILE_NAME,Context.MODE_PRIVATE).edit().putBoolean(key,isClick).commit();
    }

    public static boolean getPosition (Context context,String key,boolean defult) {
        return context.getSharedPreferences(Const.GUIDE_FILE_NAME, Context.MODE_PRIVATE).getBoolean(key, defult);
    }


}
