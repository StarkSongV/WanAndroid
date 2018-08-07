package com.sjc.wanandroid.mvp.contract.main;

import com.sjc.wanandroid.mvp.contract.common.IBaseModel;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.contract.common.IBaseView;

/**
 * Created by sjc on 2018/6/4 17:22
 */

public interface WebContract {
    interface View extends IBaseView {

    }
    interface Model extends IBaseModel {

    }
    abstract class Presenter extends IBasePresenter<View> {

    }
}
