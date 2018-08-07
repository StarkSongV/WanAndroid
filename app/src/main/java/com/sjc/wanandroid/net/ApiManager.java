package com.sjc.wanandroid.net;


import com.sjc.wanandroid.net.interceptor.LoadCookieInterceptor;
import com.sjc.wanandroid.net.interceptor.LogInterceptor;
import com.sjc.wanandroid.net.interceptor.SaveCookieInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qhy on 2018/4/4.
 */

public class ApiManager {
    private Api apiService;
    //请求超时时间
    private static final int REQUEST_TIME = 10;

    private static class INSTANCE {
        private static final ApiManager apiManager = new ApiManager();
    }

    public static ApiManager getInstance() {
        return INSTANCE.apiManager;
    }

    public Api getApiService() {
        if (apiService == null) {
            //自定义OkHttpClient

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder
                    .connectTimeout(REQUEST_TIME, TimeUnit.SECONDS)//链接超时时长
                    .readTimeout(REQUEST_TIME, TimeUnit.SECONDS)//读取超时时长
                    .writeTimeout(REQUEST_TIME, TimeUnit.SECONDS)//写入超时时长
                    .addInterceptor(new LogInterceptor())
                    .addInterceptor(new SaveCookieInterceptor())
                    .addInterceptor(new LoadCookieInterceptor());

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(AppNetConfig.BASE_URL)
                    .client(builder.build())
                    .build();
            apiService = retrofit.create(Api.class);
        }
        return apiService;
    }
}
