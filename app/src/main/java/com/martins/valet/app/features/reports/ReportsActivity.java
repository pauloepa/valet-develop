package com.martins.valet.app.features.reports;

import android.content.Context;
import android.content.Intent;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.presentation.Helpers.HasComponent;

/**
 * Created by policante on 7/21/16.
 */
public class ReportsActivity extends BaseActivity implements HasComponent<ReportsComponent> {

    private ReportsComponent component;

    public static Intent launchIntent(Context context){
        Intent intent = new Intent(context, ReportsActivity.class);
        return intent;
    }

    @Override
    protected void initializeInjector() {
        this.component = DaggerReportsComponent.builder()
                .valetActivityModule(getActivityModule())
                .valetApplicationComponent(getApplicationComponent())
                .build();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.reports_activity;
    }

    @Override
    public ReportsComponent getComponent() {
        return this.component;
    }
}
