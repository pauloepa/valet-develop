package com.martins.valet.app.features.configuration;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.Utils.UI.ValetActivityComponent;
import com.martins.valet.Utils.UI.ValetActivityModule;
import com.martins.valet.Utils.UI.ValetApplicationComponent;

import dagger.Component;

/**
 * Created by policante on 7/16/16.
 */
@PerActivity
@Component(dependencies = ValetApplicationComponent.class, modules = {ValetActivityModule.class, ConfigurationModule.class})
public interface ConfigurationComponent extends ValetActivityComponent{
    void inject(ConfigurationFragment fragment);
}
