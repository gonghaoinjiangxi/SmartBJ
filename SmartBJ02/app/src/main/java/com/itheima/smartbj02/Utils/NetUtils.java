package com.itheima.smartbj02.Utils;

import android.util.Log;

import com.android.volley.VolleyError;
import com.itheima.smartbj02.net.NetListener;
import com.itheima.smartbj02.net.NetManager;
import com.itheima.smartbj02.net.SmartBJRequest;

import static android.content.ContentValues.TAG;


/**
 * Created by 龚浩 on 2017/3/23.
 */

public class NetUtils<T> {


    public void sentRequest(String url , Class clazz ) {
        SmartBJRequest request = new SmartBJRequest(url, clazz, mListener);
        NetManager.mQueue.add(request);
    }



    private NetListener<T> mListener = new NetListener<T>() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if(mCompleteListener != null) {
                Log.d(TAG, "onErrorResponse: ==失败了"+error);
                mCompleteListener.OnError(error);
            }
        }

        @Override
        public void onResponse(T response) {
            Log.d(TAG, "onResponse: ==成功了");
            mCompleteListener.OnSuccess(response);
        }
    };

    private OnResponeCompleteListener mCompleteListener ;

    public void setOnCompleteListener(OnResponeCompleteListener listener) {
        mCompleteListener = listener;
    }

    public interface OnResponeCompleteListener<T> {
        void OnSuccess(T response);
        void OnError(VolleyError error);
    }
}
