package com.lanou.dllo.myfoodpie.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.adapter.FoodWiKiQueryAdapter;
import com.lanou.dllo.myfoodpie.adapter.QueryListViewAdapter;
import com.lanou.dllo.myfoodpie.bean.FoodWiKiQueryBean;
import com.lanou.dllo.myfoodpie.datapotting.DaoMaster;
import com.lanou.dllo.myfoodpie.datapotting.DaoSession;
import com.lanou.dllo.myfoodpie.datapotting.QueryData;
import com.lanou.dllo.myfoodpie.datapotting.QueryDataDao;
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
public class QueryActivity extends BaseActivity implements View.OnClickListener {
    private ImageView queryBackIv, queryIv, deleteIv;
    private EditText queryEt;
    private String text = "";
    private List<String> strings;
    private RecyclerView queryRv;
    private FoodWiKiQueryAdapter adapter;
    private ListView queryLv;
    private QueryListViewAdapter queryAdapter;
    private QueryDataDao dataDao;
    private static final String TAG = "QueryActivity";

    @Override
    protected int setLayout() {
        return R.layout.activity_query;
    }

    @Override
    protected void initView() {
        queryBackIv = bindView(R.id.iv_query_back);
        queryIv = bindView(R.id.iv_query_activity);
        queryEt = bindView(R.id.et_query);
        deleteIv = bindView(R.id.iv_delete_activity);
        queryRv = bindView(R.id.rv_query);
        queryLv = bindView(R.id.lv_query);
    }

    @Override
    protected void initData() {
        String url = "http://food.boohee.com/fb/v1/keywords?token=pxN9j6S1za8PGQzefHxh&user_key=e88bf69a-92d5-4dd4-89af-69aef89dc639&\" +\n" +
                "            \"app_version=2.6&app_device=Android&os_version=6.0.1&phone_model=MI+NOTE+LTE&channel=xiaomi";
        adapter = new FoodWiKiQueryAdapter(getBaseContext());
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
        queryAdapter = new QueryListViewAdapter(getBaseContext());
        queryLv.setAdapter(queryAdapter);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getBaseContext(), "query.db", null);
        DaoMaster master = new DaoMaster(helper.getWritableDb());
        DaoSession session = master.newSession();
        dataDao = session.getQueryDataDao();


    }

    @Override
    protected void bindEvent() {
        queryBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        queryIv.setOnClickListener(this);
        deleteIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        text = queryEt.getText().toString();
        switch (v.getId()) {
            case R.id.iv_query_activity:
                if (TextUtils.isEmpty(text)){
                    Toast.makeText(this, "搜索不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(this, ShowActivity.class);
                    intent.putExtra("texts",text);
                    startActivity(intent);

                    QueryData data = new QueryData(null,text);
                    dataDao.insert(data);
                    Log.e(TAG, "onClick: "+data.getText() );

                    List<QueryData> list = dataDao.queryBuilder().where(QueryDataDao.Properties.Text.eq(text)).build().list();
                    Log.e(TAG, "onClick: " +(list==null));
                    queryAdapter.setText(list);

                }
                break;
        }
    }
}
