package com.lanou.dllo.myfoodpie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.bean.KownRoamEatBean;
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
public class KnowRoamEatAdapter extends BaseAdapter {
    private Context context;
    private final int FIRST_VIEW = 1;
    private final int SECOND_VIEW = 2;
    private List<KownRoamEatBean.FeedsBean> kownBean;

    public KnowRoamEatAdapter(Context context) {
        this.context = context;
    }

    public void setKownBean(List<KownRoamEatBean.FeedsBean> kownBean) {
        this.kownBean = kownBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return kownBean != null ? kownBean.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return kownBean != null ? kownBean.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
       return kownBean.get(position).getContent_type();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = null;
        switch (getItemViewType(position)) {
            case FIRST_VIEW:
                holder = BaseViewHolder.createViewHolder(context, parent, R.layout.item_fragment_roameat_know);
                holder.setText(R.id.tv_item_know_title, kownBean.get(position).getTitle());
                holder.setText(R.id.tv_item_know_author, kownBean.get(position).getSource());
                holder.setText(R.id.tv_item_know_number, kownBean.get(position).getTail());
                holder.setImage(R.id.iv_item_konw_photo, kownBean.get(position).getImages().get(0));
                break;
            case SECOND_VIEW:
                holder = BaseViewHolder.createViewHolder(context, parent, R.layout.item_fragment_roameat_know_disparity);
                holder.setText(R.id.tv_item_know_dis_title, kownBean.get(position).getTitle());
                holder.setImage(R.id.iv_know_dis_photo_one, kownBean.get(position).getImages().get(0));
                holder.setImage(R.id.iv_know_dis_photo_two, kownBean.get(position).getImages().get(1));
                holder.setImage(R.id.iv_know_dis_photo_three, kownBean.get(position).getImages().get(2));
                holder.setText(R.id.tv_know_dis_author, kownBean.get(position).getSource());
                holder.setText(R.id.tv_know_dis_number, kownBean.get(position).getTail());
                break;
        }
        return holder.getmView();
    }
}
