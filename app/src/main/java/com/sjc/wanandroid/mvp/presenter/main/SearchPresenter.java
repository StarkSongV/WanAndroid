package com.sjc.wanandroid.mvp.presenter.main;

import com.sjc.wanandroid.mvp.contract.main.SearchContract;
import com.sjc.wanandroid.mvp.model.bean.ArticleBean;
import com.sjc.wanandroid.mvp.model.bean.FriendBean;
import com.sjc.wanandroid.mvp.model.bean.PageDataBean;
import com.sjc.wanandroid.mvp.model.main.SearchModel;
import com.sjc.wanandroid.net.callback.RxObserver;
import com.sjc.wanandroid.net.callback.RxPageListObserver;

import java.util.List;

/**
 * Created by sjc on 2018/6/28 15:10
 */

public class SearchPresenter extends SearchContract.Presenter {
    private SearchContract.Model model;
    public SearchPresenter() {
        model = new SearchModel();
    }

    @Override
    public void getHotKey() {
        RxObserver<List<String>> rxObserver = new RxObserver<List<String>>(this) {
            @Override
            protected void onSuccess(List<String> data) {
                if (obtainView()!=null) {
                    obtainView().hotKeyList(data);
                }
            }

            @Override
            public void onFaile(String errorMsg) {
                if (obtainView()!=null) {
                    obtainView().showError(errorMsg);
                }
            }
        };
        model.getHotKey(rxObserver);
        addDisposable(rxObserver);
    }

    @Override
    public void getFriend() {
        RxObserver<List<FriendBean>> rxObserver = new RxObserver<List<FriendBean>>(this) {
            @Override
            protected void onSuccess(List<FriendBean> data) {
                if (obtainView()!=null) {
                    obtainView().friendList(data);
                }
            }

            @Override
            public void onFaile(String errorMsg) {
                if (obtainView()!=null) {
                    obtainView().showError(errorMsg);
                }
            }
        };
        model.getFriend(rxObserver);
        addDisposable(rxObserver);
    }

    @Override
    public void getSearchContent(int page, String keyword) {
        RxPageListObserver<ArticleBean> pageListObserver = new RxPageListObserver<ArticleBean>(this) {
            @Override
            protected void onSuccess(List<ArticleBean> datas) {
                if (obtainView()!=null) {
                    obtainView().setData(datas);
                }
            }

            @Override
            protected void onFaile(int errorCode, String errorMsg) {
                if (obtainView()!=null) {
                    obtainView().showError(errorMsg);
                }
            }
        };
        model.getSearchContent(page, keyword, pageListObserver);
        addDisposable(pageListObserver);
    }
}
