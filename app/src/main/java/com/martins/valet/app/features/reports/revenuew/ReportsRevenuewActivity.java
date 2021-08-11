package com.martins.valet.app.features.reports.revenuew;

import android.content.Context;
import android.content.Intent;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.presentation.Helpers.HasComponent;

/**
 * Created by policante on 8/13/16.
 */
public class ReportsRevenuewActivity extends BaseActivity implements HasComponent<ReportsRevenuewComponent> {

    private ReportsRevenuewComponent component;

    public static Intent launchIntent(Context context){
        Intent intent = new Intent(context, ReportsRevenuewActivity.class);
        return intent;
    }

    @Override
    protected void initializeInjector() {
        this.component = DaggerReportsRevenuewComponent.builder()
                .valetApplicationComponent(getApplicationComponent())
                .valetActivityModule(getActivityModule())
                .build();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.reports_revenue_activity;
    }

    @Override
    public ReportsRevenuewComponent getComponent() {
        return this.component;
    }
}
