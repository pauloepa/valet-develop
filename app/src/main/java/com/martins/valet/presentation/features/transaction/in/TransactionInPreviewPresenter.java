package com.martins.valet.presentation.features.transaction.in;

import android.widget.Toast;

import com.martins.bematechprintmanager.BematechPrint;
import com.martins.valet.R;
import com.martins.valet.Utils.Helpers.DateHelper;
import com.martins.valet.Utils.Helpers.MaskHelper;
import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.Utils.Helpers.Preferences;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.model.VehycleInfo;
import com.martins.valet.domain.features.printer.PrinterInteractor;
import com.martins.valet.domain.features.transaction.TranscationSaveInteractor;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by policante on 7/11/16.
 */
@PerActivity
public class TransactionInPreviewPresenter extends BasePresenter<TransactionView> {

    private final SubscriberOn subscriberOn;
    private final ObserveOn observeOn;

    private final TranscationSaveInteractor saveInteractor;
    private final PrinterInteractor printerInteractor;

    @Inject
    public TransactionInPreviewPresenter(@Named("saveTransaction") UseCase saveInteractor,
                                         @Named("printer") UseCase printInteractor,
                                         SubscriberOn subscriberOn, ObserveOn observeOn) {
        this.subscriberOn = subscriberOn;
        this.observeOn = observeOn;
        this.printerInteractor = (PrinterInteractor) printInteractor;
        this.saveInteractor = (TranscationSaveInteractor) saveInteractor;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        saveInteractor.unsubscribe();
        printerInteractor.unsubscribe();
    }

    public void createTransaction(VehycleInfo vehycleInfo) {
        showLoading();

        Transaction transaction = new Transaction();
        transaction.setVehycle(vehycleInfo.getVehycle());
        transaction.setClient(vehycleInfo.getClient());
        transaction.setTransactionIn(new Date());

        saveInteractor.setTransaction(transaction);
        saveInteractor.execute(new SaveSubscriber(false));
    }

    public void closeTransactionWithTicket(VehycleInfo vehycleInfo) {
        showLoading();

        Transaction transaction = new Transaction();
        transaction.setVehycle(vehycleInfo.getVehycle());
        transaction.setClient(vehycleInfo.getClient());
        transaction.setTransactionIn(new Date());

        saveInteractor.setTransaction(transaction);
        saveInteractor.execute(new SaveSubscriber(true));
    }

    private void printTicket(Transaction model, boolean ticket) {
        Preferences prefs = new Preferences(getView().getContext());
        Toast.makeText(getView().getContext(), "Enviando dados para impressora", Toast.LENGTH_SHORT).show();

        showLoading();
        BematechPrint print = new BematechPrint();

        print.alignCenter()
                .withText(prefs.getCompany()).newLine()
                .withText(prefs.getDocument()).newLine()
                .withText(prefs.getAddress()).newLine().newLine()
                .withText("TICKET: ")
                .sizeLarge()
                .withTextBold(model.getIdentifierWithSpace());

        if (ticket){
            print.withText(" #");
        }

        print.newLine().sizeSmall()
                .withText("-------------------------------").newLine()
                .alignLeft()
                .withText("                 Placa: ").withTextBold(MaskHelper.plateMask(model.getVehycle().getPlate())).newLine()
                .withText("                  Tipo: " + model.getVehycle().getType()).newLine()
                .withText("                 Marca: " + model.getVehycle().getBrand()).newLine()
                .withText("                   Cor: " + model.getVehycle().getColor()).newLine()
                .alignCenter()
                .withText("-------------------------------").newLine()
                .alignLeft()
                .withText("           Entrada: ");
        try {
            print.withText(DateHelper.convertStringFromDateTime(model.getTransactionIn())).newLine();
        } catch (Exception e) {
            //
        }
        print.alignCenter().withText("-------------------------------").newLine()
                .withText(prefs.getPhone()).newLine()
                .withText(prefs.getObservation()).newLine();

        if (ticket){
            print.withPartilCut();
            print.alignCenter()
                    .sizeLarge().withTextBold(model.getIdentifierWithSpace()).newLine().sizeSmall()
                    .alignLeft()
                    .withText("                 Placa: ").withTextBold(MaskHelper.plateMask(model.getVehycle().getPlate())).newLine()
                    .withText("                  Tipo: " + model.getVehycle().getType()).newLine()
                    .withText("                 Marca: " + model.getVehycle().getBrand()).newLine()
                    .withText("                   Cor: " + model.getVehycle().getColor()).newLine();
        }

        print.withFullCut();

        printerInteractor.setPrint(print);
        printerInteractor.execute(new PrintSubscriber());
    }

    private class PrintSubscriber extends Subscriber<Boolean> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(getView().getContext(), "Falha ao imprimir\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        @Override
        public void onNext(Boolean aBoolean) {
            //
        }
    }

    private class SaveSubscriber extends Subscriber<Transaction> {

        private boolean printTicket;

        public SaveSubscriber(boolean printTicket) {
            this.printTicket = printTicket;
        }

        @Override
        public void onCompleted() {
            hideLoading();
            getView().onSaveSuccess();
        }

        @Override
        public void onError(Throwable e) {
            hideLoading();
            showError(e);
        }

        @Override
        public void onNext(Transaction transaction) {
            if (transaction != null) {
                printTicket(transaction, printTicket);
            }
        }
    }

}
