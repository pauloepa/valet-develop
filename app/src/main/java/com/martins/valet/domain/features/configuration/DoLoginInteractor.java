package com.martins.valet.domain.features.configuration;

import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

public class DoLoginInteractor extends UseCase<String> {

    private final ConfigurationRepository repository;

    private String username;
    private String password;

    @Inject
    public DoLoginInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, ConfigurationRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    public void setParams(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected void validate() {
        if (this.username == null) {
            throw new IllegalArgumentException("Username is not defined");
        }
        if (this.password == null) {
            throw new IllegalArgumentException("Password is not defined");
        }
    }

    @Override
    protected Observable<String> useCaseResult() {
        return repository.doLogin(username, password);
    }

}
