package com.junbaole.kindergartern.data.utils.activity;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Text;
import com.amap.api.services.core.LatLonPoint;
import com.junbaole.kindergartern.data.utils.SharedPreferenceUtil;
import com.junbaole.kindergartern.data.utils.StringUtils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by yaohu on 16/7/26.
 */
public class AppInfo {

    private static String mCityCode;
    private static String mCityName = "北京市";
    private static String mLocationName;
    public static LatLonPoint latLonPoint;
    private static double mLat;
    private static double mLon;
    public static void setCityCode(Context ctx, String cityCode) {
        if (!StringUtils.isBlank(cityCode)) {
            SharedPreferenceUtil.putString(ctx, "citycode", mCityCode);
            mCityCode = cityCode;
        }
    }

    public static void setLat(Context ctx, double lat) {
        if (lat!=0) {
            SharedPreferenceUtil.putString(ctx,"location_lat",lat+"");
            mLat = lat;
        }
    }

    public static double getLat(Context ctx) {
        if (mLat==0d) {
            String latString =SharedPreferenceUtil.getString(ctx,"location_lat",mLat+"");
            if(TextUtils.isDigitsOnly(latString)){
                mLat = Double.parseDouble(latString);
            }
        }
        return mLat;
    }

    public static void setLon(Context ctx, double lon) {
        if (lon!=0) {
            SharedPreferenceUtil.putString(ctx,"location_lon",lon+"");
            mLon = lon;
        }
    }

    public static double getLon(Context ctx) {
        if (mLon==0d) {
            String latString =SharedPreferenceUtil.getString(ctx,"location_lat",mLon+"");
            if(TextUtils.isDigitsOnly(latString)){
                mLon = Double.parseDouble(latString);
            }
        }
        return mLon;
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
        if (!StringUtils.isBlank(locationName)) {
            SharedPreferenceUtil.putString(ctx, "locationname", mLocationName);
            mLocationName = locationName;
        }
    }

    public static void setCityName(Context ctx, String cityName) {
        if (!StringUtils.isBlank(cityName)) {
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
