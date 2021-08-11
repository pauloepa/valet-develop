package com.martins.valet.app.features.reports.revenuew;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.Utils.UI.ValetActivityComponent;
import com.martins.valet.Utils.UI.ValetActivityModule;
import com.martins.valet.Utils.UI.ValetApplicationComponent;

import dagger.Component;

/**
 * Created by policante on 8/13/16.
 */
@PerActivity
@Component(dependencies = ValetApplicationComponent.class, modules = {ValetActivityModule.class, ReportsRevenuewModule.class})
public interface ReportsRevenuewComponent extends ValetActivityComponent {
    void inject(ReportsRevenuewFragment fragment);
}
