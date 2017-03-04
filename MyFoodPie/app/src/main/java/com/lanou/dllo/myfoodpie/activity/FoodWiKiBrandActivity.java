package com.lanou.dllo.myfoodpie.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.FoodWiKiBrandAdapter;
import com.lanou.dllo.myfoodpie.bean.FoodWiKiBrandBean;
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
            / \     / \
*/
public class FoodWiKiBrandActivity extends BaseActivity implements View.OnClickListener {
    public String sortUrl;
    private List<FoodWiKiBrandBean.FoodsBean> foodsBeen;
    private FoodWiKiBrandAdapter adapter;
    private PullToRefreshListView listView;
    private Intent intent;
    private Handler handler;
    private TextView titleTv;
    private String sortTitle[] = new String[]{"主食类", "肉蛋类", "大豆及制品", "蔬菜菌藻类", "水果类", "奶类", "油脂类", "坚果类", "调味品", "饮料类", "零食,点心..", "其他"};
    private String brandTitle[] = new String[]{"薄荷", "未得鲜", "雀巢", "光明", "伊利", "蒙牛", "三全", "永和", "南方", "康师傅", "思念", "新农哥"};
    private String chainTitle[] = new String[]{"肯德基", "麦当劳", "星巴克", "吉野家", "德克士", "大娘水饺"};
    private int position;

    @Override
    protected int setLayout() {
        return R.layout.activity_foodwikibrand;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.lv_foodwiki_brand);
        titleTv = bindView(R.id.tv_foodwikibrand_title);
    }

    @Override
    protected void initData() {
        ILoadingLayout startLayout = listView.getLoadingLayoutProxy(true, false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开以刷新");
        adapter = new FoodWiKiBrandAdapter(getBaseContext());
        listView.setAdapter(adapter);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        intent = getIntent();
        sortUrl = intent.getStringExtra("sort");
        NetTool.getInstance().startRequest(sortUrl, FoodWiKiBrandBean.class, new CallBack<FoodWiKiBrandBean>() {
            @Override
            public void onSuccess(FoodWiKiBrandBean response) {
                foodsBeen = response.getFoods();
                adapter.setFoodsBeanList(foodsBeen);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        int type = intent.getIntExtra("type", 0);
        if (type == 0) {
            position = intent.getIntExtra("position", 0);
            titleTv.setText(sortTitle[position]);
        } else if (type == 1) {
            position = intent.getIntExtra("position", 0);
            titleTv.setText(brandTitle[position]);
        } else if (type == 2) {
            position = intent.getIntExtra("position", 0);
            titleTv.setText(chainTitle[position]);
        }
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

                handler = new Handler(new Handler.Callback() {
                    String url;

                    @Override
                    public boolean handleMessage(Message msg) {

                        if (msg.what == 0) {
                            int page = 1;
                            page++;
//
//                            if (sortUrl == "http://food.boohee.com/fb/v1/foods?kind=group&value=1&order_by=1&page=1&" +
//                                    "order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_versio" +
//                                    "n=5.1&phone_model=M578CA&channel=meizu%20") {
//                                url = "http://food.boohee.com/fb/v1/foods?kind=group&value=1&order_by=1&page=" + page + "&" +
//                                        "order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_versio" +
//                                        "n=5.1&phone_model=M578CA&channel=meizu%20";
//
//                            } else if (sortUrl == "http://food.boohee.com/fb/v1/foods?kind=" +
//                                    "brand&value=20&order_by=1&page=1&" +
//                                    "order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_versio" +
//                                    "n=5.1&phone_model=M578CA&channel=meizu%20") {
//                                url = "http://food.boohee.com/fb/v1/foods?kind=" +
//                                        "brand&value=1&order_by=1&page=" + page + "&" +
//                                        "order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_versio" +
//                                        "n=5.1&phone_model=M578CA&channel=meizu%20";
//
//                            } else if (sortUrl == "http://food.boohee.com/fb/v1/foods?kind=" +
//                                    "restaurant&value=1&order_by=1&page=1&" +
//                                    "order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_versio" +
//                                    "n=5.1&phone_model=M578CA&channel=meizu%20") {
//                                url = "http://food.boohee.com/fb/v1/foods?kind=" +
//                                        "restaurant&value=1&order_by=1&page=" + page + "&" +
//                                        "order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_versio" +
//                                        "n=5.1&phone_model=M578CA&channel=meizu%20";
//
//                            }
                            NetTool.getInstance().startRequest(sortUrl, FoodWiKiBrandBean.class, new CallBack<FoodWiKiBrandBean>() {
                                @Override
                                public void onSuccess(FoodWiKiBrandBean response) {
                                    foodsBeen.addAll(response.getFoods());
                                    adapter.setFoodsBeanList(foodsBeen);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });

                            listView.onRefreshComplete();
                            Toast.makeText(getBaseContext(), "加载完成", Toast.LENGTH_SHORT).show();
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
    protected void bindEvent() {
        titleTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_foodwikibrand_title:
                finish();
                break;
        }
    }
}