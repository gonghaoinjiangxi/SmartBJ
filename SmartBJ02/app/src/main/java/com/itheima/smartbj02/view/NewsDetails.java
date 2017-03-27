package com.itheima.smartbj02.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.itheima.smartbj02.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 龚浩 on 2017/3/25.
 */

public class NewsDetails extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.text_size)
    ImageView mTextSize;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.pro_bar)
    ProgressBar mProBar;
    private int mChickId = 2;
    private WebSettings mSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String load = intent.getStringExtra("load");
        mWebView.setWebChromeClient(mChromeClient);
        mWebView.loadUrl(load);
        mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);
    }
    
    private WebChromeClient mChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(newProgress == 100) {
                mProBar.setVisibility(View.GONE);
            }
        }
    };

    @OnClick({R.id.iv_back, R.id.text_size})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.text_size:
                creatDialog();
                break;
        }
    }

    private void creatDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("字体大小");
        builder.setSingleChoiceItems(new String[]{"最小", "较小", "默认", "较大", "最大"}, mChickId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                switch (which) {
                    case 0:
                        mSettings.setTextSize(WebSettings.TextSize.SMALLEST);
                        break;
                    case 1:
                        mSettings.setTextSize(WebSettings.TextSize.SMALLER);
                        break;
                    case 2:
                        mSettings.setTextSize(WebSettings.TextSize.NORMAL);
                        break;
                    case 3:
                        mSettings.setTextSize(WebSettings.TextSize.LARGER);
                        break;
                    case 4:
                        mSettings.setTextSize(WebSettings.TextSize.LARGEST);
                        break;
                }
                mChickId = which;
            }
        });
        builder.show();
    }
}
