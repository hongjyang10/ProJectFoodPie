package com.lanou.dllo.myfoodpie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.bean.FoodWiKiBrandBean;
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
public class FoodWiKiBrandAdapter extends BaseAdapter {
    private Context context;
    private List<FoodWiKiBrandBean.FoodsBean> foodsBeanList;

    public FoodWiKiBrandAdapter(Context context) {
        this.context = context;
    }

    public void setFoodsBeanList(List<FoodWiKiBrandBean.FoodsBean> foodsBeanList) {
        this.foodsBeanList = foodsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return foodsBeanList != null ? foodsBeanList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return foodsBeanList != null ? foodsBeanList.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = null;
        holder = BaseViewHolder.createListViewHolder(convertView, parent, R.layout.item_foodwikibrand);
        holder.setText(R.id.tv_item_food_brand, foodsBeanList.get(position).getName());
        holder.setImage(R.id.cir_item_food_brand, foodsBeanList.get(position).getThumb_image_url());
        holder.setText(R.id.tv_item_food_number,foodsBeanList.get(position).getCalory());
        holder.setText(R.id.tv_item_food_hundred,foodsBeanList.get(position).getWeight());
        if (Integer.parseInt(foodsBeanList.get(position).getCalory())<368){
            holder.setImage(R.id.iv_item_food_brand,R.mipmap.ic_food_light_green);
        }else  if (Integer.parseInt(foodsBeanList.get(position).getCalory())<450){
            holder.setImage(R.id.iv_item_food_brand,R.mipmap.ic_food_light_yellow);
        }else  if (Integer.parseInt(foodsBeanList.get(position).getCalory())>450){
            holder.setImage(R.id.iv_item_food_brand,R.mipmap.ic_food_light_red);
        }
        return holder.getmView();
    }

}
