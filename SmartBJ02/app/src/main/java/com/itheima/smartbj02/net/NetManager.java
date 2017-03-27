package com.itheima.smartbj02.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by 龚浩 on 2017/3/23.
 */

public class NetManager {

    private static final int MAX_CACHE_SIZE = 5 * 1024 * 1024;
    public static RequestQueue mQueue;

    public static void init(Context context) {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(context);
        }

    }

    public static MyImgLoder getImgLoder() {
        return new MyImgLoder(MAX_CACHE_SIZE);
    }


    public static class  MyImgLoder extends LruCache<String ,Bitmap> implements ImageLoader.ImageCache {

        /**
         * @param maxSize for caches that do not override {@link #sizeOf}, this is
         *                the maximum number of entries in the cache. For all other caches,
         *                this is the maximum sum of the sizes of the entries in this cache.
         */
        public MyImgLoder(int maxSize) {
            super(maxSize);
        }

        /**
         *返回缓存图片的大小
         * @param key
         * @param value
         * @return
         */
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }

        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {

            put(url,bitmap);
        }

    }


}
