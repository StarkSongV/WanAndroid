package com.sjc.wanandroid.net.callback;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.sjc.wanandroid.R;
import com.sjc.wanandroid.common.MyApp;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.contract.common.IBaseView;
import com.sjc.wanandroid.mvp.model.bean.BaseBean;
import com.sjc.wanandroid.util.LogUtil;
import com.sjc.wanandroid.util.ToastUtils;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by sjc on 2018/6/7 14:15
 */

public abstract class RxBaseObserver<T> extends DisposableObserver<BaseBean<T>> {
    private IBaseView view;
    public RxBaseObserver(IBasePresenter iBasePresenter) {
        view = (IBaseView) iBasePresenter.obtainView();
    }


    @Override
    public void onError(Throwable e) {
        LogUtil.d("e==="+e.getMessage());
        dealException(MyApp.getInstance(), e);
    }

    private void dealException(Context context, Throwable t) {
        if (view!=null) {
            view.closeLoading();
        }
        if (t instanceof ConnectException || t instanceof UnknownHostException) {
            //连接错误
            onException(NetConfig.CONNECT_ERROR, context);
        } else if (t instanceof InterruptedException) {
            //连接超时
            onException(NetConfig.CONNECT_TIMEOUT, context);
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {
            //解析错误
            onException(NetConfig.PARSE_ERROR, context);
        } else if (t instanceof SocketTimeoutException) {
            //请求超时
            onException(NetConfig.REQUEST_TIMEOUT, context);
        } else if (t instanceof UnknownError) {
            //未知错误
            onException(NetConfig.UNKNOWN_ERROR, context);
        } else {
            //未知错误
            onException(NetConfig.UNKNOWN_ERROR, context);
        }
    }

    private void onException(int connectError, Context context) {
        switch (connectError) {
            case NetConfig.CONNECT_ERROR:
                ToastUtils.show(R.string.connect_error);
                break;
            case NetConfig.CONNECT_TIMEOUT:
                ToastUtils.show(R.string.connect_timeout);
                break;
            case NetConfig.PARSE_ERROR:
                ToastUtils.show(R.string.parse_error);
                break;
            case NetConfig.REQUEST_TIMEOUT:
                ToastUtils.show(R.string.request_timeout);
                break;
            case NetConfig.UNKNOWN_ERROR:
                ToastUtils.show(R.string.unknown_error);
                break;
        }
    }

    @Override
    public void onComplete() {

    }
}
