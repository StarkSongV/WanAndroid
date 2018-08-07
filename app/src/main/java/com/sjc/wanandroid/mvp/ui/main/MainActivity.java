package com.sjc.wanandroid.mvp.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sjc.wanandroid.R;
import com.sjc.wanandroid.manager.UserInfoManager;
import com.sjc.wanandroid.mvp.contract.common.IBasePresenter;
import com.sjc.wanandroid.mvp.ui.base.BaseActivity;
import com.sjc.wanandroid.mvp.ui.fragment.HomeFragment;
import com.sjc.wanandroid.mvp.ui.fragment.KnowledgeTreeFragment;
import com.sjc.wanandroid.mvp.ui.login.LoginAndRegisterActivity;
import com.sjc.wanandroid.util.PreUtils;
import com.sjc.wanandroid.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private List<Fragment> mFsList = new ArrayList<>();
    private android.support.design.widget.BottomNavigationView mBnvMainBottom;
    private int lastIndex = -1;
    private DrawerLayout mDrawerlayout;
    private NavigationView mLeftMenu;
    private TextView mTvMenuPhone;

    @Override
    protected boolean initToolbar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

        mBnvMainBottom = (BottomNavigationView) findViewById(R.id.bnv_main_bottom);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mLeftMenu = (NavigationView) findViewById(R.id.left_menu);
        View headerView = mLeftMenu.getHeaderView(0);
        mTvMenuPhone = headerView.findViewById(R.id.tv_menu_phone);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUserData();
    }

    private void setUserData() {
        if (UserInfoManager.getInstance().isLogin()) {
            mTvMenuPhone.setText(UserInfoManager.getInstance().getUserInfo().getUsername());
        } else {
            mTvMenuPhone.setText(R.string.not_login);
        }
    }

    @Override
    protected void initAction() {
        mBnvMainBottom.setOnNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerlayout, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawerlayout.addDrawerListener(toggle);
        mLeftMenu.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.favorite:
                    if (UserInfoManager.getInstance().isLogin()) {

                    } else {
                        startActivity(new Intent(MainActivity.this, LoginAndRegisterActivity.class));
                    }
                    break;
                case R.id.about_us:

                    break;
                case R.id.logout:
                    startActivity(new Intent(MainActivity.this, LoginAndRegisterActivity.class));
                    PreUtils.clearAll();
                    break;
            }
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_search:
                startActivity(new Intent(this,SearchActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        initTabFragment();
    }

    private void initTabFragment() {
        mFsList.add(new HomeFragment());
        mFsList.add(new KnowledgeTreeFragment());

        // 默认选中第一个
        changeFrag(0);

    }


    public void changeFrag(int curIndex) {
        if (lastIndex == curIndex) {
            return;
        }
        lastIndex = curIndex;
        if (curIndex == 0) {
            setTitle(R.string.app_name);
        } else {
            setTitle(R.string.knowledge);
        }
        for (int i = 0; i < mFsList.size(); i++) {
            if (i == curIndex) {
                showFragment(mFsList.get(i));
            } else {
                hideFragment(mFsList.get(i));
            }
        }
    }


    /**
     * 隐藏当前的fragment
     */
    protected void hideFragment(Fragment currFragment) {
        if (currFragment == null) {
            return;
        }
        FragmentTransaction currFragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        currFragment.onPause();
        if (currFragment.isAdded()) {
            currFragmentTransaction.hide(currFragment);
            currFragmentTransaction.commitAllowingStateLoss();
        }
    }

    /**
     * 显示当前的fragment
     */
    protected void showFragment(Fragment startFragment) {
        if (startFragment == null) {
            return;
        }
        FragmentTransaction startFragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        if (!startFragment.isAdded()) {
            startFragmentTransaction.add(R.id.fl_main_content, startFragment);
        } else {
            startFragment.onResume();
            startFragmentTransaction.show(startFragment);
        }
        startFragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                changeFrag(0);
                break;
            case R.id.knowledge:
                changeFrag(1);
                break;
        }
        return true;
    }

    private long lastPressTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mDrawerlayout.isDrawerOpen(Gravity.START)) {
                mDrawerlayout.closeDrawer(Gravity.START);
                return true;
            }
            if (System.currentTimeMillis() - lastPressTime > 2000) {
                lastPressTime = System.currentTimeMillis();
                ToastUtils.show("再按一次退出");
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
