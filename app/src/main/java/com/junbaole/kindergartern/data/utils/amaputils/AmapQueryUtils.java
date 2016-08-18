package com.junbaole.kindergartern.data.utils.amaputils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.junbaole.kindergartern.data.utils.activity.AppInfo;
import com.junbaole.kindergartern.data.utils.event.LocationEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yaohu on 16/7/26.
 */
public class AmapQueryUtils implements PoiSearch.OnPoiSearchListener, WeatherSearch.OnWeatherSearchListener {

    public void queryKeyWords(Context ctx, String keyWords, int pageNo) {
        PoiSearch.Query query = new PoiSearch.Query(keyWords, AppInfo.getCityCode(ctx));
        query.setPageNum(pageNo);
        query.setPageSize(10);
        PoiSearch poiSearch = new PoiSearch(ctx, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }
    public void queryKeyWords(Context ctx) {
        PoiSearch.Query query = new PoiSearch.Query(AppInfo.getLocationName(ctx),"", AppInfo.getCityName(ctx));
        query.setPageNum(0);
        query.setPageSize(25);
        PoiSearch poiSearch = new PoiSearch(ctx, query);
        if(AppInfo.latLonPoint!=null)
        poiSearch.setBound(new PoiSearch.SearchBound(AppInfo.latLonPoint, 5000, true));
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }
    public void queryWeather(Context ctx) {
        WeatherSearchQuery query = new WeatherSearchQuery(AppInfo.getCityName(ctx), WeatherSearchQuery.WEATHER_TYPE_LIVE);
        WeatherSearch mweathersearch = new WeatherSearch(ctx);
        mweathersearch.setOnWeatherSearchListener(this);
        mweathersearch.setQuery(query);
        mweathersearch.searchWeatherAsyn(); //异步搜索
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        Log.i("tag",poiResult.getPois().size()+"???"+i);
        LocationEvent event = new LocationEvent();
        event.locations = poiResult.getPois();
        EventBus.getDefault().post(event);
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i) {
        if (localWeatherLiveResult != null)
            EventBus.getDefault().post(localWeatherLiveResult);
    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {

    }
}
