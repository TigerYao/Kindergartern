package com.junbaole.kindergartern.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liangrenwang on 16/6/22.
 */
public class RetrofitServer {

    private Retrofit retrofit;
    private static final String BASEAPI = "http://60.205.57.254:8080/";

    protected RetrofitServer() {
        retrofit = new Retrofit.Builder().baseUrl(BASEAPI).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    private volatile static RetrofitServer instance = null;

    public static RetrofitServer getInstance() {
        if (instance == null) {
            synchronized (RetrofitServer.class) {
                if (instance == null) {
                    instance = new RetrofitServer();
                }
            }
        }
        return instance;
    }

    public <T> T createService(Class<T> clz) {
        return retrofit.create(clz);
    }
}
