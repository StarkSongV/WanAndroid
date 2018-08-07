package com.sjc.wanandroid.mvp.ui.main;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.adapter.ArticleRVAdapter;
import com.sjc.wanandroid.adapter.CommonRVAdapter;
import com.sjc.wanandroid.common.Constant;
import com.sjc.wanandroid.mvp.contract.main.SearchContract;
import com.sjc.wanandroid.mvp.interfaces.OnArticleItemClickListener;
import com.sjc.wanandroid.mvp.model.bean.ArticleBean;
import com.sjc.wanandroid.mvp.model.bean.FriendBean;
import com.sjc.wanandroid.mvp.presenter.main.SearchPresenter;
import com.sjc.wanandroid.mvp.ui.base.BaseListDataActivity;
import com.sjc.wanandroid.view.FlowGroupView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseListDataActivity<SearchContract.View,SearchContract.Presenter,ArticleBean>
        implements SearchContract.View{

    private SearchView searchView;
    private SearchView.SearchAutoComplete searchAutoComplete;
    private String keyword = "";
    private FlowGroupView mFgvSearchKeyword;
    private FlowGroupView mFgvSearchUsefulSites;
    private List<FriendBean> friendList;
    private View keyContent;
    private ArticleRVAdapter articleRVAdapter;

    @Override
    protected boolean initToolbar() {
        return true;
    }

    @Override
    protected SearchContract.Presenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        keyContent = LayoutInflater.from(this).inflate(R.layout.activity_search, mFlContent, false);
        mFgvSearchKeyword = (FlowGroupView) keyContent.findViewById(R.id.fgv_search_keyword);
        mFgvSearchUsefulSites = (FlowGroupView) keyContent.findViewById(R.id.fgv_search_usefulSites);
        mRefresh.setVisibility(View.GONE);
        mFlContent.addView(keyContent);
        mContainer.showNoDataText("暂无搜索内容");
    }

    @Override
    protected CommonRVAdapter<ArticleBean> getRVAdapter() {
        articleRVAdapter = new ArticleRVAdapter(getData(),Constant.PAGE_TYPE.SEARCH);
        return articleRVAdapter;
    }

    @Override
    protected void initAction() {
        super.initAction();
        mFgvSearchKeyword.setOnItemClickListener(new FlowGroupView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String content) {
                searchView.setQuery(content,true);
            }
        });
        mFgvSearchUsefulSites.setOnItemClickListener(new FlowGroupView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String content) {
                if (friendList!=null) {
                    FriendBean friendBean = friendList.get(position);
                    ArticleBean articleBean = new ArticleBean();
                    articleBean.setLink(friendBean.getLink());
                    articleBean.setTitle(friendBean.getName());
                    Intent intent = new Intent(SearchActivity.this, WebActivity.class);
                    intent.putExtra(Constant.HOME_KEY.ARTICLE_KEY,articleBean);
                    intent.putExtra(Constant.WEB_KEY.WEB_TYPE,Constant.WEB_KEY.WEB_NET);
                    startActivity(intent);
                }
            }
        });
        articleRVAdapter.setOnArticleItemClickListener(new OnArticleItemClickListener() {
            @Override
            public void onArticleItemClick(int position, ArticleBean bean) {
                Intent intent = new Intent(SearchActivity.this, WebActivity.class);
                intent.putExtra(Constant.HOME_KEY.ARTICLE_KEY,bean);
                intent.putExtra(Constant.WEB_KEY.WEB_TYPE,Constant.WEB_KEY.WEB_ARTICLE);
                startActivity(intent);
            }

            @Override
            public void onArticleCollectClick(int position, ArticleBean bean) {

            }

            @Override
            public void onArticleClassifyClick(int position, ArticleBean bean) {

            }

            @Override
            public void onArticleDeleteClick(int position, ArticleBean bean) {

            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getHotKey();
        mPresenter.getFriend();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.search_edit);
        //获取搜索框
        searchView = (SearchView) item.getActionView();
        //设置关键词
        searchView.setQueryHint(getString(R.string.keyword));
        searchView.onActionViewExpanded();
        //获取输入框,并设置输入框hint的色值
        searchAutoComplete = searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.gray));
        //设置右上角清除按钮的水波纹效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageView imageView = searchView.findViewById(R.id.search_close_btn);
            imageView.setBackgroundResource(R.drawable.image_ripple);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                keyword = query;
                mRefresh.setVisibility(View.VISIBLE);
                mFlContent.removeView(keyContent);
                firstLoadData();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    mRefresh.setVisibility(View.GONE);
                    mFlContent.addView(keyContent);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected View initHeaderView() {
        return null;
    }

    @Override
    protected boolean isCanLoadMore() {
        return true;
    }

    @Override
    protected void loadData() {
        mPresenter.getSearchContent(page,keyword);
    }

    @Override
    public void hotKeyList(List<String> hotKeyList) {
        mFgvSearchKeyword.addList(hotKeyList);
    }

    @Override
    public void friendList(List<FriendBean> friendList) {
        this.friendList = friendList;
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < friendList.size(); i++) {
            strings.add(friendList.get(i).getName());
        }
        mFgvSearchUsefulSites.addList(strings);
    }

    @Override
    public void setData(List<ArticleBean> data) {
        serEnableRefresh(false);
        listData.addAll(data);
        mFlContent.removeView(keyContent);
        mRefresh.setVisibility(View.VISIBLE);
    }
}
