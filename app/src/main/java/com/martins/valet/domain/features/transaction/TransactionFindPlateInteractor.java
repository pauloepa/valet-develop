package com.martins.valet.domain.features.transaction;

import android.text.TextUtils;

import com.martins.valet.domain.features.model.VehycleInfo;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 7/15/16.
 */
public class TransactionFindPlateInteractor extends UseCase<VehycleInfo> {

    private final TransactionRepository repository;

    private String plate;

    @Inject
    public TransactionFindPlateInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, TransactionRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    protected void validate() {
        if (TextUtils.isEmpty(plate)) {
            throw new IllegalArgumentException("Plate is not defined");
        }

        if (plate.length() != 7) {
            throw new IllegalArgumentException("Invalid plate");
        }
    }

    @Override
    protected Observable<VehycleInfo> useCaseResult() {
        return repository.findVehycleInfoFromPlate(this.plate);
    }
}
