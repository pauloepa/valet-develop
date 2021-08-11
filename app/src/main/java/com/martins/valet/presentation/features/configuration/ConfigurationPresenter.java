package com.martins.valet.presentation.features.configuration;

import com.martins.valet.domain.features.configuration.SyncInteractor;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by policante on 7/16/16.
 */
public class ConfigurationPresenter extends BasePresenter<ConfigurationView>{

    private final SyncInteractor syncInteractor;

    @Inject public ConfigurationPresenter(@Named("sync") UseCase syncInteractor) {
        this.syncInteractor = (SyncInteractor) syncInteractor;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        syncInteractor.unsubscribe();
    }

    public void sync() {
        showLoading();
        syncInteractor.execute(new SyncSubscriber());
    }

    private class SyncSubscriber extends Subscriber<Boolean>{

        @Override
        public void onCompleted() {
            hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideLoading();
            showError(e);
        }

        @Override
        public void onNext(Boolean aBoolean) {
            getView().syncComplete();
        }
    }
}
