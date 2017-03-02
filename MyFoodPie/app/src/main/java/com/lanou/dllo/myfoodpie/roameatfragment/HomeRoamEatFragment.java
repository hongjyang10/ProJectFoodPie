package com.lanou.dllo.myfoodpie.roameatfragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.HomeRoamEatAdapter;
import com.lanou.dllo.myfoodpie.bean.HomeRoamEatBean;
import com.lanou.dllo.myfoodpie.mainfragment.BaseFragment;
import com.lanou.dllo.myfoodpie.potting.CallBack;
import com.lanou.dllo.myfoodpie.potting.NetTool;
import com.lanou.dllo.myfoodpie.url.Url;

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
public class HomeRoamEatFragment extends BaseFragment {
    private String url = Url.ROAMEATHOME;
    private RecyclerView recyclerView;
    private HomeRoamEatAdapter adapter;
    private List<HomeRoamEatBean.FeedsBean> homeBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_roameat_home;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.rv_rm_home);

    }

    @Override
    protected void initData() {
        adapter = new HomeRoamEatAdapter(getActivity());
//        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, StaggeredGridLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        NetTool.getInstance().startRequest(url, HomeRoamEatBean.class, new CallBack<HomeRoamEatBean>() {
            @Override
            public void onSuccess(HomeRoamEatBean response) {
                homeBean = response.getFeeds();
                adapter.setHomeBean(homeBean);
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
