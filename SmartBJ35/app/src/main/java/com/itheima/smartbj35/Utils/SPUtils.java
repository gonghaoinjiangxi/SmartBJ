package com.itheima.smartbj35.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 龚浩 on 2017/3/16.
 */

public class SPUtils {
    private static final String FILE_NAME = "smartBJ35";

    public static void saveBoolean(Context context, String key, boolean isTrue) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, isTrue).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defult) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defult);
    }
}
