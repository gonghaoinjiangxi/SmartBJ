package com.itheima.googleplaydemo.net;

import com.itheima.googleplaydemo.bean.AppListItemBean;
import com.itheima.googleplaydemo.bean.CategoryItemBean;
import com.itheima.googleplaydemo.bean.DetailBean;
import com.itheima.googleplaydemo.bean.HomeItemBean;
import com.itheima.googleplaydemo.bean.SubjectItemBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public interface Api {
    @GET("hot")
    Call<List<String>> listHot();
    @GET("recommend")
    Call<List<String>> listRecommend();
    @GET("category")
    Call<List<CategoryItemBean>> listCategory();
    @GET("subject")
    Call<List<SubjectItemBean>> listSubject(@Query("index") int index);
    @GET("game")
    Call<List<AppListItemBean>> listGame(@Query("index") int index);
    @GET("app")
    Call<List<AppListItemBean>> listApp(@Query("index") int index);
    @GET("home")
    Call<HomeItemBean> listHome(@Query("index") int index);
    @GET("detail")
    Call<DetailBean> listDetail(@Query("packageName") String packageName);
}
