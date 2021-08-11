package com.martins.valet.domain.features.configuration;

import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/18/16.
 */
public class SaveConfigurationInteractor extends UseCase<Configuration> {

    private final ConfigurationRepository repository;

    private Configuration configuration;

    @Inject public SaveConfigurationInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, ConfigurationRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void validate() {
        if (this.configuration == null){
            throw new IllegalArgumentException("Configuration is not defined");
        }
    }

    @Override
    protected Observable<Configuration> useCaseResult() {
        return repository.saveConfiguration(this.configuration);
    }
}
