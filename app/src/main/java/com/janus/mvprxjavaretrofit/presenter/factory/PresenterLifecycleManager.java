package com.janus.mvprxjavaretrofit.presenter.factory;

import android.os.Bundle;

import com.janus.mvprxjavaretrofit.base.BasePresenter;
import com.janus.mvprxjavaretrofit.presenter.factory.ParcelFn;
import com.janus.mvprxjavaretrofit.presenter.factory.PresenterFactory;
import com.janus.mvprxjavaretrofit.presenter.factory.PresenterLifecycleImpl;
import com.janus.mvprxjavaretrofit.presenter.factory.PresenterStorage;


/**
 * Created by Administrator on 2016/9/19.
 *
 */
public class PresenterLifecycleManager<P extends BasePresenter> implements PresenterLifecycleImpl {

    private static final String PRESENTER_ID_KEY = "presenter_id";

    private PresenterFactory<P> presenterFactory;
    private P presenter;
    private Bundle bundle;

    public PresenterLifecycleManager(PresenterFactory<P> presenterFactory) {
        this.presenterFactory = presenterFactory;
    }

    public P getPresenter() {
        if (presenterFactory != null) {
            if (presenter == null && bundle != null)
                presenter = PresenterStorage.INSTANCE.getPresenter(bundle.getString(PRESENTER_ID_KEY));

            if (presenter == null) {
                presenter = presenterFactory.createPresenter();
                PresenterStorage.INSTANCE.add(presenter);
            }
            bundle = null;
        }
        return presenter;
    }

    public void setPresenterFactory(PresenterFactory<P> presenterFactory) {
        if (presenter != null)
            throw new IllegalArgumentException("setPresenterFactory() should be called before onResume()");
        this.presenterFactory = presenterFactory;
    }

    public PresenterFactory<P> getPresenterFactory() {
        return presenterFactory;
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        getPresenter();
        if (presenter != null) {
            bundle.putString(PRESENTER_ID_KEY, PresenterStorage.INSTANCE.getId(presenter));
        }
        return bundle;
    }

    public void onRestoreInstanceState(Bundle presenterState) {
        if (presenter != null)
            throw new IllegalArgumentException("onRestoreInstanceState() should be called before onResume()");
        this.bundle = ParcelFn.unmarshall(ParcelFn.marshall(presenterState));
    }


    @Override
    public void onCreated(Object view) {
        getPresenter();
        if(presenter!=null){
            //noinspection unchecked
            presenter.attachView(view);
            //noinspection unchecked
            presenter.attachModel(view.getClass());
//            presenter.onCreated();
        }
    }
}