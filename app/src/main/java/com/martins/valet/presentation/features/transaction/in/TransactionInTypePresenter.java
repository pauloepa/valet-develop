package com.martins.valet.presentation.features.transaction.in;

import com.martins.valet.data.features.entity.disk.TypeVehycleRealm;
import com.martins.valet.domain.features.model.TypeVehycle;
import com.martins.valet.domain.features.transaction.TransactionListTypeInteractor;
import com.martins.valet.domain.interactor.UseCase;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.Helpers.BaseView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by policante on 7/16/16.
 */
public class TransactionInTypePresenter extends BasePresenter<TypeVehyclesView> {

    private final TransactionListTypeInteractor typeInteractor;

    @Inject public TransactionInTypePresenter(@Named("type") UseCase typeInteractor) {
        this.typeInteractor = (TransactionListTypeInteractor) typeInteractor;
    }

    @Override
    public void resume() {
        loadType();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.typeInteractor.unsubscribe();
    }

    private void loadType(){
        showLoading();
        this.typeInteractor.execute(new ListTypeSubscriber());
    }

    private class ListTypeSubscriber extends Subscriber<List<TypeVehycle>>{

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
        public void onNext(List<TypeVehycle> type) {
            getView().onSuccessLoadType(type);
        }
    }
}
