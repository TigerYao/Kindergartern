package com.junbaole.kindergartern.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liangrenwang on 16/6/22.
 */
public class RetrofitServer {

    private Retrofit retrofit,sendcondRetrofit;
    private static final String BASEAPI = "http://60.205.57.254:8080/";


    protected RetrofitServer() {
        retrofit = new Retrofit.Builder().baseUrl(BASEAPI).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    protected RetrofitServer(boolean isSecond) {
        String baseurl = "http://60.205.57.254:8090/";
        sendcondRetrofit = new Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    private volatile static RetrofitServer instance = null;
    private volatile static RetrofitServer instanceSecond = null;

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

    public static RetrofitServer getInstanceSecond() {
        if (instanceSecond == null) {
            synchronized (RetrofitServer.class) {
                if (instanceSecond == null) {
                    instanceSecond = new RetrofitServer(true);
                }
            }
        }
        return instanceSecond;
    }

    public <T> T createService(Class<T> clz) {
        return retrofit.create(clz);
    }


    public <T> T createServiceSecond(Class<T> clz) {
        return sendcondRetrofit.create(clz);
    }
}
