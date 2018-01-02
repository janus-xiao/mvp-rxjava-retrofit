package com.janus.mvprxjavaretrofit.model;

import android.util.Log;

import com.janus.mvprxjavaretrofit.api.AllApiManager;
import com.janus.mvprxjavaretrofit.bean.Constant;
import com.janus.mvprxjavaretrofit.bean.User;
import com.janus.mvprxjavaretrofit.presenter.UserContract;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by janus on 2017/3/2.
 */

public class UserModel implements UserContract.Model{

    @Override
    public Flowable<User> getUserInfo() {
        return AllApiManager.getUserInfo();
    }
}
