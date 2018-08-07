package com.sjc.wanandroid.net.callback;

import com.sjc.wanandroid.mvp.contract.common.IBaseListDataView;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.model.bean.BaseBean;
import com.sjc.wanandroid.mvp.model.bean.PageDataBean;

import java.util.List;

/**
 * Created by sjc on 2018/7/10 17:48
 */

public abstract class RxPageListObserver<T> extends RxBaseObserver<PageDataBean<T>> {

    private final IBaseListDataView iListDataView;

    public RxPageListObserver(IBasePresenter iBasePresenter) {
        super(iBasePresenter);
        iListDataView = (IBaseListDataView) iBasePresenter.obtainView();
    }

    @Override
    public void onNext(BaseBean<PageDataBean<T>> listDataBeanBaseBean) {
        int errorCode = listDataBeanBaseBean.getErrorCode();
        if (errorCode == NetConfig.REQUEST_SUCCESS) {
            PageDataBean<T> data = listDataBeanBaseBean.getData();
            if (iListDataView!=null) {
                if (iListDataView.getPage()==0) {
                    iListDataView.clearAllList();
                }
                if (data.isOver()) {
                    iListDataView.showNoMore();
                }else{
                    iListDataView.setLoadMoreConfig();
                }
                onSuccess(data.getDatas());
                if (iListDataView.getData().size()==0) {
                    iListDataView.showNodata();
                }else{
                    iListDataView.showContent();
                }
            }
        }else{
            if (iListDataView!=null) {
                iListDataView.isLoadMoreError(iListDataView.getPage()!=0);

                onFaile(errorCode,listDataBeanBaseBean.getErrorMsg());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (iListDataView!=null) {
            iListDataView.isLoadMoreError(iListDataView.getPage()!=0);
        }
    }

    protected abstract void onSuccess(List<T> datas);

    protected abstract void onFaile(int errorCode, String errorMsg);
}
