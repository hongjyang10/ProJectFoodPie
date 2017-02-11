package com.lanou.dllo.myfoodpie.roameatfragment;

import android.widget.ListView;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.mainfragment.BaseFragment;

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
public class KonwledgeRoamEatFragment extends BaseFragment {
    private ListView listView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_roameat_knowledge;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.lv_rm_knowledge);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
