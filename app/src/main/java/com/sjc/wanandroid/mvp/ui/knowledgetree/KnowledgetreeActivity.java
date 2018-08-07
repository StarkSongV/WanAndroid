package com.sjc.wanandroid.mvp.ui.knowledgetree;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.ui.base.BaseActivity;

public class KnowledgetreeActivity extends BaseActivity {

    private TabLayout mTab;
    private ViewPager mViewpager;

    @Override
    protected boolean initToolbar() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledgetree;
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        mTab = (TabLayout) findViewById(R.id.tl_knowledgetree_tab);
        mViewpager = (ViewPager) findViewById(R.id.vp_knowledgetree_viewpager);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
