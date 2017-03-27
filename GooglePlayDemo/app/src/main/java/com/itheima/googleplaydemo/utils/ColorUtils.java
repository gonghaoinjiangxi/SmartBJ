package com.itheima.googleplaydemo.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by 龚浩 on 2017/3/27.
 */

public class ColorUtils {
    public static int getArgb() {
        int argb;
        Random r = new Random();
        int alpha = r.nextInt(255);
        int red = r.nextInt(255);
        int green = r.nextInt(255);
        int blue = r.nextInt(255);
        argb = Color.argb(alpha,red,green,blue);
        return argb;
    }
}
