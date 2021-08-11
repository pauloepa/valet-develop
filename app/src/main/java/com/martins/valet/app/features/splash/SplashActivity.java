package com.martins.valet.app.features.splash;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.presentation.Helpers.HasComponent;

/**
 * Created by policante on 7/3/16.
 */
public class SplashActivity extends BaseActivity implements HasComponent<SplashComponent> {

    private SplashComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void initializeInjector() {
        this.component = DaggerSplashComponent.builder()
                .valetApplicationComponent(getApplicationComponent())
                .valetActivityModule(getActivityModule())
                .build();
    }

    @Override
    protected boolean displayHome() {
        return false;
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.splash_activity;
    }

    @Override
    public SplashComponent getComponent() {
        return this.component;
    }
}
