package com.sjc.wanandroid.net;

import com.sjc.wanandroid.mvp.model.bean.ArticleBean;
import com.sjc.wanandroid.mvp.model.bean.BaseBean;
import com.sjc.wanandroid.mvp.model.bean.FriendBean;
import com.sjc.wanandroid.mvp.model.bean.HotKeyBean;
import com.sjc.wanandroid.mvp.model.bean.PageDataBean;
import com.sjc.wanandroid.mvp.model.bean.UserBean;
import com.sjc.wanandroid.mvp.model.bean.RegisterBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sjc on 2018/4/10 15:24
 */

public interface Api {
    /**
     * 注册
     * @param username 账号
     * @param password 密码
     * @return
     */
    @POST(AppNetConfig.REGISTER)
    @FormUrlEncoded
    Observable<BaseBean<RegisterBean>> register(@Field("username") String username
                                  ,@Field("password") String password,@Field("repassword") String repassword);

    /**
     * 登陆
     * @param username 账号
     * @param password 密码
     * @return
     */
    @POST(AppNetConfig.LOGIN)
    @FormUrlEncoded
    Observable<BaseBean<UserBean>> login(@Field("username") String username,
                                         @Field("password") String password);

    /**
     * 获取热词
     * @return
     */
    @GET(AppNetConfig.HOT_KEYWORD)
    Observable<BaseBean<List<HotKeyBean>>> getHotKey();

    /**
     * 获取常用网站
     * @return
     */
    @GET(AppNetConfig.FRIEND)
    Observable<BaseBean<List<FriendBean>>> getFriend();

    /**
     * 搜索
     * @param page
     * @param keyword
     * @return
     */
    @POST(AppNetConfig.SEARCH)
    @FormUrlEncoded
    Observable<BaseBean<PageDataBean<ArticleBean>>> search(@Path("page")int page, @Field("k")String keyword);
}
