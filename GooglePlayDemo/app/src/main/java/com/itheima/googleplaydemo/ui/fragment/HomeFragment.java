package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;

import com.itheima.googleplaydemo.app.Const;
import com.itheima.googleplaydemo.bean.HomeItemBean;
import com.itheima.googleplaydemo.net.GoogleRetrofit;
import com.leon.loopviewpagerlib.FunBanner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/3/26.
 */
public class HomeFragment extends BaseAppListFragment {

    private List<String> mPicture;

    @Override
    protected View getHeaderView() {

       return new FunBanner.Builder(getContext())
                .setHeightWidthRatio(0.377f)
                .setEnableAutoLoop(true)
                .setImageUrlHost(Const.HTTP_IMAGINE_URL)
                .setImageUrls(mPicture)
                .build();
    }

    @Override
    public void startLoadData() {
        super.startLoadData();
        Call<HomeItemBean> listHome = GoogleRetrofit.getInstance().getApi().listHome(0);
        listHome.enqueue(new Callback<HomeItemBean>() {
            @Override
            public void onResponse(Call<HomeItemBean> call, Response<HomeItemBean> response) {

                mPicture = response.body().getPicture();
                OnSuccess();
                getDataList().addAll(response.body().getList());
            }

            @Override
            public void onFailure(Call<HomeItemBean> call, Throwable t) {
                onFailer();
            }
        });
    }


    @Override
    void loadMoreData() {
        Call<HomeItemBean> listHome = GoogleRetrofit.getInstance().getApi().listHome(getDataList().size());
        listHome.enqueue(new Callback<HomeItemBean>() {
            @Override
            public void onResponse(Call<HomeItemBean> call, Response<HomeItemBean> response) {
                getDataList().addAll(response.body().getList());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HomeItemBean> call, Throwable t) {
                onFailer();
            }
        });
    }
}
