package com.lanou.dllo.myfoodpie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lanou.dllo.myfoodpie.R;
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
public class FoodWiKiQueryAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private List<String> strings;


    public FoodWiKiQueryAdapter(Context context) {
        this.context = context;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder(context, parent, R.layout.item_activity_rv_query);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_item_rv_query, strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings != null ? strings.size() : 0;
    }
}
