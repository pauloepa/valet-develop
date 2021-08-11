package com.martins.valet.presentation.features.splash;

import com.martins.valet.Utils.Helpers.Preferences;
import com.martins.valet.domain.features.configuration.SyncInteractor;
import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.domain.features.splash.LoadConfigInteractor;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by policante on 7/18/16.
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    private final LoadConfigInteractor configInteractor;
    private final SyncInteractor syncInteractor;

    @Inject
    public SplashPresenter(@Named("loadConfig") UseCase configInteractor, @Named("sync") UseCase syncInteractor) {
        this.configInteractor = (LoadConfigInteractor) configInteractor;
        this.syncInteractor = (SyncInteractor) syncInteractor;
    }

    @Override
    public void resume() {
        loadConfig();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        configInteractor.unsubscribe();
        syncInteractor.unsubscribe();
    }

    private void loadConfig() {
        showLoading();
        configInteractor.execute(new LoadConfigSubscriber());
    }

    private void sync() {
        syncInteractor.execute(new SyncSubscriber());
    }

    private class SyncSubscriber extends Subscriber<Boolean> {

        @Override
        public void onCompleted() {
            if (getView() != null) {
                getView().configComplete();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (getView() != null) {
                getView().configComplete();
            }
        }

        @Override
        public void onNext(Boolean aBoolean) {

        }
    }

    private class LoadConfigSubscriber extends Subscriber<Configuration> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (getView() != null) {
                getView().configComplete();
            }
        }

        @Override
        public void onNext(Configuration configuration) {
            if (getView() != null) {
                if ((configuration == null) || configuration.getDocument() == null || configuration.getDocument().isEmpty()) {
                    Preferences prefs = new Preferences(getView().getContext());
                    prefs.load();

                    if (configuration == null) {
                        configuration = new Configuration();
                    }

                    configuration.setCompany(prefs.getCompany());
                    configuration.setDocument(prefs.getDocument());
                    configuration.setAddress(prefs.getAddress());
                    configuration.setPhone(prefs.getPhone());
                    configuration.setObs(prefs.getObservation());

                }

                getConfigManager().setConfiguration(configuration);

                if (configuration.getUserToken() != null) {
                    sync();
                } else {
                    getView().configComplete();
                }
            }
        }
    }
}
