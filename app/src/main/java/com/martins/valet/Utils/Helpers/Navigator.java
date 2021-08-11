package com.martins.valet.Utils.Helpers;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.martins.valet.app.features.configuration.ConfigurationActivity;
import com.martins.valet.app.features.configuration.config.ConfigConfigActivity;
import com.martins.valet.app.features.configuration.printer.ConfigPrinterActivity;
import com.martins.valet.app.features.home.HomeActivity;
import com.martins.valet.app.features.login.LoginActivity;
import com.martins.valet.app.features.reports.ReportsActivity;
import com.martins.valet.app.features.reports.open.ReportsOpenActivity;
import com.martins.valet.app.features.reports.revenuew.ReportsRevenuewActivity;
import com.martins.valet.app.features.transaction.TransactionActivity;
import com.martins.valet.domain.features.model.Transaction;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by policante on 7/2/16.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void toHome(@NonNull Context context) {
        Intent intent = HomeActivity.launchIntent(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void toLogin(@NonNull Context context) {
        Intent intent = LoginActivity.launchIntent(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void toTransactionIn(@NonNull Context context) {
        Intent intent = TransactionActivity.launchIntent(context, true, null);
        context.startActivity(intent);
    }

    public void toTransactionOut(@NonNull Context context) {
        Intent intent = TransactionActivity.launchIntent(context, false, null);
        context.startActivity(intent);
    }

    public void toTransactionOut(@NonNull Context context, Transaction ticket) {
        Intent intent = TransactionActivity.launchIntent(context, false, ticket);
        context.startActivity(intent);
    }

    public void toConfiguration(@NonNull Context context) {
        Intent intent = ConfigurationActivity.launchIntent(context);
        context.startActivity(intent);
    }

    public void toPrinterConfig(@NonNull Context context) {
        Intent intent = ConfigPrinterActivity.launchIntent(context);
        context.startActivity(intent);
    }

    public void toReports(@NonNull Context context) {
        Intent intent = ReportsActivity.launchIntent(context);
        context.startActivity(intent);
    }

    public void toReportsOpen(@NonNull Context context) {
        Intent intent = ReportsOpenActivity.launchIntent(context);
        context.startActivity(intent);
    }

    public void toReportsRevenue(@NonNull Context context) {
        Intent intent = ReportsRevenuewActivity.launchIntent(context);
        context.startActivity(intent);
    }

    public void toConfigConfig(@NonNull Context context) {
        Intent intent = ConfigConfigActivity.launchIntent(context);
        context.startActivity(intent);
    }
}
