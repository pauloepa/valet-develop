package com.martins.valet.presentation.features.transaction.in;

import com.martins.valet.data.features.entity.disk.BrandVehycleRealm;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.presentation.Helpers.BaseView;

import java.util.List;

/**
 * Created by policante on 7/16/16.
 */
public interface BrandView extends BaseView{
    void onSuccessLoadBrand(List<BrandVehycle> brands);
}
