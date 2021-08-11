package com.martins.valet.app.features.configuration;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.presentation.Helpers.HasComponent;

/**
 * Created by policante on 7/16/16.
 */
public class ConfigurationActivity extends BaseActivity implements HasComponent<ConfigurationComponent> {

    ConfigurationComponent component;

    public static Intent launchIntent(Context context){
        Intent intent = new Intent(context, ConfigurationActivity.class);
        return intent;
    }

    @Override
    protected void initializeInjector() {
        this.component = DaggerConfigurationComponent.builder()
                .valetActivityModule(getActivityModule())
                .valetApplicationComponent(getApplicationComponent())
                .build();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.config_actvity;
    }

    @Override
    public ConfigurationComponent getComponent() {
        return this.component;
    }

    @Override
    protected String getToolbarTitle() {
        return "Configurações";
    }
}
