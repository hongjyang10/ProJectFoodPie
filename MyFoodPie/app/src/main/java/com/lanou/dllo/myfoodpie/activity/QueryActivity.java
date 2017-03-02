package com.lanou.dllo.myfoodpie.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.queryfragment.QueryFragment;

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
public class QueryActivity extends BaseActivity {
    private ImageView queryIv;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected int setLayout() {
        return R.layout.activity_query;
    }

    @Override
    protected void initView() {
        queryIv = bindView(R.id.iv_query_back);

    }

    @Override
    protected void initData() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_query, new QueryFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void bindEvent() {
        queryIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
