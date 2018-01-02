package com.janus.mvprxjavaretrofit.bean;

import android.util.Log;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Janus on 2017/5/17.
 *
 * 网络请求地址 拦截器
 */

public class ReqLogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Log.e("janus_test", String.format("发送请求 %s on %s %n %s",
                request.url(), chain.connection(), request.headers(), request.body()));
        return chain.proceed(request);
    }
}
