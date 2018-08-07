package com.sjc.wanandroid.mvp.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.just.agentweb.AgentWeb;
import com.sjc.wanandroid.R;
import com.sjc.wanandroid.common.Constant;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.model.bean.ArticleBean;
import com.sjc.wanandroid.mvp.ui.base.BaseActivity;

import java.lang.reflect.Method;

public class WebActivity extends BaseActivity {

    private AgentWeb mAgentWeb;
    private ArticleBean articleBean;
    private int web_type;

    @Override
    protected boolean initToolbar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        initIntent();
        FrameLayout mFlContent = findViewById(R.id.fl_web_content);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mFlContent, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(R.color.black)
                .createAgentWeb()
                .ready()
                .go(articleBean==null?"":articleBean.getLink());

    }

    private void initIntent() {
        articleBean = (ArticleBean) getIntent().getSerializableExtra(Constant.HOME_KEY.ARTICLE_KEY);
        web_type = getIntent().getIntExtra(Constant.WEB_KEY.WEB_TYPE, -1);
    }

    @Override
    protected void initAction() {}

    @Override
    protected void initData() {
        setTitle(articleBean.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_settting,menu);
        setIconsVisible(menu,true);
        MenuItem item = menu.findItem(R.id.menu_collect);
        if (web_type== Constant.WEB_KEY.WEB_NET) {
            item.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void setIconsVisible(Menu menu, boolean flag) {
        //判断menu是否为空
        if(menu != null) {
            try {
                //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                //暴力访问该方法
                method.setAccessible(true);
                //调用该方法显示icon
                method.invoke(menu, flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.menu_share:
                share();
                break;
            case  R.id.menu_collect:
                collect();
                break;
            case  R.id.menu_open_Browser:
                openBrowser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //分享
    private void share() {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, articleBean==null?"":articleBean.getLink());
        startActivity(Intent.createChooser(textIntent, "分享"));
    }
    //收藏
    private void collect() {

    }
    //浏览器打开
    private void openBrowser() {
        Uri uri = Uri.parse(articleBean==null?"":articleBean.getLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.destroy();
        super.onDestroy();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
