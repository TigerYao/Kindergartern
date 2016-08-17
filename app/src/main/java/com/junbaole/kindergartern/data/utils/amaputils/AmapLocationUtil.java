package com.junbaole.kindergartern.data.utils.amaputils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.junbaole.kindergartern.data.utils.activity.AppInfo;

import android.content.Context;
import android.util.Log;

/**
 * Created by yaohu on 16/7/26.
 */
public class AmapLocationUtil implements AMapLocationListener {

    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private Context mCtx;

    public AmapLocationUtil(Context context) {
        this.mCtx = context;
        // 初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        mLocationClient = new AMapLocationClient(context);
        // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        // 设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);

        //// 设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //// 设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        // 设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(1000);
        // 给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.setLocationListener(this);

    }

    public void start() {
        // 启动定位
        if (!mLocationClient.isStarted())
            mLocationClient.startLocation();
    }

    public void deactivate() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(aMapLocation!=null) {
            deactivate();
            Log.i("tag", aMapLocation.getCity() + "???" + aMapLocation.getAddress());
            AppInfo.setCityCode(mCtx, aMapLocation.getCityCode());
            AppInfo.setCityName(mCtx, aMapLocation.getCity());
            AppInfo.setLocationName(mCtx, aMapLocation.getAddress());
        }
    }
}
