package com.martins.valet.app.features.configuration.config;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.configuration.config.ConfigConfigPresenter;
import com.martins.valet.presentation.features.configuration.config.ConfigConfigView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by marcos on 4/9/19.
 */
public class ConfigConfigFragment extends BaseFragment implements ConfigConfigView {

    @Inject
    ConfigConfigPresenter presenter;

    @BindView(R.id.config_config_company)
    TextInputLayout company;
    @BindView(R.id.config_config_address)
    TextInputLayout address;
    @BindView(R.id.config_config_document)
    TextInputLayout document;
    @BindView(R.id.config_config_phone)
    TextInputLayout phone;
    @BindView(R.id.config_config_obs)
    TextInputLayout obs;

    public static ConfigConfigFragment newInstance() {
        Bundle args = new Bundle();
        ConfigConfigFragment fragment = new ConfigConfigFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.config_config_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        getComponent(ConfigConfigComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {
        Objects.requireNonNull(company.getEditText()).setText(presenter.getPrefs().getCompany());
        Objects.requireNonNull(address.getEditText()).setText(presenter.getPrefs().getAddress());
        Objects.requireNonNull(document.getEditText()).setText(presenter.getPrefs().getDocument());
        Objects.requireNonNull(phone.getEditText()).setText(presenter.getPrefs().getPhone());
        Objects.requireNonNull(obs.getEditText()).setText(presenter.getPrefs().getObservation());
    }

    @Override
    protected void teardownView() { }

    @OnClick(R.id.config_config_finish)
    public void onClickFinish() {
        hideKeyboard();

        presenter.getPrefs().setCompany(Objects.requireNonNull(company.getEditText()).getText().toString());
        presenter.getPrefs().setAddress(Objects.requireNonNull(address.getEditText()).getText().toString());
        presenter.getPrefs().setDocument(Objects.requireNonNull(document.getEditText()).getText().toString());
        presenter.getPrefs().setPhone(Objects.requireNonNull(phone.getEditText()).getText().toString());
        presenter.getPrefs().setObservation(Objects.requireNonNull(obs.getEditText()).getText().toString());
        presenter.saveConfigSetup();
    }

    @Override
    public void onSaveSuccess() {
        Snackbar.make(getActivityRootView(), "Configurações salvas com sucesso", Snackbar.LENGTH_LONG).show();
        finish();
    }
}
