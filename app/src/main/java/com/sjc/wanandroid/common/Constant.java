package com.sjc.wanandroid.common;

/**
 * Created by sjc on 2018/6/7 14:56
 */

public class Constant {
    public static class USER_KEY {
        public static final String USER_INFO = "user_info";//用户信息
        public static final String ISLOGIN = "isLogin";//是否登录
        public static final String AES = "aes";//用户密钥
    }

    public static class HOME_KEY {
        public static final String ARTICLE_KEY = "article_key";
    }

    public static class WEB_KEY {
        public static final String WEB_TYPE = "web_type";
        public static final int WEB_NET = 0;
        public static final int WEB_ARTICLE = 1;
    }
    public static class PAGE_TYPE{
        public static final int HOME =0;
        public static final int TREE = 1;
        public static final int COLLECT = 2;
        public static final int SEARCH = 3;
    }
}
