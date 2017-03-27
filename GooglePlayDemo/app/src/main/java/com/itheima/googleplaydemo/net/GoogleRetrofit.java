package com.itheima.googleplaydemo.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itheima.googleplaydemo.app.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class GoogleRetrofit {
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
                .baseUrl(Const.HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
        mApi = retrofit.create(Api.class);
    }

    public Api getApi() {
        return mApi;
    }
}
