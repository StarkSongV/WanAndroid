package com.sjc.wanandroid.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sjc.wanandroid.R;

/**
 * 四种状态的布局
 * Created by sjc on 2018/7/3 15:17
 */

public class ContainerLayout extends FrameLayout {

    private View mLoadingView; //加载中
    private View mErrorView; //加载错误
    private View mNodataView; //没有数据
    private View mContentView; //显示内容页面
    private TextView tv_nodata;
    private TextView tv_load_error;

    private Context mContext;
    private LayoutInflater mInflater;
    private OnClickReLoadListener listener;

    public ContainerLayout(@NonNull Context context) {
        super(context);
    }

    public ContainerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public ContainerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLoadingView = mInflater.inflate(R.layout.container_loading_layout, this,false);
        mErrorView = mInflater.inflate(R.layout.container_error_layout, this,false);
        mNodataView = mInflater.inflate(R.layout.container_nodata_layout, this,false);
        tv_nodata = mNodataView.findViewById(R.id.tv_load_nodata);
        tv_load_error = mErrorView.findViewById(R.id.tv_load_error);
        addView(mLoadingView);
        addView(mErrorView);
        addView(mNodataView);
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mNodataView.setVisibility(GONE);

        tv_load_error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    showLoading();
                    listener.onReLoadClick(ContainerLayout.this);
                }
            }
        });
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof RecyclerView) {
                mContentView = childAt;
                break;
            }
        }
    }

    //加载状态
    public void showLoading() {
        if (mContentView != null) {
            mContentView.setVisibility(GONE);
        }
        mLoadingView.setVisibility(VISIBLE);
        mErrorView.setVisibility(GONE);
        mNodataView.setVisibility(GONE);
    }

    //显示内容
    public void showContent() {
        if (mContentView != null) {
            mContentView.setVisibility(VISIBLE);
        }
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mNodataView.setVisibility(GONE);
    }

    //显示错误
    public void showError() {
        if (mContentView != null) {
            mContentView.setVisibility(GONE);
        }
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(VISIBLE);
        mNodataView.setVisibility(GONE);
    }

    //没有数据
    public void showNodata() {
        if (mContentView != null) {
            mContentView.setVisibility(GONE);
        }
        mLoadingView.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mNodataView.setVisibility(VISIBLE);
    }
    //没有数据
    public void showNoDataText(String content) {
        tv_nodata.setText(content);
    }
    public void setOnClickReLoadListener(OnClickReLoadListener listener){
        this.listener = listener;
    }
    public interface OnClickReLoadListener{
        void onReLoadClick(ContainerLayout layout);
    }
}
