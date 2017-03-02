package com.lanou.dllo.myfoodpie.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou.dllo.myfoodpie.R;
import com.squareup.picasso.Picasso;

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
public class RoamEaTHomeActivity extends BaseActivity {
    private ImageView photoIv,headIV;
    private TextView nameTv,numberTv;
    private Intent intent;


    @Override
    protected int setLayout() {
        return R.layout.activity_roameathome;
    }

    @Override
    protected void initView() {
        photoIv = bindView(R.id.iv_roameathome_activity);
        headIV = bindView(R.id.cir_activity_rm_home);
        nameTv = bindView(R.id.tv_activity_rm_home_name);
        numberTv = bindView(R.id.tv_activity_rm_home_number);
    }

    @Override
    protected void initData() {
        intent = getIntent();
        String photo = intent.getStringExtra("imgs");
        String head = intent.getStringExtra("photo");
        String name = intent.getStringExtra("name");
        String number = intent.getStringExtra("number");
        Glide.with(this).load(photo).into(photoIv);
        Picasso.with(this).load(head).into(headIV);
        nameTv.setText(name);
        numberTv.setText(number);
    }

    @Override
    protected void bindEvent() {

    }
}
