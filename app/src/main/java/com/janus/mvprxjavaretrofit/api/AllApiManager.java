package com.janus.mvprxjavaretrofit.api;

import com.janus.mvprxjavaretrofit.bean.Constant;
import com.janus.mvprxjavaretrofit.bean.ReqLogInterceptor;
import com.janus.mvprxjavaretrofit.bean.User;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by janus on 2017/3/2.
 */
public class AllApiManager {

    private static final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private static final OkHttpClient okHttpClient = builder
            .addInterceptor(new ReqLogInterceptor())    //添加网络请求拦截器
            .retryOnConnectionFailure(true)
            .build();

    private static final Retrofit Retrofit = new Retrofit.Builder()
            //不同请求都不需要改变的那段地址
            .baseUrl(Constant.BASE_PATH)
            //增加返回值为String的支持
            .addConverterFactory(ScalarsConverterFactory.create())
            //增加返回值为Gson的支持(以实体类返回)
            .addConverterFactory(GsonConverterFactory.create())
            //增加返回值为Oservable<T>的支持
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build();

    private static final AllApiManagerService allApiManagerService = Retrofit.create(AllApiManagerService.class);

    /**
     * 获取用户信息
     * @return 用户信息user类
     */
    public static Flowable<User> getUserInfo() {
        return allApiManagerService.getUserInfo();
    }
}
