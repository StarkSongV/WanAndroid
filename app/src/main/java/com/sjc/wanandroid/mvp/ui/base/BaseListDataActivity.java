package com.sjc.wanandroid.mvp.ui.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.adapter.CommonRVAdapter;
import com.sjc.wanandroid.mvp.contract.common.IBaseListDataView;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.contract.common.IBaseView;
import com.sjc.wanandroid.view.LMRecyclerView;
import com.sjc.wanandroid.weight.ContainerLayout;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListDataActivity<V extends IBaseView, P extends IBasePresenter<V>, T> extends BaseActivity<V, P>
        implements IBaseListDataView<T>, LMRecyclerView.OnFooterAutoLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener, ContainerLayout.OnClickReLoadListener {

    protected SwipeRefreshLayout mRefresh;
    protected ContainerLayout mContainer;
    protected LMRecyclerView mRecyclerview;
    protected FrameLayout mFlContent;
    public List<T> listData = new ArrayList<>();
    protected int page = 0;
    protected CommonRVAdapter<T> rvAdapter;
    private boolean isFirstLoad = true;
    private boolean isAutoLoadMore = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_list_data;
    }

    @Override
    protected void initView() {
        mFlContent = (FrameLayout) findViewById(R.id.fl_baselist_content);
        mRefresh = findViewById(R.id.baseactivity_refresh);
        mContainer = findViewById(R.id.baseactivity_container);
        mRecyclerview = findViewById(R.id.baseactivity_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(layoutManager);
        rvAdapter = getRVAdapter();
        mRecyclerview.setAdapter(rvAdapter);
    }

    protected abstract CommonRVAdapter<T> getRVAdapter();

    @Override
    protected void initAction() {
        mRecyclerview.addHeaderView(initHeaderView());
        mRecyclerview.setCanLoadMore(isCanLoadMore());
        mRecyclerview.addFooterAutoLoadMoreListener(this);
        mContainer.setOnClickReLoadListener(this);
        mRefresh.setOnRefreshListener(this);
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
