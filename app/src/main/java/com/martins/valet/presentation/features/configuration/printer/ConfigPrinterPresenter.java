package com.martins.valet.presentation.features.configuration.printer;

import com.martins.bematechprintmanager.BematechPrint;
import com.martins.valet.domain.features.configuration.SaveConfigurationInteractor;
import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.domain.features.printer.PrinterInteractor;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by policante on 7/18/16.
 */
public class ConfigPrinterPresenter extends BasePresenter<ConfigPrinterView> {

    private final PrinterInteractor printerInteractor;
    private final SaveConfigurationInteractor saveConfigurationInteractor;

    @Inject
    public ConfigPrinterPresenter(@Named("printer") UseCase printerInteractor, @Named("saveConfig") UseCase saveConfigurationInteractor) {
        this.saveConfigurationInteractor = (SaveConfigurationInteractor) saveConfigurationInteractor;
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
        saveConfigurationInteractor.unsubscribe();
        printerInteractor.unsubscribe();
    }

    public void printTest(String host) {
        showLoading();
        printerInteractor.setPrint(getPrintTest(), host);
        printerInteractor.execute(new PrintSubscriber());
    }

    private BematechPrint getPrintTest() {
        return new BematechPrint().alignCenter()
                .withText("TESTE DE IMPRESSÃO").newLine()
                .withFullCut();
    }

    public void savePrinterSetup(String host) {
        showLoading();
        Configuration configuration = new Configuration(getConfigManager().getConfiguration());
        configuration.setPrinterHost(host);
        saveConfigurationInteractor.setConfiguration(configuration);
        saveConfigurationInteractor.execute(new SaveConfigurationSubscriber());
    }

    private class SaveConfigurationSubscriber extends Subscriber<Configuration> {

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
        public void onNext(Configuration configuration) {
            getConfigManager().setConfiguration(configuration);
            getView().onSaveSuccess();
        }
    }

    private class PrintSubscriber extends Subscriber<Boolean> {
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
        public void onNext(Boolean aBoolean) {
            getView().onPrintSuccess();
        }
    }
}
