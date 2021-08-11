package com.martins.valet.domain.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by policante on 7/11/16.
 */
public abstract class UseCase<T> {
    private final SubscriberOn subscriberOn;
    private final ObserveOn observeOn;
    private Subscription subscription = Subscriptions.empty();

    public UseCase(SubscriberOn subscriberOn, ObserveOn observeOn) {
        this.subscriberOn = subscriberOn;
        this.observeOn = observeOn;
    }

    protected abstract void validate();

    protected abstract Observable<T> useCaseResult();

    private Observable<T> buildUseCaseObservable(){
        try {
            validate();
            return useCaseResult();
        }catch (Exception e){
            return Observable.error(e);
        }
    }

    public void execute(Subscriber<T> subscriber){
        subscription = buildUseCaseObservable()
                .subscribeOn(subscriberOn.getScheduler())
                .observeOn(observeOn.getScheduler())
                .subscribe(subscriber);
    }

    public void unsubscribe(){
        if (!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
