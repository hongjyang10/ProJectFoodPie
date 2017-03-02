package com.lanou.dllo.myfoodpie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lanou.dllo.myfoodpie.R;
import com.lanou.dllo.myfoodpie.activity.FoodWiKiBrandActivity;
import com.lanou.dllo.myfoodpie.bean.FoodWiKiBean;
import com.lanou.dllo.myfoodpie.potting.BaseViewHolder;

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
public class FoodWiKiAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private FoodWiKiBean.GroupBean groupBean;
    private int type;
    private String sortType[] = {"group", "brand", "restaurant"};
    private Intent intent;

    public void setType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }

    public FoodWiKiAdapter(Context context) {
        this.context = context;
    }

    public void setGroupBean(FoodWiKiBean.GroupBean groupBean) {
        this.groupBean = groupBean;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;

        holder = BaseViewHolder.createViewHolder(context, parent, R.layout.item_fragment_foodwiki_rv);

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {

        holder.setText(R.id.tv_item_foodwiki, groupBean.getCategories().get(position).getName());
        holder.setImage(R.id.iv_item_foodwiki, groupBean.getCategories().get(position).getImage_url());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(context, FoodWiKiBrandActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("type",type);
                int value = 0;
                if(type == 1){
                    if(position == 0){
                        value = 20;
                    }else if(position < 11){
                        value = position;
                    }else if(position == 11){
                        value = 21;
                    }else{
                        value = 23;
                    }
                }else {
                    value = position+1;
                }
                String url = "http://food.boohee.com/fb/v1/foods?kind=" +
                        "" + sortType[type] + "&value=" + value + "&order_by=1&page=1&" +
                        "order_asc=0&token=&user_key=&app_version=2.6&app_device=Android&os_versio" +
                        "n=5.1&phone_model=M578CA&channel=meizu%20";
                intent.putExtra("sort", url);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return groupBean == null ? 0 : groupBean.getCategories().size();
    }

}
