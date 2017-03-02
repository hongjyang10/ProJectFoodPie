package com.lanou.dllo.myfoodpie.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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
    private ListView listView;
    private Intent intent;
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
        adapter = new FoodWiKiBrandAdapter(getBaseContext());
        listView.setAdapter(adapter);
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