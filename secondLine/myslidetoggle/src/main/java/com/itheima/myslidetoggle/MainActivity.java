package com.itheima.myslidetoggle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.itheima.myslidetoggle.com.itheima.myslidetoggle.view.MyView;

public class MainActivity extends AppCompatActivity {

    private MyView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initListener();
    }

    private void initListener() {
        mView.setOnUnLockListener(new MyView.OnUnLockListener() {
            @Override
            public void UnLock() {
                Toast.makeText(MainActivity.this,"解锁",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void initView() {
        mView = (MyView) findViewById(R.id.my_view);
    }


}
