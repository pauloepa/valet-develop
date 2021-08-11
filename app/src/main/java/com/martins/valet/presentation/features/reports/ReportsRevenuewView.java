package com.martins.valet.presentation.features.reports;

import com.martins.valet.domain.features.model.ReportsRevenuew;
import com.martins.valet.presentation.Helpers.BaseView;

/**
 * Created by policante on 8/13/16.
 */
public interface ReportsRevenuewView extends BaseView {
    void onLoadSuccess(ReportsRevenuew reportsRevenuew);
}
