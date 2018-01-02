package com.janus.mvprxjavaretrofit.presenter.factory;

import android.support.annotation.Nullable;

import com.janus.mvprxjavaretrofit.base.BasePresenter;


/**
 * This class represents a {@link PresenterFactory} that creates a presenter using {@link Class#newInstance()} method.
 *
 * @param <P> the type of the presenter.
 */
public class ReflectionPresenterFactory<P extends BasePresenter> implements PresenterFactory<P> {

    private Class<P> presenterClass;

    /**
     * This method returns a {@link ReflectionPresenterFactory} instance if a given view class has
     * a {@link RequiresPresenter} annotation, or null otherwise.
     *
     * @param viewClass a class of the view
     * @param <P>       a type of the presenter
     * @return a {@link ReflectionPresenterFactory} instance that is supposed to create a presenter from {@link RequiresPresenter} annotation.
     */
    @Nullable
    public static <P extends BasePresenter> ReflectionPresenterFactory<P> fromViewClass(Class<?> viewClass) {
        RequiresPresenter annotation = viewClass.getAnnotation(RequiresPresenter.class);
        //noinspection unchecked
        Class<P> presenterClass = annotation == null ? null : (Class<P>)annotation.value();
        return presenterClass == null ? null : new ReflectionPresenterFactory<>(presenterClass);
    }

    @Nullable
    public static Class<?> fromModelClass(Class<?> modelClass) {
        RequiresModel annotation = modelClass.getAnnotation(RequiresModel.class);
        //noinspection unchecked
        Class<?> modelClasss = annotation == null ? null : annotation.value();
        return modelClasss == null ? null : modelClasss;
    }

    public ReflectionPresenterFactory(Class<P> presenterClass) {
        this.presenterClass = presenterClass;
    }

    @Override
    public P createPresenter() {
        try {
            return presenterClass.newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
