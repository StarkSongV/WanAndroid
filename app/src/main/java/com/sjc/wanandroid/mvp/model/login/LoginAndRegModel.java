package com.sjc.wanandroid.mvp.model.login;

import android.text.TextUtils;

import com.sjc.wanandroid.manager.UserInfoManager;
import com.sjc.wanandroid.mvp.contract.login.LoginAndRegContract;
import com.sjc.wanandroid.mvp.model.bean.UserBean;
import com.sjc.wanandroid.mvp.model.bean.RegisterBean;
import com.sjc.wanandroid.net.ApiManager;
import com.sjc.wanandroid.net.RxSchedulers;
import com.sjc.wanandroid.net.callback.RxObserver;
import com.sjc.wanandroid.util.RegexUtils;

/**
 * Created by sjc on 2018/6/11 17:10
 */
public class LoginAndRegModel implements LoginAndRegContract.Model{

    @Override
    public void login(String username, String pwd,RxObserver<UserBean> observer) {
        if (TextUtils.isEmpty(username)) {
            observer.onFaile("账号不能为空!");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            observer.onFaile("密码不能为空!");
            return;
        }
        if (!RegexUtils.checkMobile(username)) {
            observer.onFaile("请输入合法的手机号!");
            return;
        }
        ApiManager.getInstance().getApiService()
                .login(username, pwd)
                .compose(RxSchedulers.io_main()).subscribe(observer);
    }

    @Override
    public void register(String username, String pwd, String confirmPwd, RxObserver<RegisterBean> observer) {
        if (TextUtils.isEmpty(username)) {
            observer.onFaile("账号不能为空!");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            observer.onFaile("密码不能为空!");
            return;
        }
        if (TextUtils.isEmpty(confirmPwd)) {
            observer.onFaile("请再次确认密码!");
            return;
        }
        if (!RegexUtils.checkMobile(username)) {
            observer.onFaile("请输入合法的手机号!");
            return;
        }
        if (!TextUtils.equals(pwd, confirmPwd)) {
            observer.onFaile("两次输入的密码不同!");
            return;
        }
        ApiManager.getInstance().getApiService()
                .register(username, pwd,confirmPwd)
                .compose(RxSchedulers.io_main()).subscribe(observer);
    }

    @Override
    public void saveUserInfo(UserBean userBean) {
        UserInfoManager.getInstance().saveUserInfo(userBean);
        UserInfoManager.getInstance().saveLoginStatus(true);
    }

}
