package com.lanou.dllo.myfoodpie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.datapotting.QueryData;
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
public class QueryListViewAdapter extends BaseAdapter {
    private static final String TAG = "QueryListViewAdapter";
    private Context context;
    private List<QueryData> text;

    public QueryListViewAdapter(Context context) {
        this.context = context;
    }

    public void setText(List<QueryData> text) {
        this.text = text;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(text!=null){

            Log.e(TAG, "getCount: "+text.size());
        }
        return text != null ? text.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return text != null ? text.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = BaseViewHolder.createListViewHolder(convertView, parent, R.layout.item_fragment_query);
        String str = text.get(position).getText();
        Log.e(TAG, "getView: "+str);
        holder.setText(R.id.tv_item_query, str);
        return holder.getmView();
    }
}
