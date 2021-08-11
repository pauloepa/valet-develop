package com.martins.valet.app.features.configuration.config;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.Utils.UI.ValetActivityComponent;
import com.martins.valet.Utils.UI.ValetActivityModule;
import com.martins.valet.Utils.UI.ValetApplicationComponent;

import dagger.Component;

/**
 * Created by policante on 7/18/16.
 */
@PerActivity
@Component(dependencies = ValetApplicationComponent.class, modules = {ValetActivityModule.class, ConfigConfigModule.class})
public interface ConfigConfigComponent extends ValetActivityComponent{
    void inject(ConfigConfigFragment fragment);
}
