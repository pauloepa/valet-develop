package com.martins.valet.presentation.features.reports;

import android.widget.Toast;

import com.martins.bematechprintmanager.BematechPrint;
import com.martins.valet.Utils.Helpers.CurrencyFormatterHelper;
import com.martins.valet.Utils.Helpers.DateHelper;
import com.martins.valet.Utils.Helpers.MaskHelper;
import com.martins.valet.domain.features.model.ReportAgreement;
import com.martins.valet.domain.features.model.ReportsRevenuew;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.printer.PrinterInteractor;
import com.martins.valet.domain.features.reports.ReportsRevenuewInteractor;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by policante on 8/13/16.
 */
public class ReportsRevenuewPresenter extends BasePresenter<ReportsRevenuewView> {

    private final ReportsRevenuewInteractor revenuewInteractor;
    private final PrinterInteractor printerInteractor;

    @Inject
    public ReportsRevenuewPresenter(@Named("revenuew") UseCase revenuewInteractor, @Named("printer") UseCase printerInteractor) {
        this.revenuewInteractor = (ReportsRevenuewInteractor) revenuewInteractor;
        this.printerInteractor = (PrinterInteractor) printerInteractor;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        revenuewInteractor.unsubscribe();
        printerInteractor.unsubscribe();
    }

    public void getReports(Date in, Date out){
        showLoading();
        revenuewInteractor.setDates(in, out);
        revenuewInteractor.execute(new ReportsSubscriber());
    }

    public void printRevenue(ReportsRevenuew reports, Date dateIn, Date dateOut) {
        showLoading();
        try {
            BematechPrint print = new BematechPrint();
            print.alignCenter()
                    .withText("RELATÓRIO DE FATURAMENTO").newLine()
                    .withText("Período").newLine()
                    .withText(DateHelper.convertStringFromDateTime(dateIn))
                    .withText(" - ")
                    .withText(DateHelper.convertStringFromDateTime(dateOut))
                    .newLine()
                    .alignLeft()
                    .withText("-------------------------------").newLine()
                    .alignLeft()
                    .withText("                Aberto: " + reports.getOpen().getQuantity()).newLine()
                    .withText("                Avulso: " + reports.getAvulso().getQuantity()).newLine()
                    .withText("                Mensal: " + reports.getMensal().getQuantity()).newLine()
                    .withText("                 Total: " + reports.getGeneral().getQuantity()).newLine()
                    .alignCenter()
                    .withText("-------------------------------").newLine()
                    .withText("        Ticket inicial: " + reports.getGeneral().getValueBegin()).newLine()
                    .withText("          Ticket final: " + reports.getGeneral().getValueFinal()).newLine()
                    .alignCenter()
                    .withText("-------------------------------").newLine()
                    .alignLeft();

            String currency = CurrencyFormatterHelper.formatNumberToReal(Double.parseDouble(reports.getGeneral().getValueTotal()));
            print.withText("              Faturado: " + currency).newLine();

                    if (reports.getAgreements().size() > 0){
                        print.withText("Convenios").newLine()
                                .alignLeft();

                        for (ReportAgreement agreement: reports.getAgreements()) {
                            print.withText("      ")
                                    .withText(agreement.getAgreement())
                                    .withText(" - ")
                                    .withText(String.valueOf(agreement.getQuantity())).newLine();
                        }
                    }
                    print.alignCenter()
                        .withText("Impressão").newLine()
                            .withText(DateHelper.convertStringFromDateTime(new Date())).newLine()
                    .withPartilCut();

            printerInteractor.setPrint(print);
            printerInteractor.execute(new PrinterSubscriber());
        }catch (Exception e){
            hideLoading();
            Toast.makeText(getView().getContext(), "Falha ao imprimir\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private class ReportsSubscriber extends Subscriber<ReportsRevenuew>{

        @Override
        public void onCompleted() {
            hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideLoading();
            showError(e);
        }

        @Override
        public void onNext(ReportsRevenuew reportsRevenuew) {
            getView().onLoadSuccess(reportsRevenuew);
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

}
