package com.sjc.wanandroid.common;

import android.app.Application;

import com.sjc.wanandroid.manager.UserInfoManager;
import com.sjc.wanandroid.util.PreUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by sjc on 2018/6/13 14:23
 */

public class AppConfig {
    public static void initialize(Application context){
        LeakCanary.install(context);
        PreUtils.init(context);
        UserInfoManager.getInstance().saveLoginStatus(false);
    }
}
