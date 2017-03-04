package com.lanou.dllo.myfoodpie.queryfragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.FoodWiKiQueryAdapter;
import com.lanou.dllo.myfoodpie.adapter.QueryListViewAdapter;
import com.lanou.dllo.myfoodpie.bean.FoodWiKiQueryBean;
import com.lanou.dllo.myfoodpie.datapotting.QueryData;
import com.lanou.dllo.myfoodpie.datapotting.QueryDataDao;
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
public class QueryFragment extends BaseFragment implements View.OnClickListener {
    private FoodWiKiQueryAdapter adapter;
    private List<String> strings;
    private RecyclerView queryRv;
    private ListView queryLv;
    private QueryListViewAdapter queryAdapter;
    private TextView deleteTv;
    private QueryData data;
    private QueryDataDao dataDao;
    private static final String TAG = "QueryFragment";

    @Override
    protected int setLayout() {
        return R.layout.fragment_query;
    }

    @Override
    protected void initView() {
        queryRv = bindView(R.id.rv_query);
        queryLv = bindView(R.id.lv_query);
        deleteTv = bindView(R.id.tv_delete_activity);
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

//        String text = getArguments().getString("text").toString();
//        Log.e(TAG, "initData: " +text );
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "query.db", null);
//        DaoMaster master = new DaoMaster(helper.getWritableDb());
//        DaoSession session = master.newSession();
//        dataDao = session.getQueryDataDao();

//        queryAdapter = new QueryListViewAdapter(getContext());
//
//        queryLv.setAdapter(queryAdapter);
//        List<QueryData> list = dataDao.loadAll();


//        for (QueryData queryData : list) {
//            String a=queryData.getText().toString();
//            Log.e(TAG, "onClick: " + a);
//            datas.add(a);
//        }
//
//        adapter.setText(a);
//        data = new QueryData(null, text);
//        dataDao.insert(data);
    }

    @Override
    protected void initEvent() {
        deleteTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_delete_activity:
//               queryDataDao.deleteAll();
//                break;
        }
    }
}
