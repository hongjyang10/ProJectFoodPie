package com.lanou.dllo.myfoodpie.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
public class ShowActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backIv;
    private EditText showEt;

    @Override
    protected int setLayout() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        backIv = bindView(R.id.iv_delete_activity_show);
        showEt = bindView(R.id.et_show);
    }

    @Override
    protected void initData() {
        backIv.setOnClickListener(this);
        Intent intent = getIntent();
        String text = intent.getStringExtra("texts");
        showEt.setText(text);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_delete_activity_show:
                Intent intent = new Intent(this,QueryActivity.class);
                showEt.setText("");
                startActivity(intent);
                break;
        }
    }
}
