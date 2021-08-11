package com.martins.valet.presentation.features.reports;

import android.widget.Toast;

import com.martins.bematechprintmanager.BematechPrint;
import com.martins.valet.Utils.Helpers.CurrencyFormatterHelper;
import com.martins.valet.Utils.Helpers.DateHelper;
import com.martins.valet.Utils.Helpers.MaskHelper;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.printer.PrinterInteractor;
import com.martins.valet.domain.features.reports.ReportsOpenInteractor;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by policante on 7/31/16.
 */
public class ReportsOpenPresenter extends BasePresenter<ReportsOpenView> {

    private final PrinterInteractor printerInteractor;
    private final ReportsOpenInteractor openInteractor;

    @Inject
    public ReportsOpenPresenter(@Named("reportsOpen") UseCase openInteractor, @Named("printer") UseCase printerInteractor) {
        this.printerInteractor = (PrinterInteractor) printerInteractor;
        this.openInteractor = (ReportsOpenInteractor) openInteractor;
    }

    @Override
    public void resume() {
        showLoading();
        loadTransactionOpen();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        printerInteractor.unsubscribe();
        openInteractor.unsubscribe();
    }

    public void loadTransactionOpen() {
        openInteractor.execute(new ReportsOpenSubscriber());
    }

    public void printerOpenTransactions(List<Transaction> list) {
        showLoading();
        try {
            BematechPrint print = new BematechPrint();

            print.alignCenter().withText("RELATÓRIO DE PÁTIO").newLine().newLine();

            String currency;
            float total = 0;

            for (Transaction model : list) {
                total += model.getValueTotal();
                currency = CurrencyFormatterHelper.formatNumberToReal(model.getValueTotal());

                print.alignCenter()
                        .withText("---------------------------------------").newLine().alignLeft()
                        .withText("             Ticket: ").withTextBold(model.getIdentifierWithSpace()).newLine()
                        .withText("              Placa: ").withTextBold(MaskHelper.plateMask(model.getVehycle().getPlate())).newLine()
                        .withText("              Marca: ").withTextBold(model.getVehycle().getBrand()).newLine()
                        .withText("                Cor: ").withTextBold(model.getVehycle().getColor()).newLine()
                        .withText("               Tipo: ").withTextBold(model.getVehycle().getType()).newLine()
                        .withText("            Entrada: ").withText(DateHelper.convertStringFromDateTime(model.getTransactionIn())).newLine()
                        .withText("            Faturar: ").withTextBold(currency).newLine();
            }

            print.alignCenter()
                    .withText("---------------------------------------").newLine().alignLeft()
                    .withText("               Total: ").withTextBold(CurrencyFormatterHelper.formatNumberToReal(total)).newLine()
                    .withText("           Impressão: ").withText(DateHelper.convertStringFromDateTime(new Date())).newLine()
            .withFullCut();

            printerInteractor.setPrint(print);
            printerInteractor.execute(new PrinterSubscriber());

        }catch (Exception e){
            hideLoading();
            Toast.makeText(getView().getContext(), "Falha ao imprimir\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void printerTicket(Transaction model) {
        showLoading();
        try{
            BematechPrint print = new BematechPrint();

            print.alignCenter()
                    .sizeLarge().withTextBold(model.getIdentifierWithSpace()).newLine().sizeSmall()
                    .alignLeft()
                    .withText("                 Placa: ").withTextBold(MaskHelper.plateMask(model.getVehycle().getPlate())).newLine()
                    .withText("                  Tipo: " + model.getVehycle().getType()).newLine()
                    .withText("                 Marca: " + model.getVehycle().getBrand()).newLine()
                    .withText("                   Cor: " + model.getVehycle().getColor()).newLine();

            printerInteractor.setPrint(print);
            printerInteractor.execute(new PrinterSubscriber());

        }catch (Exception e){
            hideLoading();
            Toast.makeText(getView().getContext(), "Falha ao imprimir\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private class PrinterSubscriber extends Subscriber<Boolean>{

        @Override
        public void onCompleted() {
            hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideLoading();
            Toast.makeText(getView().getContext(), "Falha ao imprimir\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        @Override
        public void onNext(Boolean aBoolean) {
            Toast.makeText(getView().getContext(), "Impressão enviada com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    private class ReportsOpenSubscriber extends Subscriber<List<Transaction>>{

        @Override
        public void onCompleted() {
            getView().hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            getView().hideLoading();
            getView().showError(e);
        }

        @Override
        public void onNext(List<Transaction> transactions) {
            getView().onSuccessList(transactions);
        }
    }
}
