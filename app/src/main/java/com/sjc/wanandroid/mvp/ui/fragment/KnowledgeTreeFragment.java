package com.sjc.wanandroid.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.ui.base.BaseFragment;

/**
 * 知识体系
 * Created by sjc on 2018/6/19 13:39
 */

public class KnowledgeTreeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_tree;
    }

    @Override
    protected void initViews(View inflate) {

    }

    @Override
    protected void initAction(View inflate) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
    private String TAG = "sjc--------     "+getClass().getName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void getBundle(Bundle bundle) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "isVisibleToUser    " + isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.i(TAG, "hidden    " + hidden);
        super.onHiddenChanged(hidden);

    }
}
