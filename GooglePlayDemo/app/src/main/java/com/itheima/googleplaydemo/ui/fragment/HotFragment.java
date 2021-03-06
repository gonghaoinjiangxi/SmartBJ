package com.itheima.googleplaydemo.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.net.Api;
import com.itheima.googleplaydemo.net.GoogleRetrofit;
import com.itheima.googleplaydemo.utils.ColorUtils;
import com.itheima.googleplaydemo.utils.LogUtils;
import com.itheima.googleplaydemo.widgest.FlowLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 龚浩 on 2017/3/26.
 */

public class HotFragment extends BaseFragment {

    private Api mApi;
    private List<String> mData;

    @Override
    public void startLoadData() {
        mApi = GoogleRetrofit.getInstance().getApi();
        Call<List<String>> call = mApi.listHot();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                mData = response.body();
                OnSuccess();

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                LogUtils.logd("==========失败了",t.getLocalizedMessage());
                onFailer();
            }
        });
    }



    @Override
    public View getContentView() {
        FlowLayout flowLayout = new FlowLayout(getContext());
        int padding = getContext().getResources().getDimensionPixelOffset(R.dimen.smaller_size);
        flowLayout.setPadding(padding,padding,padding,padding);
        if(mData != null) {
            for (int i = 0; i < mData.size(); i++) {
                TextView view = new TextView(getContext());
                StateListDrawable selector = new StateListDrawable();
                GradientDrawable drawable = new GradientDrawable();
                GradientDrawable pressDrawable = new GradientDrawable();
                drawable.setCornerRadius(padding);
                drawable.setColor(ColorUtils.getArgb());

                pressDrawable.setCornerRadius(padding);
                pressDrawable.setColor(Color.BLUE);

                selector.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
                selector.addState(new int[]{},drawable);
                view.setText(mData.get(i));
                view.setClickable(true);
                view.setPadding(padding, padding, padding, padding);
                view.setTextSize(18);
                view.setBackgroundDrawable(selector);
                view.setGravity(Gravity.CENTER);
                flowLayout.addView(view);
            }
        }
        return flowLayout;
    }
}
