package com.janus.mvprxjavaretrofit.presenter.factory;


import com.janus.mvprxjavaretrofit.base.BasePresenter;

public interface PresenterFactory<P extends BasePresenter> {
    P createPresenter();
}
