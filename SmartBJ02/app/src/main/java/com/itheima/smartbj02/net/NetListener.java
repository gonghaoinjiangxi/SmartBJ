package com.itheima.smartbj02.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by 龚浩 on 2017/3/23.
 */

public abstract class NetListener<T> implements Response.Listener<T>,Response.ErrorListener {
    @Override
    public abstract void onErrorResponse(VolleyError error) ;

    @Override
    public abstract void onResponse(T response) ;
}
