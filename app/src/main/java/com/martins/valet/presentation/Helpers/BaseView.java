package com.martins.valet.presentation.Helpers;

import android.app.Activity;
import android.content.Context;

import com.martins.valet.data.features.entity.disk.TypeVehycleRealm;

import java.util.List;

/**
 * Created by policante on 7/3/16.
 */
public interface BaseView {
    Context getContext();

    void showLoading();
    void hideLoading();

    void showError(Throwable throwable);

    void setCurrentActivity(Activity activity);
}
