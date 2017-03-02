package com.lanou.dllo.myfoodpie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.bean.EvaRoamEatBean;
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
public class EvaRoamEatAdapter extends BaseAdapter {
    private Context context;
    private List<EvaRoamEatBean.FeedsBean> evaBean;

    public EvaRoamEatAdapter(Context context) {
        this.context = context;
    }

    public void setEvaBean(List<EvaRoamEatBean.FeedsBean> evaBean) {
        this.evaBean = evaBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return evaBean != null ? evaBean.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return evaBean != null ? evaBean.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = BaseViewHolder.createListViewHolder(convertView, parent, R.layout.item_fragment_roameat_evaluation);
        holder.setImage(R.id.iv_item_eva_photo, evaBean.get(position).getBackground());
        holder.setText(R.id.tv_item_eva_title, evaBean.get(position).getSource());
        holder.setText(R.id.tv_item_eva_text, evaBean.get(position).getTitle());
        holder.setText(R.id.tv_item_eva_number, evaBean.get(position).getTail());
        return holder.getmView();
    }
}