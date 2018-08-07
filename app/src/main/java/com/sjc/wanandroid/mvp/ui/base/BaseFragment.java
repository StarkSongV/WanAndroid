package com.sjc.wanandroid.mvp.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.contract.common.IBaseView;
import com.sjc.wanandroid.util.ToastUtils;

/**
 * Created by sjc on 2018/6/19 13:55
 */

public abstract class BaseFragment<V extends IBaseView, P extends IBasePresenter<V>> extends Fragment implements IBaseView{
    public P mPresenter;
    private ProgressDialog loadingDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null) getBundle(bundle);

    }

    protected abstract void getBundle(Bundle bundle);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
        initViews(inflate);
        initAction(inflate);
        initData();
        return inflate;
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View inflate);

    protected abstract void initAction(View inflate);

    protected abstract void initData();

    protected abstract P createPresenter();
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
            loadingDialog = new ProgressDialog(getActivity());
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
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter.removeAllDisposable();
        }
        super.onDestroy();
    }

}
