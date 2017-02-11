package com.lanou.dllo.myfoodpie.activity;

import android.content.Intent;
import android.os.CountDownTimer;

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
public class StartActivity extends BaseActivity {
    private CountDownTimer countDownTimer;

    @Override
    protected int setLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                countDownTimer.cancel();
            }
        }.start();
    }

    @Override
    protected void bindEvent() {

    }
}
