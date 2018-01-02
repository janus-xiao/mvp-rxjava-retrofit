package com.janus.mvprxjavaretrofit.api;

import com.janus.mvprxjavaretrofit.bean.User;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by janus on 2017/3/3.
 */
public interface AllApiManagerService {

    @POST("html5/live?roomId=85981")
    Flowable<User> getUserInfo();
}
