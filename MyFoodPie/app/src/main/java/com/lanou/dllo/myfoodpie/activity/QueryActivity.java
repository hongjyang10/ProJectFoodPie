package com.lanou.dllo.myfoodpie.activity;

import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.queryfragment.QueryFragment;
import com.lanou.dllo.myfoodpie.queryfragment.ShowFragment;

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
    private FragmentTransaction fragmentTransaction;
    private EditText queryEt;
    private String text;
    private QueryFragment queryFragment = new QueryFragment();
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
    }

    @Override
    protected void initData() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_query, queryFragment);
        fragmentTransaction.commit();
        text = queryEt.getText().toString();
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
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.iv_query_activity:
                text = queryEt.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(this, "搜索不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    fragmentTransaction.replace(R.id.fl_query, new ShowFragment());
                    fragmentTransaction.commit();


                }
                break;
            case R.id.iv_delete_activity:

                fragmentTransaction.replace(R.id.fl_query, queryFragment);
                queryEt.setText("");
                fragmentTransaction.commit();
                break;
        }
    }
}
