package com.martins.valet.presentation.features.transaction.in;

import com.martins.valet.domain.features.model.VehycleInfo;
import com.martins.valet.presentation.Helpers.BaseView;

/**
 * Created by policante on 7/15/16.
 */
public interface PlateView extends BaseView {
    void vehycleInfoNotFound();
    void vehycleInfoSuccess(VehycleInfo vehycleInfo);
}
