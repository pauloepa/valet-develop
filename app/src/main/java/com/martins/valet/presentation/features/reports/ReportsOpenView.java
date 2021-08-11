package com.martins.valet.presentation.features.reports;

import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.presentation.Helpers.BaseView;

import java.util.List;

/**
 * Created by policante on 7/31/16.
 */
public interface ReportsOpenView extends BaseView{
    void onSuccessList(List<Transaction> transactions);
}
