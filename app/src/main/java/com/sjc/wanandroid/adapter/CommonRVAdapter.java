package com.sjc.wanandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sjc.wanandroid.adapter.viewholder.CommonRVViewHolder;
import com.sjc.wanandroid.view.LMRecyclerView;

import java.util.List;

/**
 * Created by sjc on 2018/7/4 16:06
 */

public abstract class CommonRVAdapter<T> extends RecyclerView.Adapter<CommonRVViewHolder> {
    private List<T> list;

    public CommonRVAdapter(List<T> list) {
        this.list = list;
    }

    public void notifyAllDataSetChanged(LMRecyclerView lmRecyclerView) {
        lmRecyclerView.notifyDataSetChanged();
    }

    public void notifyItemChanged(int position,LMRecyclerView lmRecyclerView) {
        lmRecyclerView.notifyItemChanged(position);

    }
    public void notifyItemRemovedData(int position,LMRecyclerView lmRecyclerView) {
        lmRecyclerView.notifyItemRemoved(position);
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public CommonRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonRVViewHolder.createViewHolder(parent,getLayoutId(viewType));
    }

    protected abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(CommonRVViewHolder holder, int position) {
        T bean = list.get(position);
        bindData(holder,bean,position);
    }

    protected abstract void bindData(CommonRVViewHolder holder, T bean, int position);

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
