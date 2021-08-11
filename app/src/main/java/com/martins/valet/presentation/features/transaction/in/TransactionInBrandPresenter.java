package com.martins.valet.presentation.features.transaction.in;

import com.martins.valet.data.features.entity.disk.BrandVehycleRealm;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.transaction.TransactionListBrandsInteractor;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by policante on 7/16/16.
 */
public class TransactionInBrandPresenter extends BasePresenter<BrandView> {

    private final TransactionListBrandsInteractor brandsInteractor;


    @Inject public TransactionInBrandPresenter(@Named("brands") UseCase brandsInteractor) {
        this.brandsInteractor = (TransactionListBrandsInteractor) brandsInteractor;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.brandsInteractor.unsubscribe();
    }

    public void loadBrands(int typeId){
        showLoading();
        this.brandsInteractor.setTypeId(typeId);
        this.brandsInteractor.execute(new BrandSubscriber());
    }

    private class BrandSubscriber extends Subscriber<List<BrandVehycle>>{

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
        public void onNext(List<BrandVehycle> brands) {
            getView().onSuccessLoadBrand(brands);
        }
    }
}
