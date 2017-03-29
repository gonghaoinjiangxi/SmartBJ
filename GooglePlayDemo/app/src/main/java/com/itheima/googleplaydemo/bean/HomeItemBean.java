package com.itheima.googleplaydemo.bean;

import java.util.List;

/**
 * Created by 龚浩 on 2017/3/29.
 */

public class HomeItemBean {

    private List<String> picture;
    /**
     * id : 1525489
     * name : 黑马程序员
     * packageName : com.itheima.www
     * iconUrl : app/com.itheima.www/icon.jpg
     * stars : 5
     * size : 91767
     * downloadUrl : app/com.itheima.www/com.itheima.www.apk
     * des : 产品介绍：google市场app测试。
     */

    private List<AppListItemBean> list;

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public List<AppListItemBean> getList() {
        return list;
    }

    public void setList(List<AppListItemBean> list) {
        this.list = list;
    }

}
