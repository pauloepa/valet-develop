package com.martins.valet.app.features.reports;

import android.os.Bundle;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.presentation.Helpers.BasePresenter;

import butterknife.OnClick;

/**
 * Created by policante on 7/21/16.
 */
public class ReportsFragment extends BaseFragment {

    public static ReportsFragment newInstance() {
        Bundle args = new Bundle();
        ReportsFragment fragment = new ReportsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.reports_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void injectDependencies() {
        getComponent(ReportsComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {

    }

    @Override
    protected void teardownView() {

    }

    @OnClick(R.id.reports_cardview_place)
    public void onClickPlace(){
        navigator.toReportsOpen(getContext());
    }

    @OnClick(R.id.reports_cardview_revenuew)
    public void onClickEndDay(){
        navigator.toReportsRevenue(getContext());
    }
}
