package com.sjc.wanandroid.net.callback;

import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.model.bean.BaseBean;

/**
 * Created by sjc on 2018/6/8 13:20
 */

public abstract class RxObserver<T> extends RxBaseObserver<T> {

    public RxObserver(IBasePresenter iBasePresenter) {
        super(iBasePresenter);
    }

    @Override
    public void onNext(BaseBean<T> tBaseBean) {
        if (tBaseBean.getErrorCode() == NetConfig.REQUEST_SUCCESS) {
            onSuccess(tBaseBean.getData());
        }else{
            onFaile(tBaseBean.getErrorMsg());
        }
    }
    protected abstract void onSuccess(T data);
    public abstract void onFaile(String errorMsg);
}
