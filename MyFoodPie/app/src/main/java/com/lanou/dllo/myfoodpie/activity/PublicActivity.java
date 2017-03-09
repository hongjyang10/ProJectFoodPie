package com.lanou.dllo.myfoodpie.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.MyselfFragmentAdapter;
import com.lanou.dllo.myfoodpie.myselffragment.DeliverFragment;
import com.lanou.dllo.myfoodpie.myselffragment.FoodFragment;
import com.lanou.dllo.myfoodpie.myselffragment.ObligationFragment;
import com.lanou.dllo.myfoodpie.myselffragment.SpendFragment;
import com.lanou.dllo.myfoodpie.myselffragment.TextFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

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
    private TextView qqTv;
    private Button noLogin;

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
        if (intent.getIntExtra("xml", 0) == R.layout.activity_login) {
            qqTv = bindView(R.id.tv_activity_qq);
        }
        if (intent.getIntExtra("xml", 0) == R.layout.activity_set) {
           noLogin = bindView(R.id.btn_set_nologin);
        }
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
        if (intent.getIntExtra("xml", 0) == R.layout.activity_login) {


        }

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
        if (intent.getIntExtra("xml", 0) == R.layout.activity_login) {
            qqTv.setOnClickListener(this);
        }
        if (intent.getIntExtra("xml", 0) == R.layout.activity_set) {
            noLogin.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_back:
                finish();
                break;
            case R.id.tv_activity_qq:
                ShareSDK.initSDK(this);
                Toast.makeText(this, "aaaaa", Toast.LENGTH_SHORT).show();
                Platform qq = ShareSDK.getPlatform(getBaseContext(), QQ.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {


                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        arg0.getDb().exportData();
                        SharedPreferences sharedPreferences =getSharedPreferences("register", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("register", true);
                        editor.commit();

                        Iterator ite = arg2.entrySet().iterator();
                        while (ite.hasNext()) {
                            Map.Entry entry = (Map.Entry) ite.next();
                            Object key = entry.getKey();
                            if (key.toString().equals("nickname")) {
                                String nickname = null;
                                nickname = entry.getValue().toString();
                                SharedPreferences sp1 =getSharedPreferences("register", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = sp1.edit();
                                editor1.putString("nickname", nickname);
                                editor1.commit();

                            }
                            if (key.toString().equals("figureurl_qq_2")) {
                                String headurl = null;
                                headurl = entry.getValue().toString();
                                SharedPreferences sp2 =getSharedPreferences("register", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sp2.edit();
                                editor2.putString("headurl", headurl);
                                editor2.commit();

                            }

                        }

                        sendBroadcast(new Intent("START"));

                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
                qq.showUser(null);//执行登录，登录后在回调里面获取用户资料
                finish();
                break;
            case R.id.btn_set_nologin:
                Platform qqq = ShareSDK.getPlatform(getBaseContext(), QQ.NAME);
                qqq.removeAccount();
                SharedPreferences sp = getSharedPreferences("register", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("register", false);
                editor.commit();
                finish();
                sendBroadcast(new Intent("START"));
                break;
        }
    }
}
