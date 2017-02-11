package com.lanou.dllo.myfoodpie.activity;

import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.mainfragment.FoodWiKiFragment;
import com.lanou.dllo.myfoodpie.mainfragment.MyselfFragment;
import com.lanou.dllo.myfoodpie.mainfragment.RoamEatFragment;

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
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        radioGroup = bindView(R.id.rg_main);
    }

    @Override
    protected void initData() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frag_main, new FoodWiKiFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void bindEvent() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.btn_foodwiki:
                fragmentTransaction.replace(R.id.frag_main, new FoodWiKiFragment());
                fragmentTransaction.commit();
                break;
            case R.id.btn_roameat:
                fragmentTransaction.replace(R.id.frag_main, new RoamEatFragment());
                fragmentTransaction.commit();
                break;
            case R.id.btn_myself:
                fragmentTransaction.replace(R.id.frag_main, new MyselfFragment());
                fragmentTransaction.commit();
                break;
        }
    }
}
