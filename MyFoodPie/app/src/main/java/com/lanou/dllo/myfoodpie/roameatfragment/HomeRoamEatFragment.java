package com.lanou.dllo.myfoodpie.roameatfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.HomeRoamEatAdapter;
import com.lanou.dllo.myfoodpie.bean.HomeRoamEatBean;
import com.lanou.dllo.myfoodpie.mainfragment.BaseFragment;
import com.lanou.dllo.myfoodpie.potting.CallBack;
import com.lanou.dllo.myfoodpie.potting.NetTool;
import com.lanou.dllo.myfoodpie.url.Scorell;
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
    int pager = 1;
    private String url = Url.ROAMEATHOME;
    private RecyclerView recyclerView;
    private HomeRoamEatAdapter adapter;
    private List<HomeRoamEatBean.FeedsBean> homeBean;
    private Receiver receiver;

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
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        NetTool.getInstance().startRequest(url, HomeRoamEatBean.class, new CallBack<HomeRoamEatBean>() {
            @Override
            public void onSuccess(HomeRoamEatBean response) {
                homeBean=response.getFeeds() ;
                adapter.setHomeBean(homeBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        Scorell scorell = new Scorell(recyclerView, getContext());

        scorell.load();

        receiver=new Receiver();

        IntentFilter intentFilter = new IntentFilter("LOADING");

        getContext().registerReceiver(receiver, intentFilter);

    }

    @Override
    protected void initEvent() {
    }


    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            pager++;
            final String freshenUrl = "http://food.boohee.com/fb/v1/feeds/category_feed?page=" + pager + "&category=1&per=10&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1&phone_model=M578CA&channel=meizu%20";

        NetTool.getInstance().startRequest(freshenUrl, HomeRoamEatBean.class, new CallBack<HomeRoamEatBean>() {
                @Override
                public void onSuccess(HomeRoamEatBean response) {
                    homeBean.addAll(response.getFeeds());
                    adapter.setHomeBean(homeBean);
                }

                @Override
                public void onError(Throwable e) {

                }
            });


        }
    }
}