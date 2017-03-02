package com.lanou.dllo.myfoodpie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.potting.BaseViewHolder;

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
public class MyselfAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list;

    public MyselfAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list != null ? list.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = BaseViewHolder.createListViewHolder(convertView, parent, R.layout.item_fragment_myself);
        Map<String, Object> map = list.get(position);
        int imgs = (int) map.get("photo");
        String text = (String)map.get("text");
        holder.setImage(R.id.iv_item_myself_head, imgs);
        holder.setText(R.id.tv_item_myself_text, text);
        return holder.getmView();
    }
}