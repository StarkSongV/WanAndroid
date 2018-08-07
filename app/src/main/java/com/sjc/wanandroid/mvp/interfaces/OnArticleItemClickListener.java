package com.sjc.wanandroid.mvp.interfaces;

import com.sjc.wanandroid.mvp.model.bean.ArticleBean;

public interface OnArticleItemClickListener {
    void onArticleItemClick(int position, ArticleBean bean);//文章条目点击监听
    void onArticleCollectClick(int position, ArticleBean bean);//文章被点击收藏
    void onArticleClassifyClick(int position, ArticleBean bean);//文章分类被点击
    void onArticleDeleteClick(int position, ArticleBean bean);//文章被点击删除收藏
}