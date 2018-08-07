package com.sjc.wanandroid.mvp.model.main;

import com.sjc.wanandroid.mvp.contract.main.SearchContract;
import com.sjc.wanandroid.mvp.model.bean.ArticleBean;
import com.sjc.wanandroid.mvp.model.bean.BaseBean;
import com.sjc.wanandroid.mvp.model.bean.FriendBean;
import com.sjc.wanandroid.mvp.model.bean.HotKeyBean;
import com.sjc.wanandroid.mvp.model.bean.PageDataBean;
import com.sjc.wanandroid.net.ApiManager;
import com.sjc.wanandroid.net.RxSchedulers;
import com.sjc.wanandroid.net.callback.RxObserver;
import com.sjc.wanandroid.net.callback.RxPageListObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sjc on 2018/6/28 14:43
 */

public class SearchModel implements SearchContract.Model {

    @Override
    public void getHotKey(final RxObserver<List<String>> rxObserver) {
        ApiManager.getInstance().getApiService()
                .getHotKey()
                .map(new Function<BaseBean<List<HotKeyBean>>,BaseBean<List<String>>>() {
                    @Override
                    public BaseBean<List<String>> apply(BaseBean<List<HotKeyBean>> listBaseBean) throws Exception {
                        List<HotKeyBean> data = listBaseBean.getData();
                        List<String> strings = new ArrayList<>();
                        for (int i = 0; i < data.size(); i++) {
                            strings.add(data.get(i).getName());
                        }
                        BaseBean<List<String>> listBaseBean1 = new BaseBean<>();
                        listBaseBean1.setData(strings);
                        return listBaseBean1;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rxObserver);
    }

    @Override
    public void getFriend(RxObserver<List<FriendBean>> rxObserver) {
        ApiManager.getInstance().getApiService()
                .getFriend()
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

    @Override
    public void getSearchContent(int page, String keyword, RxPageListObserver<ArticleBean> rxObserver) {
        ApiManager.getInstance().getApiService()
                .search(page,keyword)
                .compose(RxSchedulers.io_main())
                .subscribe(rxObserver);
    }

}
