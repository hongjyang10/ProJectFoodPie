package com.lanou.dllo.myfoodpie.mainfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.RoamEatFragmentAdapter;
import com.lanou.dllo.myfoodpie.roameatfragment.DelicacyRoamEatFragment;
import com.lanou.dllo.myfoodpie.roameatfragment.EvaluatingRoamEatFragment;
import com.lanou.dllo.myfoodpie.roameatfragment.HomeRoamEatFragment;
import com.lanou.dllo.myfoodpie.roameatfragment.KonwledgeRoamEatFragment;

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
        adapter = new RoamEatFragmentAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeRoamEatFragment());
        fragments.add(new EvaluatingRoamEatFragment());
        fragments.add(new KonwledgeRoamEatFragment());
        fragments.add(new DelicacyRoamEatFragment());
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