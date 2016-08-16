package com.junbaole.kindergartern.data.utils.activity;

import android.content.Context;

import com.junbaole.kindergartern.data.utils.SharedPreferenceUtil;
import com.junbaole.kindergartern.data.utils.StringUtils;

/**
 * Created by yaohu on 16/7/26.
 */
public class AppInfo {

    private static String mCityCode;
    private static String mCityName = "北京市";

    public static void setCityCode(Context ctx, String cityCode) {
        if (StringUtils.isBlank(cityCode)) {
            SharedPreferenceUtil.putString(ctx, "citycode", mCityCode);
            mCityCode = cityCode;
        }
    }

    public static String getCityCode(Context ctx) {
        if (StringUtils.isBlank(mCityCode)) {
            mCityCode = SharedPreferenceUtil.getString(ctx, "citycode", mCityCode);
        }
        return mCityCode;
    }

    public static void setCityName(Context ctx, String cityName) {
        if (StringUtils.isBlank(cityName)) {
            SharedPreferenceUtil.putString(ctx, "cityName", mCityName);
            mCityName = cityName;
        }
    }

    public static String getCityName(Context ctx) {
        if (StringUtils.isBlank(mCityName)) {
            mCityName = SharedPreferenceUtil.getString(ctx, "cityName", mCityName);
        }
        return mCityName;
    }
}
