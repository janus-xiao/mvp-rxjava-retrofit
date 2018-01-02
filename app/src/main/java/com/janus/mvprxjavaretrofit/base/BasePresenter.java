package com.janus.mvprxjavaretrofit.base;

import com.janus.mvprxjavaretrofit.presenter.factory.ReflectionPresenterFactory;

import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by janus on 2017/3/2.
 *
 */

public class BasePresenter<V, M> implements IBasePresenter{

    protected V view;
    protected M model;
    private CompositeDisposable mCompositeDisposable;
    /**
     * Rxjava 取消注册防止内存泄漏
     */
    @Override
    public void onUnSubscribe() {
        if(mCompositeDisposable != null && mCompositeDisposable.isDisposed()){
            mCompositeDisposable.clear();
        }
    }

    /**
     *  Rxjava 注册
     * @param disposable
     */
    @Override
    public void addSubscription(Disposable disposable) {
        if(mCompositeDisposable == null)
            mCompositeDisposable = new CompositeDisposable();
        if(disposable != null)
            mCompositeDisposable.add(disposable);
    }


    private CopyOnWriteArrayList<OnDestroyListener> onDestroyListeners = new CopyOnWriteArrayList<>();


    public interface OnDestroyListener {
        void onDestroy();
    }

    public void addOnDestroyListener(OnDestroyListener listener) {
        onDestroyListeners.add(listener);
    }

    public void destroy() {
        for (OnDestroyListener listener : onDestroyListeners) {
            listener.onDestroy();
        }
    }


    public void attachView(V view)  {
        this.view = view;
    }

    public void attachModel(Class<M> classm){
        //noinspection unchecked
        Class<M> modelClass = (Class<M>) ReflectionPresenterFactory.fromModelClass(classm);
        if (modelClass == null)
            throw new NullPointerException("attachModel is null");
        try {
            model = modelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
