package com.itheima.smartbj02.net;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import static android.content.ContentValues.TAG;

/**
 * Created by 龚浩 on 2017/3/23.
 */

public class SmartBJRequest<T> extends JsonRequest<T> {
    private Class<T> mClass ;
    private NetListener<T> mListener;

    public SmartBJRequest( String url,Class clazz,NetListener<T> listener) {
        super(Method.GET, url, null,listener,listener);
        mClass = clazz;
        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {


        String json = null;
        try {
            json = new String(response.data, JsonRequest.PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        T result = gson.fromJson(json, mClass);
        Log.d(TAG, "parseNetworkResponse: ==="+result.toString());
        Response<T> success = Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        return success;
    }

    //将结果扔到主线程
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
