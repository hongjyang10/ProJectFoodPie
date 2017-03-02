package com.lanou.dllo.myfoodpie.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.MyselfFragmentAdapter;
import com.lanou.dllo.myfoodpie.myselffragment.DeliverFragment;
import com.lanou.dllo.myfoodpie.myselffragment.FoodFragment;
import com.lanou.dllo.myfoodpie.myselffragment.ObligationFragment;
import com.lanou.dllo.myfoodpie.myselffragment.SpendFragment;
import com.lanou.dllo.myfoodpie.myselffragment.TextFragment;

import java.util.ArrayList;
import java.util.List;

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
public class PublicActivity extends BaseActivity implements View.OnClickListener {
    private Intent intent;
    private TextView backTv;
    private List<Fragment> fragments;
    private MyselfFragmentAdapter adapter;
    private ViewPager collectVp, orderVp;
    private TabLayout collectTab, orderTab;

    @Override
    protected int setLayout() {
        intent = getIntent();
        return intent.getIntExtra("xml", 0);
    }

    @Override
    protected void initView() {
        backTv = bindView(R.id.tv_login_back);
        initViewPhoto();
        initViewCollect();
        initViewUpload();
        initViewOrder();
    }

    private void initViewPhoto() {
    }

    private void initViewCollect() {
        if (intent.getIntExtra("xml", 0) == R.layout.activitv_collect) {
            collectVp = bindView(R.id.vp_collect);
            collectTab = bindView(R.id.tab_collect);
        }
    }

    private void initViewUpload() {

    }

    private void initViewOrder() {
        if (intent.getIntExtra("xml", 0) == R.layout.activitv_order) {
            orderVp = bindView(R.id.vp_order);
            orderTab = bindView(R.id.tab_order);
        }
    }

    @Override
    protected void initData() {
        backTv.setOnClickListener(this);
        initDataPhoto();
        initDataCollect();
        initDataUpload();
        initDataOrder();

    }

    private void initDataPhoto() {
    }

    private void initDataCollect() {
        if (intent.getIntExtra("xml", 0) == R.layout.activitv_collect) {
            fragments = new ArrayList<>();
            fragments.add(new TextFragment());
            fragments.add(new FoodFragment());
            adapter = new MyselfFragmentAdapter(getSupportFragmentManager());
            adapter.setFragments(fragments);
            collectVp.setAdapter(adapter);
            collectTab.setupWithViewPager(collectVp);
            for (int i = 0; i < collectTab.getTabCount(); i++) {
                TabLayout.Tab tab = collectTab.getTabAt(i);
                tab.setText(adapter.getcollectTitle(i));
            }
        }
    }

    private void initDataUpload() {

    }

    private void initDataOrder() {
        if (intent.getIntExtra("xml", 0) == R.layout.activitv_order) {
            fragments = new ArrayList<>();
            fragments.add(new SpendFragment());
            fragments.add(new ObligationFragment());
            fragments.add(new DeliverFragment());
            adapter = new MyselfFragmentAdapter(getSupportFragmentManager());
            adapter.setFragments(fragments);
            orderVp.setAdapter(adapter);
            orderTab.setupWithViewPager(orderVp);
            for (int i = 0; i < orderTab.getTabCount(); i++) {
                TabLayout.Tab tab = orderTab.getTabAt(i);
                tab.setText(adapter.getorderTitle(i));
            }
        }
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_back:
                finish();
                break;
        }
    }
}
