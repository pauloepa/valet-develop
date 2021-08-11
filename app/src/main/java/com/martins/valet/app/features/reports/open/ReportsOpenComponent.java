package com.martins.valet.app.features.reports.open;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.Utils.UI.ValetActivityComponent;
import com.martins.valet.Utils.UI.ValetActivityModule;
import com.martins.valet.Utils.UI.ValetApplicationComponent;

import dagger.Component;

/**
 * Created by policante on 7/30/16.
 */
@PerActivity
@Component(dependencies = ValetApplicationComponent.class, modules = {ValetActivityModule.class, ReportsOpenModule.class})
public interface ReportsOpenComponent extends ValetActivityComponent{
    void inject(ReportsOpenFragment fragment);
}
