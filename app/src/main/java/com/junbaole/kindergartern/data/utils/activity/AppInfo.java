package com.junbaole.kindergartern.data.utils.activity;

import com.junbaole.kindergartern.data.utils.SharedPreferenceUtil;
import com.junbaole.kindergartern.data.utils.StringUtils;

import android.content.Context;

/**
 * Created by yaohu on 16/7/26.
 */
public class AppInfo {

    private static String mCityCode;
    private static String mCityName = "北京市";
    private static String mLocationName;

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

    public static String getLocationName(Context ctx) {
        if (StringUtils.isBlank(mLocationName)) {
            mLocationName = SharedPreferenceUtil.getString(ctx, "locationname", mLocationName);
        }
        return mLocationName;
    }

    public static void setLocationName(Context ctx, String locationName) {
        if (StringUtils.isBlank(locationName)) {
            SharedPreferenceUtil.putString(ctx, "locationname", mLocationName);
            mLocationName = locationName;
        }
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
