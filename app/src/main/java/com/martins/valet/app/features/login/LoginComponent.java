package com.martins.valet.app.features.login;

import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.Utils.UI.ValetActivityComponent;
import com.martins.valet.Utils.UI.ValetActivityModule;
import com.martins.valet.Utils.UI.ValetApplicationComponent;

import dagger.Component;

/**
 * Created by policante on 7/3/16.
 */
@PerActivity
@Component(dependencies = ValetApplicationComponent.class, modules = {ValetActivityModule.class, LoginModule.class})
public interface LoginComponent extends ValetActivityComponent{
    void inject(LoginFragment fragment);
}
