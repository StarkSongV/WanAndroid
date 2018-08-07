package com.sjc.wanandroid.mvp.ui.login;

import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.mvp.contract.login.LoginAndRegContract;
import com.sjc.wanandroid.mvp.presenter.login.LoginAnRegPresenter;
import com.sjc.wanandroid.mvp.ui.base.BaseActivity;
import com.sjc.wanandroid.view.ClearEditText;
import com.sjc.wanandroid.util.ToastUtils;

public class LoginAndRegisterActivity extends BaseActivity<LoginAndRegContract.View,LoginAndRegContract.Presenter>
        implements View.OnClickListener,LoginAndRegContract.View{
    private TextInputLayout mTilLoginUsername, mTilLoginPwd,mTilRegisterUsername,mTilRegisterPwd,mTilRegisterAffirmpwd;
    private ClearEditText mEtLoginUsername, mEtLoginPwd,mEtRegisterUsername,mEtRegisterPwd,mEtRegisterAffirmpwd;
    private LinearLayout mLlLogin,mLlRegister;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private boolean isRegister = false;
    private TextView mTvLoginTitle;
    private ImageButton mIbtnRegisterBack;
    private String loginUserName;
    private String loginPwd;
    private String regUserName;
    private String regPwd;
    private String regAffirmPwd;

    @Override
    protected boolean initToolbar() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_loginandregister;
    }

    @Override
    protected LoginAndRegContract.Presenter createPresenter() {
        return new LoginAnRegPresenter();
    }


    @Override
    protected void initView() {
        mTvLoginTitle = (TextView) findViewById(R.id.tv_login_title);
        mLlLogin = (LinearLayout) findViewById(R.id.ll_login);
        mTilLoginUsername = (TextInputLayout)findViewById(R.id.til_login_username);
        mTilLoginPwd = (TextInputLayout)findViewById(R.id.til_login_pwd);
        mEtLoginUsername = (ClearEditText)findViewById(R.id.et_login_username);
        mEtLoginPwd = (ClearEditText)findViewById(R.id.et_login_pwd);

        mLlRegister = (LinearLayout) findViewById(R.id.ll_register);
        mTilRegisterUsername = (TextInputLayout) findViewById(R.id.til_register_username);
        mEtRegisterUsername = (ClearEditText) findViewById(R.id.et_register_username);
        mTilRegisterPwd = (TextInputLayout) findViewById(R.id.til_register_pwd);
        mEtRegisterPwd = (ClearEditText) findViewById(R.id.et_register_pwd);
        mTilRegisterAffirmpwd = (TextInputLayout) findViewById(R.id.til_register_affirmpwd);
        mEtRegisterAffirmpwd = (ClearEditText) findViewById(R.id.et_register_affirmpwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mIbtnRegisterBack = (ImageButton) findViewById(R.id.ibtn_register_back);

        hideToolbar(true);
    }

    @Override
    protected void initAction() {
        mBtnLogin.setOnClickListener(this);
        mIbtnRegisterBack.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                initEdit();
//                mEtLoginUsername.startShakeAnimation();
//                mTilLoginUsername.setError("手机号输入不正确!");
                mPresenter.login(loginUserName,loginPwd);
                break;
            case R.id.btn_register:
                if (!isRegister) {
                    setLayout(true);
                } else {
                    initEdit();
                    mPresenter.register(regUserName,regPwd,regAffirmPwd);
                }
                break;
            case R.id.ibtn_register_back:
                setLayout(false);
                break;
        }
    }

    private void initEdit() {
        loginUserName = mEtLoginUsername.getText().toString().trim();
        loginPwd = mEtLoginPwd.getText().toString().trim();
        regUserName = mEtRegisterUsername.getText().toString().trim();
        regPwd = mEtRegisterPwd.getText().toString().trim();
        regAffirmPwd = mEtRegisterAffirmpwd.getText().toString().trim();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isRegister) {
            setLayout(false);
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }

    /**
     * 设置注册和登录布局
     * @param isRegister 是否是注册
     */
    public void setLayout(boolean isRegister) {
        this.isRegister = isRegister;
        if (isRegister) {
            mBtnLogin.setVisibility(View.GONE);
            mLlLogin.setVisibility(View.GONE);
            mLlRegister.setVisibility(View.VISIBLE);
            mTvLoginTitle.setText("注册");
            mIbtnRegisterBack.setVisibility(View.VISIBLE);
        } else {
            mBtnLogin.setVisibility(View.VISIBLE);
            mLlLogin.setVisibility(View.VISIBLE);
            mLlRegister.setVisibility(View.GONE);
            mTvLoginTitle.setText("登录");
            mIbtnRegisterBack.setVisibility(View.GONE);
        }
    }

    @Override
    public void loginSuccess() {
        finish();
        ToastUtils.show(R.string.login_success);
    }

    @Override
    public void regSuccess() {
        ToastUtils.show("注册成功!");
        setLayout(false);
    }
}
