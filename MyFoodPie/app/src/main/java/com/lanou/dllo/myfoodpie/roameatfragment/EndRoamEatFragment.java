package com.lanou.dllo.myfoodpie.roameatfragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.activity.WebViewActivity;
import com.lanou.dllo.myfoodpie.adapter.KnowRoamEatAdapter;
import com.lanou.dllo.myfoodpie.bean.KownRoamEatBean;
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
public class EndRoamEatFragment extends BaseFragment {
    private String url,pageUrl;
    private PullToRefreshListView listView;
    private KnowRoamEatAdapter adapter;
    private Handler handler;
    private List<KownRoamEatBean.FeedsBean> kownBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_roameat_end;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.lv_rm_end);

    }

    @Override
    protected void initData() {
        ILoadingLayout startLayout = listView.getLoadingLayoutProxy(true, false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        adapter = new KnowRoamEatAdapter(getContext());
        listView.setAdapter(adapter);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        url = getArguments().getString("url");
        NetTool.getInstance().startRequest(url, KownRoamEatBean.class, new CallBack<KownRoamEatBean>() {
            @Override
            public void onSuccess(KownRoamEatBean response) {
                kownBean = response.getFeeds();
                adapter.setKownBean(kownBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        if (msg.what == 0) {
                            int page = 1;
                            page++;
                            if(url=="http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1&phone_model=M578CA&channel=meizu%20"){
                                pageUrl= "http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=3&per=10&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1&phone_model=M578CA&channel=meizu%20";

                            }else if (url == "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1&phone_model=M578CA&channel=meizu%20"){
                                pageUrl= "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10&token=&user_key=&app_version=2.6&app_device=Android&os_version=5.1&phone_model=M578CA&channel=meizu%20";

                            }
                            NetTool.getInstance().startRequest(pageUrl, KownRoamEatBean.class, new CallBack<KownRoamEatBean>() {
                                @Override
                                public void onSuccess(KownRoamEatBean response) {
                                    kownBean.addAll(response.getFeeds());
                                    adapter.setKownBean(kownBean);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });

                            listView.onRefreshComplete();
                            Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                    }

                }).start();

            }
        });
    }

    @Override
    protected void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", kownBean.get(position).getLink());
                intent.putExtra("fragmentXml",R.layout.activity_web);
                startActivity(intent);
            }
        });
    }
}
