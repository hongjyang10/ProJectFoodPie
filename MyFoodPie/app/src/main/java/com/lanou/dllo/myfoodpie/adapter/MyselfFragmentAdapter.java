package com.lanou.dllo.myfoodpie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
public class MyselfFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String collectTitle[] = new String[]{"文章", "食物"};
    private String orderTitle[] = new String[]{"待付款", "已付款", "已发货"};

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MyselfFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    public String getcollectTitle(int position) {
        return collectTitle[position];
    }

    public String getorderTitle(int position) {
        return orderTitle[position];
    }
}
