package com.sjc.wanandroid.mvp.contract.login;

import com.sjc.wanandroid.mvp.contract.common.IBaseModel;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.contract.common.IBaseView;
import com.sjc.wanandroid.mvp.model.bean.UserBean;
import com.sjc.wanandroid.mvp.model.bean.RegisterBean;
import com.sjc.wanandroid.net.callback.RxObserver;

/**
 * Created by sjc on 2018/6/8 11:44
 */

public interface LoginAndRegContract {
    interface View extends IBaseView {
        void loginSuccess();

        void regSuccess();
    }
    abstract class Presenter extends IBasePresenter<View> {
        /**
         * 登录
         * @param username
         * @param pwd
         */
        public abstract void login(String username, String pwd);

        /**
         * 注册
         * @param username
         * @param pwd
         * @param confirmPwd
         */
        public abstract void register(String username, String pwd, String confirmPwd);
    }

    interface Model extends IBaseModel {
        /**
         * 登录
         * @param username
         * @param pwd
         */
        void login(String username, String pwd,RxObserver<UserBean> rxObserver);
        /**
         * 注册
         * @param username
         * @param pwd
         */
        void register(String username, String pwd,String confirmPwd, RxObserver<RegisterBean> observer);

        /**
         * 保存用户数据
         * @param userBean
         */
        void saveUserInfo(UserBean userBean);
    }
}
