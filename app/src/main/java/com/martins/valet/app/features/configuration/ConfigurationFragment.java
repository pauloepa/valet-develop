package com.martins.valet.app.features.configuration;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.configuration.ConfigurationPresenter;
import com.martins.valet.presentation.features.configuration.ConfigurationView;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by policante on 7/16/16.
 */
public class ConfigurationFragment extends BaseFragment implements ConfigurationView {

    @Inject
    ConfigurationPresenter presenter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.config_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        getComponent(ConfigurationComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {

    }

    @Override
    protected void teardownView() {

    }

    @OnClick(R.id.config_cardview_sync)
    public void onSyncClick(){
        presenter.sync();
    }

    @OnClick(R.id.config_cardview_printer)
    public void onPrinterClick(){
        navigator.toPrinterConfig(getContext());
    }

    @OnClick(R.id.config_cardview_config)
    public void onConfigClick(){
        navigator.toConfigConfig(getContext());
    }

    @Override
    public void syncComplete() {
        finish();
    }
}
