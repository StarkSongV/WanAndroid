package com.sjc.wanandroid.mvp.contract.common;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by sjc on 2018/4/9 17:42
 */
public abstract class IBasePresenter<V> {
    private WeakReference<V> vWeakReference;
    //用来存放Disposable的容器
    private CompositeDisposable disposable;

    public void attach(V view) {
        vWeakReference = new WeakReference<>(view);
    }

    public void detach() {
        if (isAttch()) {
            vWeakReference.clear();
            vWeakReference = null;
        }
    }

    public V obtainView() {
        return isAttch() ? vWeakReference.get() : null;
    }

    private boolean isAttch() {
        return vWeakReference != null && vWeakReference.get() != null;
    }

    //添加指定的请求
    protected void addDisposable(Disposable disposable) {
        if (this.disposable == null) {
            this.disposable = new CompositeDisposable();
        }
        this.disposable.add(disposable);
    }

    //移除指定的请求
    protected void removeDisposable(Disposable disposable) {
        if (this.disposable != null) {
            this.disposable.remove(disposable);
        }
    }

    //取消所有的请求Tag
    public void removeAllDisposable() {
        if (this.disposable != null) {
            this.disposable.clear();
        }
    }
}
