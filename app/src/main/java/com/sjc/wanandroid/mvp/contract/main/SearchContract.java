package com.sjc.wanandroid.mvp.contract.main;

import com.sjc.wanandroid.mvp.contract.common.IBaseListDataView;
import com.sjc.wanandroid.mvp.contract.common.IBaseModel;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.contract.common.IBaseView;
import com.sjc.wanandroid.mvp.model.bean.ArticleBean;
import com.sjc.wanandroid.mvp.model.bean.FriendBean;
import com.sjc.wanandroid.net.callback.RxObserver;
import com.sjc.wanandroid.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by sjc on 2018/6/4 17:22
 */

public interface SearchContract {
    interface View extends IBaseListDataView<ArticleBean> {
        void hotKeyList(List<String> hotKeyList);
        void friendList(List<FriendBean> friendList);
    }
    interface Model extends IBaseModel {
        /**
         * 获取网络热词
         * @param rxObserver
         */
        void getHotKey(RxObserver<List<String>> rxObserver);

        /**
         * 常用网站
         * @param rxObserver
         */
        void getFriend(RxObserver<List<FriendBean>> rxObserver);

        /**
         * 获取搜索出的数据
         * @param page 页码
         * @param keyword 关键字
         * @param rxObserver 订阅者
         */
        void getSearchContent(int page,String keyword, RxPageListObserver<ArticleBean> rxObserver);
    }
    abstract class Presenter extends IBasePresenter<View> {
        /**
         * 获取网络热词
         */
       public abstract void getHotKey();

        /**
         * 常用网站
         */
        public abstract void getFriend();
        /**
         * 获取搜索出的数据
         */
        public abstract void getSearchContent(int page,String keyword);
    }
}
