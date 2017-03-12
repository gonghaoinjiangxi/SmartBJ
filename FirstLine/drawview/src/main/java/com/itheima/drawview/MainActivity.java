package com.itheima.drawview;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.itheima.drawview.com.itheima.drawview.view.MyView;

public class MainActivity extends AppCompatActivity {

    private MyView mMyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mMyView = (MyView) findViewById(R.id.my_view);
    }


    public void start(View view) {

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {

                    final int finalI = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMyView.setprogress(finalI);
                        }
                    });


                    SystemClock.sleep(100);
                }
            }
        }.start();
    }
}
