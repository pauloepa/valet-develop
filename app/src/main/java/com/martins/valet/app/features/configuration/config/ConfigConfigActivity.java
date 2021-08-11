package com.martins.valet.app.features.configuration.config;

import android.content.Context;
import android.content.Intent;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.presentation.Helpers.HasComponent;

/**
 * Created by marcos on 4/9/19.
 */
public class ConfigConfigActivity extends BaseActivity implements HasComponent<ConfigConfigComponent> {

    ConfigConfigComponent component;

    public static Intent launchIntent(Context context){
        Intent intent = new Intent(context, ConfigConfigActivity.class);
        return intent;
    }

    @Override
    protected void initializeInjector() {
        component = DaggerConfigConfigComponent.builder()
                .valetApplicationComponent(getApplicationComponent())
                .valetActivityModule(getActivityModule())
                .build();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.config_config_activity;
    }

    @Override
    public ConfigConfigComponent getComponent() {
        return component;
    }
}
