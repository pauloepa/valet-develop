package com.martins.valet.app.features.configuration.printer;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.Utils.UI.ValetActivityComponent;
import com.martins.valet.Utils.UI.ValetActivityModule;
import com.martins.valet.Utils.UI.ValetApplicationComponent;
import com.martins.valet.Utils.UI.ValetApplicationModule;

import dagger.Component;

/**
 * Created by policante on 7/18/16.
 */
@PerActivity
@Component(dependencies = ValetApplicationComponent.class, modules = {ValetActivityModule.class, ConfigPrinterModule.class})
public interface ConfigPrinterComponent extends ValetActivityComponent{
    void inject(ConfigPrinterFragment fragment);
}
