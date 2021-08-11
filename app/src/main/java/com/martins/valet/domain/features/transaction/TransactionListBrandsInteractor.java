package com.martins.valet.domain.features.transaction;

import com.martins.valet.data.features.entity.disk.BrandVehycleRealm;
import com.martins.valet.domain.features.configuration.ConfigurationRepository;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/16/16.
 */
public class TransactionListBrandsInteractor extends UseCase<List<BrandVehycle>> {

    private final ConfigurationRepository repository;

    private int typeId;

    @Inject public TransactionListBrandsInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, ConfigurationRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    protected void validate() {
        if (typeId <= 0 ){
            throw new IllegalArgumentException("Type id is invalid");
        }
    }

    @Override
    protected Observable<List<BrandVehycle>> useCaseResult() {
        return repository.getAllBrands(this.typeId);
    }
}
