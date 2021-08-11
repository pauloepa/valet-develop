package com.martins.valet.app.features.home;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.Utils.UI.ValetActivityComponent;
import com.martins.valet.Utils.UI.ValetActivityModule;
import com.martins.valet.Utils.UI.ValetApplicationComponent;

import dagger.Component;

/**
 * Created by policante on 7/5/16.
 */
@PerActivity
@Component(dependencies = ValetApplicationComponent.class, modules = {ValetActivityModule.class, HomeModule.class})
public interface HomeComponent extends ValetActivityComponent {
    void inject(HomeFragment fragment);
}
