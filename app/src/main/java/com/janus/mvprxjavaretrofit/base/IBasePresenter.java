package com.janus.mvprxjavaretrofit.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by janus on 2017/3/12.
 */
public interface IBasePresenter {
    void onUnSubscribe();
    void addSubscription(Disposable disposable);
}
