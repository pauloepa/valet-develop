package com.martins.valet.app.features.configuration.printer;

import android.content.Context;
import android.content.Intent;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.presentation.Helpers.HasComponent;

/**
 * Created by policante on 7/18/16.
 */
public class ConfigPrinterActivity extends BaseActivity implements HasComponent<ConfigPrinterComponent> {

    ConfigPrinterComponent component;

    public static Intent launchIntent(Context context){
        Intent intent = new Intent(context, ConfigPrinterActivity.class);
        return intent;
    }

    @Override
    protected void initializeInjector() {
        component = DaggerConfigPrinterComponent.builder()
                .valetApplicationComponent(getApplicationComponent())
                .valetActivityModule(getActivityModule())
                .build();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.config_printer_activity;
    }

    @Override
    public ConfigPrinterComponent getComponent() {
        return component;
    }
}
