package com.itheima.googleplaydemo.net;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.itheima.googleplaydemo.app.Const;
import com.itheima.googleplaydemo.bean.DetailBean;
import com.itheima.googleplaydemo.bean.DownLoadInfo;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 龚浩 on 2017/4/1.
 */

public class DownLoadManager {
    private static DownLoadManager sDownLoadManager;
    private static final String DOWNLOAD_DIRECTORY = Environment.getExternalStorageDirectory()
            + "/Android/data/" + "com.itheima.googleplaydemo" + "/apk/";
    private HashMap<String, DownLoadInfo> mHashMap = new HashMap<String, DownLoadInfo>();
    public static final int STATE_UN_DOWNLOAD = 0;//未下载
    public static final int STATE_DOWNLOADING = 1;//下载中
    public static final int STATE_PAUSE = 2;//暂停下载
    public static final int STATE_WAITING = 3;//等待下载
    public static final int STATE_FAILED = 4;//下载失败
    public static final int STATE_DOWNLOADED = 5;//下载完成
    public static final int STATE_INSTALLED = 6;//已安装
    private static final String TAG = "DownLoadManager";

    //私有化构造,防止外部类通过构造创建本类对象
    private DownLoadManager() {
        createDownloadPath();
    }

    private void createDownloadPath() {
        File file = new File(DOWNLOAD_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    public static DownLoadManager getInstant() {
        if (sDownLoadManager == null) {
            synchronized (DownLoadManager.class) {
                if (sDownLoadManager == null) {
                    sDownLoadManager = new DownLoadManager();
                }
            }
        }
        return sDownLoadManager;
    }


    //由于多处需要判断下载的状态,所以,在DownLoadManager中初始化downinfo
    public DownLoadInfo initDownloadInfo(Context context, DetailBean bean) {
        //以一个map集合的形式将信息存储起来,判断如果存在缓存,那么就直接获取到该对象
      /*  if (mHashMap.get(bean.getPackageName()) != null) {
            return mHashMap.get(bean.getPackageName());
        }*/

        DownLoadInfo downLoadInfo = new DownLoadInfo();
        String path = DOWNLOAD_DIRECTORY + bean.getPackageName();
        String downloadUrl = bean.getDownloadUrl();
        downLoadInfo.setDownloadUrl(downloadUrl);
        downLoadInfo.setDownloadPath(path);
        downLoadInfo.setPacakageName(bean.getPackageName());
        File file = new File(path);
        if (!file.exists()) {
            downLoadInfo.setStatus(STATE_UN_DOWNLOAD);
        } else if (file.length() == bean.getSize()) {
            downLoadInfo.setStatus(STATE_DOWNLOADED);
        } else {
            downLoadInfo.setProgress((int) file.length());
        }

        getPacakageInstall(context, bean, downLoadInfo);
        mHashMap.put(bean.getPackageName(),downLoadInfo);
        Log.d(TAG, "initDownloadInfo: ====++++++"+bean.getPackageName());

        return downLoadInfo;
    }

    private void getPacakageInstall(Context context, DetailBean bean, DownLoadInfo downLoadInfo) {

        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(bean.getPackageName(), PackageManager.GET_ACTIVITIES);
            if(info != null) {
                downLoadInfo.setStatus(STATE_INSTALLED);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void handleClickEvent(Context context, DetailBean bean) {
        Log.d(TAG, "getPacakageInstall: ====++++++"+bean.getPackageName());
        DownLoadInfo info = mHashMap.get(bean.getPackageName());
        switch (info.getStatus()) {
            case DownLoadManager.STATE_UN_DOWNLOAD:
                startDownload(info);
                break;
            case DownLoadManager.STATE_DOWNLOADING:

                break;
            case DownLoadManager.STATE_PAUSE:

                break;
            case DownLoadManager.STATE_WAITING:

                break;
            case DownLoadManager.STATE_FAILED:

                break;
            case DownLoadManager.STATE_DOWNLOADED:
                startInstall(context,info);
                break;
            case DownLoadManager.STATE_INSTALLED:
                openApp(context,info);
                break;
        }
    }

    private void openApp(Context context, DownLoadInfo info) {
        PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(info.getPacakageName());
        context.startActivity(intent);
    }

    private void startInstall(Context context, DownLoadInfo info) {
      /*  <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:scheme="content" />
        <data android:scheme="file" />
        <data android:mimeType="application/vnd.android.package-archive" />
        </intent-filter>*/

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.parse("file:" + info.getDownloadPath()),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);



    }

    private void startDownload(DownLoadInfo info) {
        //开始下载,开启子线程
        DownloadTask task = new DownloadTask();
        task.execute(info);
    }


    class DownloadTask extends AsyncTask<DownLoadInfo, Void, Void> {

        private OkHttpClient mOkHttpClient = new OkHttpClient();

        //开启线程下载
        @Override
        protected Void doInBackground(DownLoadInfo... params) {
            Log.d(TAG, "doInBackground: ============================开始下载");
            DownLoadInfo info = params[0];
            String path = info.getDownloadPath();
            int progress = info.getProgress();
            //String url = info.getDownloadUrl();
            String url = Const.HTTP_URL + "download?name=" + info.getDownloadUrl() + "&range=" + info.getProgress();
            Request request = new Request.Builder().url(url).build();
            InputStream inputStream = null;
            FileOutputStream outputStream = null;

            try {
                Response response = mOkHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    inputStream = response.body().byteStream();
                    outputStream = new FileOutputStream(path);
                    byte[] buffer = new byte[1024 * 4];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len);
                        progress = progress +len;
                        Log.d(TAG, "doInBackground: ====" + progress);
                    }
                    Log.d(TAG, "doInBackground: ============================下载完成");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeStream(inputStream);
                closeStream(outputStream);
            }
            return null;
        }
    }

    public void closeStream(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
