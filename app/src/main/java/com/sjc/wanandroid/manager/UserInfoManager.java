package com.sjc.wanandroid.manager;

import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.sjc.wanandroid.common.Constant;
import com.sjc.wanandroid.mvp.model.bean.UserBean;
import com.sjc.wanandroid.util.AesEncryptionUtils;
import com.sjc.wanandroid.util.PreUtils;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by sjc on 2018/6/13 13:57
 */

public class UserInfoManager {
    private UserInfoManager() {
    }

    private static class INSTANCE {
        private static UserInfoManager userInfoManager = new UserInfoManager();
    }

    public static UserInfoManager getInstance() {
        return INSTANCE.userInfoManager;
    }

    private UserInfoManager userInfoManager;

    public void saveLoginStatus(boolean isLogin) {
        PreUtils.put(Constant.USER_KEY.ISLOGIN, isLogin);
    }

    public boolean isLogin() {
        return (boolean) PreUtils.get(Constant.USER_KEY.ISLOGIN, false);
    }

    public void saveUserInfo(UserBean userBean) {
        String userInfoJson = new Gson().toJson(userBean);
        SecretKeySpec key = AesEncryptionUtils.createKey();
        String encrypt = AesEncryptionUtils.encrypt(key, userInfoJson);
        //保存用户信息
        PreUtils.put(Constant.USER_KEY.USER_INFO, encrypt);
        //保存密钥
        saveAesKey(key);
    }

    public UserBean getUserInfo() {
        UserBean userBean = null;
        String userInfoStr = (String) PreUtils.get(Constant.USER_KEY.USER_INFO, "");
        if (TextUtils.isEmpty(userInfoStr)) {
            return null;
        }
        SecretKeySpec aesKey = getAesKey();
        String decrypt = AesEncryptionUtils.decrypt(aesKey, userInfoStr);
        userBean = new Gson().fromJson(decrypt, UserBean.class);
        return userBean;
    }

    private SecretKeySpec getAesKey() {
        String aeskey = (String) PreUtils.get(Constant.USER_KEY.AES, "");
        SecretKeySpec secretKey = AesEncryptionUtils.getSecretKey(Base64.decode(aeskey, Base64.DEFAULT));
        return secretKey;
    }

    private void saveAesKey(SecretKeySpec key) {
        PreUtils.put(Constant.USER_KEY.AES, Base64.encodeToString(key.getEncoded(), Base64.DEFAULT));
    }
}
