package com.martins.valet.presentation.features.transaction.in;

import com.martins.valet.domain.features.model.Vehycle;
import com.martins.valet.domain.features.model.VehycleInfo;
import com.martins.valet.domain.features.transaction.TransactionFindPlateInteractor;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by policante on 7/15/16.
 */
public class TransactionInPlatePresenter extends BasePresenter<PlateView> {

    private final TransactionFindPlateInteractor findPlateInteractor;

    @Inject
    public TransactionInPlatePresenter(@Named("findPlate") UseCase findPlateInteractor) {
        this.findPlateInteractor = (TransactionFindPlateInteractor) findPlateInteractor;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        findPlateInteractor.unsubscribe();
    }

    public void findVehycle(String plate) {
        showLoading();
        if (plate.trim().equals("")) {
            getView().vehycleInfoNotFound();
            return;
        }
        findPlateInteractor.setPlate(plate);
        findPlateInteractor.execute(new VehycleInfoSubscriber());
    }

    private class VehycleInfoSubscriber extends Subscriber<VehycleInfo> {

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
        public void onNext(VehycleInfo vehycleInfo) {
            if (vehycleInfo.getVehycle() == null){
                getView().vehycleInfoNotFound();
            }else{
                getView().vehycleInfoSuccess(vehycleInfo);
            }
        }
    }

}
