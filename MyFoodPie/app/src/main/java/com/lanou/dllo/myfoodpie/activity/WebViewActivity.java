package com.lanou.dllo.myfoodpie.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lanou.dllo.myfoodpie.R;

/*
         |              |
         | \            | \
         |   | | | | | |    | | | | |||||\
         |                          |||||||\
         |         ( )              ||||||||
         |                           |||||/
         |                  | | | | | |||/
         |    |             |          |
         |    |             |          |
       / |   | |            |          |\
      |      |/             |          \|
       \ |                  |
         |                  |
           \ | | | | | | | /
             |       |            <-----弱鸡
             |       |
             |       |
*/
public class WebViewActivity extends BaseActivity implements View.OnClickListener {
    private WebView webView;
    private Intent intent;
    private LinearLayout shareLayout, keepLayout;
    private ImageView backIv;

    @Override
    protected int setLayout() {
        intent = getIntent();

        return intent.getIntExtra("fragmentXml", 0);
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.wv_ac_web);
        shareLayout = bindView(R.id.ll_web_share);
        keepLayout = bindView(R.id.ll_web_keep);
        
        backIv = bindView(R.id.iv_web_back);
    }

    @Override
    protected void initData() {
        //webView显示图片方法
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
    }

    @Override
    protected void bindEvent() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        webView.loadUrl(intent.getStringExtra("url"));

        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_web_back:
                finish();
                break;
        }
    }
}
