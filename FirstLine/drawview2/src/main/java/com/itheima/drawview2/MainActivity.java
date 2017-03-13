package com.itheima.drawview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.itheima.drawview2.com.itheima.drawview2.view.MyVeiw;

public class MainActivity extends AppCompatActivity {

    private MyVeiw mMyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mMyView = (MyVeiw) findViewById(R.id.my_view);
    }

    private void initData() {
        mMyView.setOnTaggleChangedListener(new MyVeiw.OnTaggleChanged() {
            @Override
            public void isChanged(boolean isOpen) {
                if (isOpen) {
                    Toast.makeText(MainActivity.this, "开启了", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "关闭了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initListener() {
        mMyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyView.setOnClickListener();
            }
        });
    }


}
