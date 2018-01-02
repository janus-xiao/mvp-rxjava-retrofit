package com.janus.mvprxjavaretrofit.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.janus.mvprxjavaretrofit.R;
import com.janus.mvprxjavaretrofit.base.BaseActivity;
import com.janus.mvprxjavaretrofit.bean.User;
import com.janus.mvprxjavaretrofit.model.UserModel;
import com.janus.mvprxjavaretrofit.presenter.UserContract;
import com.janus.mvprxjavaretrofit.presenter.UserPresenter;
import com.janus.mvprxjavaretrofit.presenter.factory.RequiresModel;
import com.janus.mvprxjavaretrofit.presenter.factory.RequiresPresenter;

import butterknife.BindView;

@RequiresModel(UserModel.class)
@RequiresPresenter(UserPresenter.class)
public class UserActivity extends BaseActivity<UserPresenter> implements UserContract.View {

    @BindView(R.id.test)
    private TextView test = null;

    @Override
    public void initData() {
        mPresenter.getUserInfo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user;
    }

    /**
     * 加载数据完毕更新视图
     *
     * @param user 数据对象
     */
    @Override
    public void loadUserInfoView(final User user) {
        test.setText("resCode: " + user.getResCode() + " resMsg: " + user.getResMsg());
    }

}
