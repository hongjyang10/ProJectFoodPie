package com.lanou.dllo.myfoodpie.roameatfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
    private String url;
    private ListView listView;
    private KnowRoamEatAdapter adapter;
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
        adapter = new KnowRoamEatAdapter(getContext());
        listView.setAdapter(adapter);
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
