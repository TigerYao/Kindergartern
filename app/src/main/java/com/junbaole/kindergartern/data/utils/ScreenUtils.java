package com.junbaole.kindergartern.data.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by TigerYao on 16/7/23.
 */
public class ScreenUtils {


    public static int width,height,densityDpi;
    public static float density;

    public static void initDisplay(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        density = metrics.density;
        densityDpi = metrics.densityDpi;
    }

}
