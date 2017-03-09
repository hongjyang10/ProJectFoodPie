package com.lanou.dllo.myfoodpie.mainfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.activity.PublicActivity;
import com.lanou.dllo.myfoodpie.adapter.MyselfAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MyselfFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private List<Map<String, Object>> list;
    private MyselfAdapter adapter;
    private TextView loginTv;
    private ImageView setIv;
    private Intent intent;
    private static final String TAG = "MyselfFragment";
    private ImageView headImg;
    private RefreshReceiver receiver;

    @Override
    protected int setLayout() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.lv_myself);
        loginTv = bindView(R.id.tv_myself_login);
        setIv = bindView(R.id.iv_myself_set);
        headImg = bindView(R.id.iv_my_head);
        receiver = new RefreshReceiver();
        IntentFilter filter = new IntentFilter("START");
        getContext().registerReceiver(receiver, filter);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        HashMap<String, Object> myPhoto = new HashMap<>();
        myPhoto.put("photo", R.mipmap.ic_my_photo);
        myPhoto.put("text", "我的照片");
        list.add(myPhoto);
        HashMap<String, Object> myCollect = new HashMap<>();
        myCollect.put("photo", R.mipmap.ic_my_collect);
        myCollect.put("text", "我的收藏");
        list.add(myCollect);
        HashMap<String, Object> upload = new HashMap<>();
        upload.put("photo", R.mipmap.ic_my_upload);
        upload.put("text", "上传食物数据");
        list.add(upload);
        HashMap<String, Object> order = new HashMap<>();
        order.put("photo", R.mipmap.ic_my_order);
        order.put("text", "我的订单");
        list.add(order);
        adapter = new MyselfAdapter(getActivity());
        adapter.setList(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        SharedPreferences sp = getContext().getSharedPreferences("register", Context.MODE_PRIVATE);
        String headurl = sp.getString("headurl", null);
        String nickname = sp.getString("nickname", null);


        if(register()){
            loginTv.setText(nickname);
            Picasso.with(getContext()).load(headurl).into(headImg);
        }else{
            loginTv.setText("点击登录");
            headImg.setImageResource(R.mipmap.ic_analyse_default);
        }

    }

    @Override
    protected void initEvent() {
        loginTv.setOnClickListener(this);
        setIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_myself_login:
                intent = new Intent(getActivity(), PublicActivity.class);
                intent.putExtra("xml", R.layout.activity_login);
                startActivity(intent);

                break;
            case R.id.iv_myself_set:
                if (register()) {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activity_set);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activity_login);
                    startActivity(intent);
                }

                break;
        }
    }

    private boolean register() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("register", Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean("register", false);
    }

    class RefreshReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "onReceive: ");
            initData();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                if (register()) {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activitv_photo);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activity_login);
                    startActivity(intent);
                }
                break;
            case 1:
                if (register()) {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activitv_collect);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activity_login);
                    startActivity(intent);
                }
                break;
            case 2:
                if (register()) {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activitv_upload);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activity_login);
                    startActivity(intent);
                }
                break;
            case 3:
                if (register()) {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activitv_order);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), PublicActivity.class);
                    intent.putExtra("xml", R.layout.activity_login);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getContext().unregisterReceiver(receiver);
    }
}
