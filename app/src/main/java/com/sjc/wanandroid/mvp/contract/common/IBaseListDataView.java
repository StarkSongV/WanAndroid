package com.sjc.wanandroid.mvp.contract.common;

import java.util.List;

/**
 * Created by sjc on 2018/4/9 17:43
 */

public interface IBaseListDataView<T> extends IBaseView {
    void showContent();//显示内容

    void showNodata();//显示没有数据

    void firstLoadData();//显示第一次加载

    void showNoMore();//显示没有更多数据

    void setData(List<T> data); //设置数据

    List<T> getData();//获取数据

    void clearAllList();//清空列表

    void setLoadMoreConfig();//设置加载更多配置

    int getPage();//获取页码

    void isLoadMoreError(boolean isLoadMoreError);//是否是加载更多的时候加载错误

}
