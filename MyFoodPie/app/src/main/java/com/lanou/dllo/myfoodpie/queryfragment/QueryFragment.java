package com.lanou.dllo.myfoodpie.queryfragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.FoodWiKiQueryAdapter;
import com.lanou.dllo.myfoodpie.bean.FoodWiKiQueryBean;
import com.lanou.dllo.myfoodpie.mainfragment.BaseFragment;
import com.lanou.dllo.myfoodpie.potting.CallBack;
import com.lanou.dllo.myfoodpie.potting.NetTool;

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
public class QueryFragment extends BaseFragment {
    private FoodWiKiQueryAdapter adapter;
    private List<String> strings;
    private RecyclerView queryRv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_query;
    }

    @Override
    protected void initView() {
        queryRv = bindView(R.id.rv_query);
    }

    @Override
    protected void initData() {
        String url = "http://food.boohee.com/fb/v1/keywords?token=pxN9j6S1za8PGQzefHxh&user_key=e88bf69a-92d5-4dd4-89af-69aef89dc639&\" +\n" +
                "            \"app_version=2.6&app_device=Android&os_version=6.0.1&phone_model=MI+NOTE+LTE&channel=xiaomi";
        adapter = new FoodWiKiQueryAdapter(getContext());
        queryRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        queryRv.setAdapter(adapter);
        NetTool.getInstance().startRequest(url, FoodWiKiQueryBean.class, new CallBack<FoodWiKiQueryBean>() {
            @Override
            public void onSuccess(FoodWiKiQueryBean response) {
                strings = response.getKeywords();
                adapter.setStrings(strings);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    protected void initEvent() {

    }
}
