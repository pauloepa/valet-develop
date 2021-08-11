package com.martins.valet.app.features.transaction;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.Utils.UI.ValetActivityComponent;
import com.martins.valet.Utils.UI.ValetActivityModule;
import com.martins.valet.Utils.UI.ValetApplicationComponent;
import com.martins.valet.app.features.transaction.in.TransactionInBrandFragment;
import com.martins.valet.app.features.transaction.in.TransactionInColorFragment;
import com.martins.valet.app.features.transaction.in.TransactionInPlateFragment;
import com.martins.valet.app.features.transaction.in.TransactionInPreviewFragment;
import com.martins.valet.app.features.transaction.in.TransactionInTypeFragment;
import com.martins.valet.app.features.transaction.out.TransactionOutAgreementFragment;
import com.martins.valet.app.features.transaction.out.TransactionOutPlateFragment;
import com.martins.valet.app.features.transaction.out.TransactionOutPreviewFragment;
import com.martins.valet.app.features.transaction.out.TransactionOutTicketFragment;

import dagger.Component;

/**
 * Created by policante on 7/7/16.
 */
@PerActivity
@Component(dependencies = ValetApplicationComponent.class, modules = {ValetActivityModule.class, TransactionModule.class})
public interface TransactionComponent extends ValetActivityComponent{

//  IN
    void inject(TransactionInPlateFragment fragment);
    void inject(TransactionInTypeFragment fragment);
    void inject(TransactionInBrandFragment fragment);
    void inject(TransactionInColorFragment fragment);
    void inject(TransactionInPreviewFragment fragment);

//  OUT
    void inject(TransactionOutPlateFragment fragment);
    void inject(TransactionOutPreviewFragment fragment);
    void inject(TransactionOutTicketFragment fragment);

    void inject(TransactionOutAgreementFragment fragment);
}
