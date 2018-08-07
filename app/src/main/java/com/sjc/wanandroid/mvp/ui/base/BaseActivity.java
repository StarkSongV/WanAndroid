package com.sjc.wanandroid.mvp.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.contract.common.IBaseView;
import com.sjc.wanandroid.manager.AppManager;
import com.sjc.wanandroid.util.ToastUtils;


public abstract class BaseActivity<V extends IBaseView, P extends IBasePresenter<V>> extends AppCompatActivity implements IBaseView {
    public P mPresenter;
    private ProgressDialog loadingDialog;
    protected Toolbar mToolbar;
    protected FrameLayout mFlContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        AppManager.getInstance().addActivity(this);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
        init();
        initView();
        initAction();
        initData();
    }

    private void init() {
        loadingDialog = new ProgressDialog(this);
        mToolbar = (Toolbar) findViewById(R.id.tb_base_toolbar);
        mFlContent = (FrameLayout) findViewById(R.id.fl_base_content);
        setSupportActionBar(mToolbar);
        boolean initToolbar = initToolbar();
        if (initToolbar) {
            mToolbar.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                //必须要在setSupportActionBar之后,不然不起作用
                @Override
                public void onClick(View v) {
                    navigationIconClick();
                }
            });
        }else{
            mToolbar.setVisibility(View.GONE);
        }
        initContent(getLayoutId());
    }

    private void navigationIconClick() {
        finish();
    }

    protected abstract boolean initToolbar();

    private void initContent(int layoutId) {
        if (layoutId != 0) {
            View inflate = LayoutInflater.from(this).inflate(layoutId, mFlContent, false);
            mFlContent.addView(inflate);
        }
    }

    public void hideToolbar(boolean isHide) {
        mToolbar.setVisibility(isHide ? View.GONE : View.VISIBLE);
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();

    protected abstract void initView();

    protected abstract void initAction();

    protected abstract void initData();

    @Override
    public void closeLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public void showError(String error) {
        ToastUtils.show(error);
    }

    @Override
    public void startLoading(String message) {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this);
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setMessage(TextUtils.isEmpty(message) ? "数据加载中" : message);
            loadingDialog.show();
        } else {
            loadingDialog.setMessage(message);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter.removeAllDisposable();
        }
        AppManager.getInstance().finishActivity(this);
        super.onDestroy();
    }
}
