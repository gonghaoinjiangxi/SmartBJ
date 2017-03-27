package com.itheima.smartbj02.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 龚浩 on 2017/3/22.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayOutId());
        ButterKnife.bind(this);
        init();
    }

    public void init() {
    }

    public abstract int getLayOutId() ;

    public void navigateTo(Class clazz) {
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }
}
