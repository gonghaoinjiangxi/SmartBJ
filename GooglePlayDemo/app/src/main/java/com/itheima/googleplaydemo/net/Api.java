package com.itheima.googleplaydemo.net;

import com.itheima.googleplaydemo.bean.CategoryItemBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

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
}
