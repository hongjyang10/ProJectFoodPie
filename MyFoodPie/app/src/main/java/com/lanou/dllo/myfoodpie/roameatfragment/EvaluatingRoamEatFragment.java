package com.lanou.dllo.myfoodpie.roameatfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.activity.WebViewActivity;
import com.lanou.dllo.myfoodpie.adapter.EvaRoamEatAdapter;
import com.lanou.dllo.myfoodpie.bean.EvaRoamEatBean;
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
public class EvaluatingRoamEatFragment extends BaseFragment {
    private String url = Url.ROAMEATEVA;
    private ListView listView;
    private EvaRoamEatAdapter adapter;
    private List<EvaRoamEatBean.FeedsBean> evaBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_roameat_evaluating;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.lv_rm_evaluating);
    }

    @Override
    protected void initData() {
        adapter = new EvaRoamEatAdapter(getActivity());
        listView.setAdapter(adapter);
        NetTool.getInstance().startRequest(url, EvaRoamEatBean.class, new CallBack<EvaRoamEatBean>() {
            @Override
            public void onSuccess(EvaRoamEatBean response) {
                evaBean = response.getFeeds();
                adapter.setEvaBean(evaBean);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    protected void initEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", evaBean.get(position).getLink());
                intent.putExtra("fragmentXml",R.layout.activity_web);
                startActivity(intent);
            }
        });
    }
}
