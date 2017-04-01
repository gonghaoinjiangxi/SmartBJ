package com.itheima.googleplaydemo.bean;

/**
 * Created by 龚浩 on 2017/4/1.
 */

public class DownLoadInfo {

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    private int progress;

    private String pacakageName;
    private int status;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    private String downloadUrl;

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    private String downloadPath;

    public String getPacakageName() {
        return pacakageName;
    }

    public void setPacakageName(String pacakageName) {
        this.pacakageName = pacakageName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
