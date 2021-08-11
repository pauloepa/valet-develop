package com.martins.valet.presentation.features.login;

import com.martins.valet.domain.features.configuration.DoLoginInteractor;
import com.martins.valet.domain.features.configuration.SaveConfigurationInteractor;
import com.martins.valet.domain.features.configuration.SyncInteractor;
import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by policante on 7/18/16.
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private final SaveConfigurationInteractor saveConfigInteractor;
    private final DoLoginInteractor doLoginInteractor;
    private final SyncInteractor syncInteractor;

    @Inject
    public LoginPresenter(@Named("saveConfig") UseCase saveConfigInteractor,
                          @Named("doLogin") UseCase loginInteractor,
                          @Named("sync") UseCase syncInteractor) {
        this.saveConfigInteractor = (SaveConfigurationInteractor) saveConfigInteractor;
        this.doLoginInteractor = (DoLoginInteractor) loginInteractor;
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
        saveConfigInteractor.unsubscribe();
        doLoginInteractor.unsubscribe();
        syncInteractor.unsubscribe();
    }

    public void doLogin(String usernameStr, String passwordStr) {
        showLoading();
        doLoginInteractor.setParams(usernameStr, passwordStr);
        doLoginInteractor.execute(new LoginSubscriber());
    }

    private void saveUserToken(String token) {
        Configuration configuration = new Configuration(getConfigManager().getConfiguration());
        configuration.setUserToken(token);
        saveConfigInteractor.setConfiguration(configuration);
        saveConfigInteractor.execute(new SaveConfigurationSubscriber());
    }

    private void sync() {
        syncInteractor.execute(new SyncSubscriber());
    }

    private class SyncSubscriber extends Subscriber<Boolean> {

        @Override
        public void onCompleted() {
            hideLoading();
            getView().onLoginSucceed();
        }

        @Override
        public void onError(Throwable e) {
            hideLoading();
            getView().onLoginSucceed();
        }

        @Override
        public void onNext(Boolean aBoolean) {

        }
    }

    private class LoginSubscriber extends Subscriber<String> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            hideLoading();
            getView().showError(e);
        }

        @Override
        public void onNext(String token) {
            saveUserToken(token);
        }
    }

    private class SaveConfigurationSubscriber extends Subscriber<Configuration> {

        @Override
        public void onCompleted() {
            sync();
        }

        @Override
        public void onError(Throwable e) {
            hideLoading();
            showError(e);
        }

        @Override
        public void onNext(Configuration configuration) {
            getConfigManager().setConfiguration(configuration);
        }
    }

}
