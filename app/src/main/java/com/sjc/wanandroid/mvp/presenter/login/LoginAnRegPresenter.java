package com.sjc.wanandroid.mvp.presenter.login;

import com.sjc.wanandroid.mvp.contract.login.LoginAndRegContract;
import com.sjc.wanandroid.mvp.model.bean.RegisterBean;
import com.sjc.wanandroid.mvp.model.bean.UserBean;
import com.sjc.wanandroid.mvp.model.login.LoginAndRegModel;
import com.sjc.wanandroid.net.callback.RxObserver;

/**
 * Created by sjc on 2018/6/4 17:21
 */

public class LoginAnRegPresenter extends LoginAndRegContract.Presenter {
    private LoginAndRegContract.Model model;

    public LoginAnRegPresenter() {
        this.model = new LoginAndRegModel();
    }

    @Override
    public void login(String username, String pwd) {
        if (obtainView() != null) {
            obtainView().startLoading("登录中...");
            RxObserver<UserBean> rxObserver = new RxObserver<UserBean>(this) {
                @Override
                protected void onSuccess(UserBean data) {
                    model.saveUserInfo(data);
                    obtainView().loginSuccess();
                    obtainView().startLoading("登录成功!");
                    obtainView().closeLoading();
                }

                @Override
                public void onFaile(String errorMsg) {
                    obtainView().showError(errorMsg);
                    obtainView().closeLoading();
                }
            };
            model.login(username, pwd, rxObserver);
            addDisposable(rxObserver);
        }
    }

    @Override
    public void register(String username, String pwd, String confirmPwd) {
        if (obtainView() != null) {
            obtainView().startLoading("注册中...");
            RxObserver<RegisterBean> observer = new RxObserver<RegisterBean>(this) {
                @Override
                protected void onSuccess(RegisterBean data) {
                    obtainView().regSuccess();
                    obtainView().startLoading("注册成功!");
                    obtainView().closeLoading();
                }

                @Override
                public void onFaile(String errorMsg) {
                    obtainView().showError(errorMsg);
                    obtainView().closeLoading();
                }
            };
            model.register(username, pwd, confirmPwd, observer);
            addDisposable(observer);
        }
    }
}
