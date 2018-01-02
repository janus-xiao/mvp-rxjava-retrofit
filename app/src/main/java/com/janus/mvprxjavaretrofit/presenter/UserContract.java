package com.janus.mvprxjavaretrofit.presenter;

import com.janus.mvprxjavaretrofit.base.BasePresenter;
import com.janus.mvprxjavaretrofit.bean.User;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * Created by janus on 2017/3/12.
 */

public class UserContract {

    public interface Model{
        Flowable<User> getUserInfo();
    }

    public interface View{
        void loadUserInfoView(User user);
    }

//    abstract class Presenter extends BasePresenter<View>{
//    }
}
