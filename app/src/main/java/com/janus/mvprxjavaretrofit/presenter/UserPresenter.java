package com.janus.mvprxjavaretrofit.presenter;

import android.util.Log;

import com.janus.mvprxjavaretrofit.base.BasePresenter;
import com.janus.mvprxjavaretrofit.bean.Constant;
import com.janus.mvprxjavaretrofit.bean.User;
import com.janus.mvprxjavaretrofit.model.UserModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by janus on 2017/3/2.
 *
 */

public class UserPresenter extends BasePresenter<UserContract.View, UserModel>{

    /**
     * 获取用户信息
     * @return 用户信息
     */
    public void getUserInfo(){
        addSubscription(model.getUserInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        view.loadUserInfoView(user);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(Constant.TAG, "onFailure Presenter");
                    }
                }));
    }
}
