package com.lanou.dllo.myfoodpie.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();

        initData();

        bindEvent();

    }


    protected abstract int setLayout();

    //绑定id
    protected abstract void initView();

    //初始化数据的
    protected abstract void initData();

    //绑定监听事件的
    protected abstract void bindEvent();

    //绑定id方法
    protected <T extends View> T bindView(int resId) {
        return (T) findViewById(resId);
    }
}