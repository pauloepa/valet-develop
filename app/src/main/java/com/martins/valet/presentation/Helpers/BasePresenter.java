package com.martins.valet.presentation.Helpers;

import androidx.annotation.StringRes;

import com.martins.valet.presentation.features.configuration.ConfigManager;

import javax.inject.Inject;

/**
 * Created by policante on 7/3/16.
 */
public abstract class BasePresenter<V extends BaseView> implements LoadPresenter, ErrorPresenter{

    @Inject
    ConfigManager configManager;

    private V view;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    protected String getString(@StringRes int resId) {
        return getView().getContext().getString(resId);
    }

    protected String getString(@StringRes int resId, Object... formatArgs) {
        return getView().getContext().getString(resId, formatArgs);
    }

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();

    @Override
    public void showLoading() {
        getView().showLoading();
    }

    @Override
    public void hideLoading() {
        getView().hideLoading();
    }

    @Override
    public void showError(Throwable e) {
        getView().showError(e);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }
}
