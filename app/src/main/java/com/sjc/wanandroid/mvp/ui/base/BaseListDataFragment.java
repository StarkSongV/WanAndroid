package com.sjc.wanandroid.mvp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.adapter.CommonRVAdapter;
import com.sjc.wanandroid.mvp.contract.common.IBaseListDataView;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.contract.common.IBaseView;
import com.sjc.wanandroid.view.LMRecyclerView;
import com.sjc.wanandroid.weight.ContainerLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjc on 2018/7/9 15:25
 */

public abstract class BaseListDataFragment<V extends IBaseView, P extends IBasePresenter<V>, T> extends BaseFragment
        implements IBaseListDataView<T>, LMRecyclerView.OnFooterAutoLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener, ContainerLayout.OnClickReLoadListener {

    private SwipeRefreshLayout mRefresh;
    private ContainerLayout mContainer;
    private LMRecyclerView mRecyclerview;
    private boolean isFristLoad = true;//是否是第一次加载
    private boolean isVisible = false; //是否可见
    private boolean isPreLoad = false;//是否已经预加载完
    public List<T> listData = new ArrayList<>();
    private int page = 0;
    private boolean isFirstLoad = true;
    private boolean isAutoLoadMore = true;
    private CommonRVAdapter<T> rvAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_listdata;
    }

    @Override
    protected void initViews(View inflate) {
        mRefresh = inflate.findViewById(R.id.basefragment_refresh);
        mContainer = inflate.findViewById(R.id.basefragment_container);
        mRecyclerview = inflate.findViewById(R.id.basefragment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(layoutManager);

        rvAdapter = getRVAdapter();
        mRecyclerview.setAdapter(rvAdapter);
    }

    protected abstract CommonRVAdapter<T> getRVAdapter();


    @Override
    protected void initAction(View inflate) {
        mRecyclerview.addHeaderView(initHeaderView());
        mRecyclerview.setCanLoadMore(isCanLoadMore());
        mRecyclerview.addFooterAutoLoadMoreListener(this);
        mRefresh.setOnRefreshListener(this);
        mContainer.setOnClickReLoadListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isLazyLoad()) {
            isPreLoad = true;
            lazyLoad();
        } else {
            loadData();
        }
    }


    private void lazyLoad() {
        if (!isFristLoad || !isVisible || !isPreLoad) {
            return;
        }
        loadData();
        isFristLoad = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isLazyLoad()) return;

        if (isVisibleToUser) {
            lazyLoad();
        }
        isVisible = isVisibleToUser;
    }

    //是否执行懒加载(页面可见的时候才开始加载)
    public boolean isLazyLoad() {
        return false;
    }

    @Override
    public List<T> getData() {
        return listData;
    }

    @Override
    public void clearAllList() {
        listData.clear();
    }


    protected abstract View initHeaderView();

    protected abstract boolean isCanLoadMore();

    protected abstract void loadData();

    @Override
    public void firstLoadData() {
        isFirstLoad = true;
        mContainer.showLoading();
        page = 0;
        isAutoLoadMore = true;
        loadData();
    }

    @Override
    public void showContent() {
        mContainer.showContent();
        if (rvAdapter != null) rvAdapter.notifyAllDataSetChanged(mRecyclerview);
    }

    @Override
    public void showNodata() {
        mContainer.showNodata();
    }


    @Override
    public void loadMore() {
        if (!isAutoLoadMore) return;
        loadData();
    }

    //设置加载更多的参数
    @Override
    public void setLoadMoreConfig() {
        page++;
        isAutoLoadMore = true;
        mRecyclerview.showLoadMore();
    }

    //LMRecyclerView 底部点击重新加载
    @Override
    public void reLoadMore() {
        isAutoLoadMore = true;
        mRecyclerview.showLoadMore();
        loadData();
    }

    //刷新
    @Override
    public void onRefresh() {
        isAutoLoadMore = true;
        page = 0;
        loadData();
    }

    @Override
    public void closeLoading() {
        super.closeLoading();
        serEnableRefresh(false);
    }

    //ContainerLayout点击重新加载
    @Override
    public void onReLoadClick(ContainerLayout layout) {
        firstLoadData();
    }

    public void serEnableRefresh(boolean isEnableRefresh) {
        mRefresh.setRefreshing(isEnableRefresh);
    }

    public void setEnableLoadMore(boolean isEnableLoadMore) {
        mRecyclerview.setCanLoadMore(isEnableLoadMore);
    }

    @Override
    public void showError(String error) {
        super.showError(error);
        serEnableRefresh(false);
    }

    @Override
    public void showNoMore() {
        isAutoLoadMore = false;
        mRecyclerview.showNoMoreData();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void isLoadMoreError(boolean isLoadMoreError) {
        isAutoLoadMore = false;
        if (isLoadMoreError) {
            mRecyclerview.showLoadMoreError();
        } else {
            mContainer.showError();
        }
    }
}
