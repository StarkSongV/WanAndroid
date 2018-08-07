package com.sjc.wanandroid.mvp.contract.common;

/**
 * Created by sjc on 2018/4/9 17:43
 */

public interface IBaseView {
    void showError(String error);
    void startLoading(String message);
    void closeLoading();
}
