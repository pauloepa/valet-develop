package com.martins.valet.domain.features.reports;

import com.martins.valet.domain.features.model.ReportsRevenuew;
import com.martins.valet.domain.features.transaction.TransactionRepository;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;

import java.util.Date;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 8/13/16.
 */
public class ReportsRevenuewInteractor extends UseCase<ReportsRevenuew> {

    private final TransactionRepository repository;

    private Date dateIn;
    private Date dateOut;

    @Inject public ReportsRevenuewInteractor(SubscriberOn subscriberOn, ObserveOn observeOn, TransactionRepository repository) {
        super(subscriberOn, observeOn);
        this.repository = repository;
    }

    public void setDates(Date in, Date out){
        this.dateIn = in;
        this.dateOut = out;
    }

    @Override
    protected void validate() {
        if (this.dateIn == null || this.dateOut == null){
            throw new IllegalArgumentException("Date is invalid");
        }
    }

    @Override
    protected Observable<ReportsRevenuew> useCaseResult() {
        return repository.getReportsRevenuew(this.dateIn, this.dateOut);
    }
}
