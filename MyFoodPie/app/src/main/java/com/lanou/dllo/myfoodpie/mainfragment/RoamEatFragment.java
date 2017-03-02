package com.lanou.dllo.myfoodpie.mainfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.RoamEatFragmentAdapter;
import com.lanou.dllo.myfoodpie.roameatfragment.EvaluatingRoamEatFragment;
import com.lanou.dllo.myfoodpie.roameatfragment.HomeRoamEatFragment;
import com.lanou.dllo.myfoodpie.roameatfragment.EndRoamEatFragment;
import com.lanou.dllo.myfoodpie.url.Url;

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
public class RoamEatFragment extends BaseFragment {
    private ViewPager viewPager;
    private RoamEatFragmentAdapter adapter;
    private List<Fragment> fragments;
    private TabLayout tabLayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_roameat;
    }

    @Override
    protected void initView() {
        viewPager = bindView(R.id.vp_roameat);
        tabLayout = bindView(R.id.tab_roameat);
        adapter = new RoamEatFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeRoamEatFragment());
        fragments.add(new EvaluatingRoamEatFragment());
        Fragment knowFragment = new EndRoamEatFragment();
        Bundle bundleKnow = new Bundle();
        bundleKnow.putString("url", Url.ROAMEATKNOW);
        knowFragment.setArguments(bundleKnow);
        fragments.add(knowFragment);
        Fragment deliFragment = new EndRoamEatFragment();
        Bundle bundleDeli = new Bundle();
        bundleDeli.putString("url", Url.ROAMEATDELI);
        deliFragment.setArguments(bundleDeli);
        fragments.add(deliFragment);
        adapter.setFragments(fragments);
        adapter.setContext(getContext());
    }

    @Override
    protected void initEvent() {
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(adapter.getTitleView(i));
        }
    }
}