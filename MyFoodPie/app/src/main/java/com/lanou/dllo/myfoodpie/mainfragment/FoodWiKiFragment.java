package com.lanou.dllo.myfoodpie.mainfragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.activity.QueryActivity;
import com.lanou.dllo.myfoodpie.adapter.FoodWiKiAdapter;
import com.lanou.dllo.myfoodpie.bean.FoodWiKiBean;
import com.lanou.dllo.myfoodpie.potting.CallBack;
import com.lanou.dllo.myfoodpie.potting.NetTool;
import com.lanou.dllo.myfoodpie.url.Url;

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
public class FoodWiKiFragment extends BaseFragment implements View.OnClickListener {
    private FoodWiKiAdapter sortAdapter, brandAdapter, chainAdapter;
    private String url = Url.FOODWIKI;
    private RecyclerView sortRv, brandRv, chainRv;
    private TextView searchTv;
    private FoodWiKiBean.GroupBean groupBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_foodwiki;
    }

    @Override
    protected void initView() {
        sortRv = bindView(R.id.rv_foodwiki_sort);
        brandRv = bindView(R.id.rv_foodwiki_brand);
        chainRv = bindView(R.id.rv_foodwiki_chain);
        searchTv = bindView(R.id.tv_food_search);
    }

    @Override
    protected void initData() {
        NetTool.getInstance().startRequest(url, FoodWiKiBean.class, new CallBack<FoodWiKiBean>() {

            @Override
            public void onSuccess(FoodWiKiBean response) {

                groupBean = response.getGroup().get(0);
                sortAdapter = new FoodWiKiAdapter(getContext());
                sortAdapter.setGroupBean(groupBean);
                sortAdapter.setType(0);
                sortRv.setAdapter(sortAdapter);
                sortRv.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));

                groupBean = response.getGroup().get(1);
                brandAdapter = new FoodWiKiAdapter(getContext());
                brandAdapter.setGroupBean(groupBean);
                brandAdapter.setType(1);
                brandRv.setAdapter(brandAdapter);
                brandRv.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));

                groupBean = response.getGroup().get(2);
                chainAdapter = new FoodWiKiAdapter(getContext());
                chainAdapter.setGroupBean(groupBean);
                chainAdapter.setType(2);
                chainRv.setAdapter(chainAdapter);
                chainRv.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    @Override
    protected void initEvent() {
        searchTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_food_search:
                Intent intent = new Intent(getActivity(), QueryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
