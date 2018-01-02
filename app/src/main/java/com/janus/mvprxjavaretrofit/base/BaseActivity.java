package com.janus.mvprxjavaretrofit.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.janus.mvprxjavaretrofit.presenter.factory.PresenterLifecycleManager;
import com.janus.mvprxjavaretrofit.presenter.factory.PresenterFactory;
import com.janus.mvprxjavaretrofit.presenter.factory.ReflectionPresenterFactory;
import com.janus.mvprxjavaretrofit.presenter.factory.ViewWithPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView, ViewWithPresenter<P>{


    public P mPresenter;
    private Unbinder mButterKnifeBind;
    private PresenterLifecycleManager<P> mPresenterManager =
            new PresenterLifecycleManager<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mButterKnifeBind = ButterKnife.bind(this);
        mPresenterManager.onCreated(this);
        mPresenter = mPresenterManager.getPresenter();
        initData();
        Log.i("mvp", "BaseAc");
    }

    @Override
    protected void onDestroy() {
        mButterKnifeBind.unbind();
        super.onDestroy();
    }

    @Override
    public P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    @Override
    public PresenterFactory<P> getPresenterFactory() {
        return mPresenterManager.getPresenterFactory();
    }

    @Override
    public void setPresenterFactory(PresenterFactory<P> presenterFactory) {
        mPresenterManager.setPresenterFactory(presenterFactory);
    }

    public abstract void initData();
    public abstract int getLayoutId();
}
