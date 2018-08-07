package com.sjc.wanandroid.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sjc on 2018/7/4 16:07
 */

public class CommonRVViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> sparseArray;
    private View mItemView;
    private CommonRVViewHolder(View mItemView) {
        super(mItemView);
        this.mItemView = mItemView;
        if (sparseArray==null) {
            sparseArray = new SparseArray<>();
        }
    }
    public static CommonRVViewHolder createViewHolder(ViewGroup parent,int layoutId){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new CommonRVViewHolder(inflate);
    }
    public static CommonRVViewHolder createViewHolder(View view){
        return new CommonRVViewHolder(view);
    }
    public <T extends View> T getView(int id){
        View view = sparseArray.get(id);
        if (view==null) {
            view = mItemView.findViewById(id);
            sparseArray.put(id,view);
        }
        return (T) view;
    }

}
