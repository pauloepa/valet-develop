package com.martins.valet.domain.interactor;

import rx.Scheduler;

/**
 * Created by policante on 7/2/16.
 */
public interface SubscriberOn {
    Scheduler getScheduler();
}
