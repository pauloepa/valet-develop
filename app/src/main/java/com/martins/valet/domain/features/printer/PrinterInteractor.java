package com.martins.valet.domain.features.printer;

import com.martins.bematechprintmanager.BematechManager;
import com.martins.bematechprintmanager.BematechPrint;
import com.martins.valet.Utils.Helpers.DateHelper;
import com.martins.valet.Utils.Helpers.MaskHelper;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.features.configuration.ConfigManager;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by policante on 7/16/16.
 */
public class PrinterInteractor extends UseCase<Boolean> {

    private BematechPrint print;
    private String host;

    @Inject
    ConfigManager configManager;

    @Inject public PrinterInteractor(SubscriberOn subscriberOn, ObserveOn observeOn) {
        super(subscriberOn, observeOn);
    }

    public void setPrint(BematechPrint print, String host) {
        this.print = print;
        this.host = host;
    }

    public void setPrint(BematechPrint print) {
        this.print = print;
        if (configManager.getConfiguration() != null && configManager.getConfiguration().getPrinterHost() != null) {
            this.host = configManager.getConfiguration().getPrinterHost();
        }else{
            this.host = null;
        }
    }

    @Override
    protected void validate() {
        if (print == null){
            throw new IllegalArgumentException("Print is not defined");
        }
        if (this.host == null) {
            throw new IllegalArgumentException("Impressora não encontrada ou cadastrada");
        }
    }

    @Override
    protected Observable<Boolean> useCaseResult() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    new BematechManager(host, 9100).send(print);
                    subscriber.onNext(true);
                }catch (Exception e){
                    subscriber.onError(new Exception("Não foi possível imprimir"));
                }finally {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
