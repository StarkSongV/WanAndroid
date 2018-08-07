package com.sjc.wanandroid.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sjc on 2018/6/12 11:43
 */

public class RxSchedulers {
    /**
     * 指定上游线程为io线程
     * ,下游线程为主线程
     * @return
     */
    public static ObservableTransformer io_main(){
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
