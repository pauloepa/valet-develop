package com.martins.valet.Utils.UI;

import android.content.Context;

import com.martins.valet.Utils.Helpers.Navigator;
import com.martins.valet.ValetApplication;
import com.martins.valet.domain.features.configuration.ConfigurationRepository;
import com.martins.valet.domain.features.transaction.TransactionRepository;
import com.martins.valet.domain.interactor.ObserveOn;
import com.martins.valet.domain.interactor.SubscriberOn;
import com.martins.valet.presentation.features.configuration.ConfigManager;
import com.martins.valet.presentation.features.home.HomeMenuManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by policante on 7/2/16.
 */
@Singleton
@Component(modules = ValetApplicationModule.class)
public interface ValetApplicationComponent {
    Context context();
    ObserveOn observeOn();
    SubscriberOn subscriberOn();
    Navigator navigator();
    HomeMenuManager homeMenuManager();
    ConfigManager configManager();

    TransactionRepository repository();
    ConfigurationRepository configurationRepository();

    void inject(ValetApplication valetApplication);
    void inject(BaseActivity activity);
    void inject(BaseFragment fragment);
}
