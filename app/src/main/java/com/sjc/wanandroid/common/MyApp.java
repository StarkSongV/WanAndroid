package com.sjc.wanandroid.common;

import android.app.Application;
import android.content.Context;

import com.sjc.wanandroid.util.PreUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by sjc on 2018/5/2 16:40
 */

public class MyApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        //初始化app配置
        AppConfig.initialize(this);
    }
    public static Context getInstance(){
        return context;
    }
}
