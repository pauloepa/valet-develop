package com.martins.valet.app.features.reports.open;

import android.content.Context;
import android.content.Intent;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.app.features.reports.ReportsComponent;
import com.martins.valet.presentation.Helpers.HasComponent;

/**
 * Created by policante on 7/30/16.
 */
public class ReportsOpenActivity extends BaseActivity implements HasComponent<ReportsOpenComponent> {

    ReportsOpenComponent component;

    public static Intent launchIntent(Context context) {
        Intent intent = new Intent(context, ReportsOpenActivity.class);
        return intent;
    }

    @Override
    protected void initializeInjector() {
        this.component = DaggerReportsOpenComponent.builder()
                .valetActivityModule(getActivityModule())
                .valetApplicationComponent(getApplicationComponent())
                .build();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.reports_open_activity;
    }

    @Override
    public ReportsOpenComponent getComponent() {
        return this.component;
    }

}
