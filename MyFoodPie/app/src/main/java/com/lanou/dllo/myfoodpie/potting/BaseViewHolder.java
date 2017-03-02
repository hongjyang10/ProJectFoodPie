package com.lanou.dllo.myfoodpie.potting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View mView;
    private SparseArray<View> sparseArray;
    private Context mContext;

    public View getmView() {
        return mView;
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(View itemView, Context context) {
        super(itemView);
        mView = itemView;
        sparseArray = new SparseArray<>();
        mContext = context;
    }

    public static BaseViewHolder createViewHolder(Context context, ViewGroup group, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, group, false);
        BaseViewHolder holder = new BaseViewHolder(itemView, context);
        return holder;
    }

    public static BaseViewHolder createListViewHolder(View view, ViewGroup group, int layoutId) {
        BaseViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(group.getContext()).inflate(layoutId, group, false);
            holder = new BaseViewHolder(view, group.getContext());
            view.setTag(holder);
        } else {
            holder = (BaseViewHolder) view.getTag();
        }
        return holder;
    }

    public <T extends View> T getView(int id) {
        View view = sparseArray.get(id);
        if (view == null) {
            view = mView.findViewById(id);
            sparseArray.put(id, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int id, String s) {
        TextView textView = getView(id);
        if (s != null) {
            textView.setText(s);
        }
        return this;
    }

    public BaseViewHolder setImage(int id, String url) {
        ImageView imageView = getView(id);
        if (url != null) {
            Picasso.with(mContext).load(url).into(imageView);
        }
        return this;
    }

    public BaseViewHolder setImage(int id, int resource) {
        ImageView imageView = getView(id);
        if (resource != 0) {
            imageView.setImageResource(resource);
        }
        return this;
    }

    public BaseViewHolder hideView(int id){
        TextView textView = getView(id);
        if(textView != null){
            textView.setVisibility(View.GONE);
        }
        return this;
    }

//    public BaseViewHolder setRecyclerView(int id, FoodWiKiBean.GroupBean groupBean) {
//        RecyclerView recyclerView = getView(id);
//        if(recyclerView != null){
//            FoodWiKiAdapter adapter = new FoodWiKiAdapter(mContext);
//            adapter.setGroupBean(groupBean);
//            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3, LinearLayoutManager.VERTICAL, false));
//            recyclerView.setAdapter(adapter);
//        }
//        return this;
//    }
}
