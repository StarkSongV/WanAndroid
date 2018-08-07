package com.sjc.wanandroid.mvp.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.sjc.wanandroid.adapter.CommonRVAdapter;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.ui.base.BaseListDataFragment;

import java.util.List;

/**
 * Created by sjc on 2018/7/17 10:17
 */

public class TreeFragment extends BaseListDataFragment {
    public static TreeFragment newInstance(String cid){
        TreeFragment treeFragment = new TreeFragment();
        Bundle bundle = new Bundle();
        treeFragment.setArguments(bundle);
        return treeFragment;
    }
    @Override
    public void setData(List data) {

    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected CommonRVAdapter getRVAdapter() {
        return null;
    }

    @Override
    protected View initHeaderView() {
        return null;
    }

    @Override
    protected boolean isCanLoadMore() {
        return false;
    }

    @Override
    protected void loadData() {

    }
}
