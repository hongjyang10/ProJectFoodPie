package com.lanou.dllo.myfoodpie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.activity.RoamEaTHomeActivity;
import com.lanou.dllo.myfoodpie.activity.WebViewActivity;
import com.lanou.dllo.myfoodpie.bean.HomeRoamEatBean;
import com.lanou.dllo.myfoodpie.potting.BaseViewHolder;

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
public class HomeRoamEatAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "HomeRoamEatAdapter";
    private Context context;
    private final int FIRST_VIEW = 6;
    private final int SECOND_VIEW = 5;
    private List<HomeRoamEatBean.FeedsBean> homeBean;

    public HomeRoamEatAdapter(Context context) {
        this.context = context;
    }

    public void setHomeBean(List<HomeRoamEatBean.FeedsBean> homeBean) {
        this.homeBean = homeBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return homeBean.get(position).getContent_type();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;
        switch (viewType) {
            case FIRST_VIEW:
                holder = BaseViewHolder.createViewHolder(context, parent, R.layout.item_fragment_roameat_home_head);
                break;
            case SECOND_VIEW:
                holder = BaseViewHolder.createViewHolder(context, parent, R.layout.item_fragment_roameat_home);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case FIRST_VIEW:
                holder.setImage(R.id.iv_item_home_head_end, homeBean.get(position).getCard_image());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebViewActivity.class);
                        String url = homeBean.get(position).getLink();
                        intent.putExtra("url", url);
                        intent.putExtra("fragmentXml",R.layout.activity_home_web);
                        context.startActivity(intent);
                    }
                });
                break;
            case SECOND_VIEW:
                if (TextUtils.isEmpty(homeBean.get(position).getDescription())) {
                    holder.hideView(R.id.tv_item_home_text);
                }
                holder.setImage(R.id.iv_item_home_photo, homeBean.get(position).getCard_image());
                holder.setText(R.id.tv_item_home_title, homeBean.get(position).getTitle());
                holder.setText(R.id.tv_item_home_text, homeBean.get(position).getDescription());
                holder.setImage(R.id.iv_item_home_head, homeBean.get(position).getPublisher_avatar());
                holder.setText(R.id.tv_item_home_name, homeBean.get(position).getPublisher());
                holder.setText(R.id.tv_item_home_number, homeBean.get(position).getLike_ct() + "");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, RoamEaTHomeActivity.class);
                        String url = homeBean.get(position).getCard_image();
                        intent.putExtra("imgs", url);
                        intent.putExtra("photo",homeBean.get(position).getPublisher_avatar());
                        intent.putExtra("name",homeBean.get(position).getPublisher());
                        intent.putExtra("number",homeBean.get(position).getLike_ct()+ "");
                        context.startActivity(intent);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return homeBean != null ? homeBean.size() : 0;
    }

}
