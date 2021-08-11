package com.martins.valet.Utils.UI;

import android.app.Activity;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.domain.features.transaction.TransactionRepository;

import dagger.Component;

/**
 * Created by policante on 7/2/16.
 */
@PerActivity
@Component(dependencies = ValetApplicationComponent.class, modules = ValetActivityModule.class)
public interface ValetActivityComponent {
    Activity activity();
}
