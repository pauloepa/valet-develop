package com.martins.valet.presentation.features.transaction.in;

import com.martins.valet.data.features.entity.disk.TypeVehycleRealm;
import com.martins.valet.domain.features.model.TypeVehycle;
import com.martins.valet.presentation.Helpers.BaseView;

import java.util.List;

/**
 * Created by policante on 7/16/16.
 */
public interface TypeVehyclesView extends BaseView{

    void onSuccessLoadType(List<TypeVehycle> types);
}
