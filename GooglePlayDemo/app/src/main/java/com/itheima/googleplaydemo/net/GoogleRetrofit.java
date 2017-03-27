package com.itheima.googleplaydemo.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class GoogleRetrofit {
    private static String HTTP_BASEURL = "http://10.0.2.2:8080/GooglePlayServer/";
    private  Gson mGson = new GsonBuilder().setLenient().create();
    private Api mApi;
    public static GoogleRetrofit sGoogleRetrofit;

    //单例模式
    public static GoogleRetrofit getInstance() {
        if (sGoogleRetrofit == null) {
            synchronized (GoogleRetrofit.class) {
                if (sGoogleRetrofit == null) {
                    sGoogleRetrofit = new GoogleRetrofit();
                }
            }
        }
        return sGoogleRetrofit;
    }

    private GoogleRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTP_BASEURL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
        mApi = retrofit.create(Api.class);
    }

    public Api getApi() {
        return mApi;
    }
}
