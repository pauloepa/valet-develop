package com.martins.valet.app.features.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.app.features.splash.DaggerSplashComponent;
import com.martins.valet.presentation.Helpers.HasComponent;

/**
 * Created by policante on 7/3/16.
 */
public class LoginActivity extends BaseActivity implements HasComponent<LoginComponent> {

    private LoginComponent component;

    public static Intent launchIntent(Context context){
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void initializeInjector() {
        this.component = DaggerLoginComponent.builder()
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
        return R.layout.login_activity;
    }

    @Override
    public LoginComponent getComponent() {
        return this.component;
    }
}
