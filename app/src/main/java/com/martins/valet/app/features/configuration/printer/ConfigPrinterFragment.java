package com.martins.valet.app.features.configuration.printer;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import android.text.InputFilter;
import android.util.Patterns;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.configuration.printer.ConfigPrinterPresenter;
import com.martins.valet.presentation.features.configuration.printer.ConfigPrinterView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by policante on 7/18/16.
 */
public class ConfigPrinterFragment extends BaseFragment implements ConfigPrinterView {

    @Inject
    ConfigPrinterPresenter presenter;

    @BindView(R.id.config_printer_ip_address)
    TextInputLayout ipAddress;

    public static ConfigPrinterFragment newInstance() {
        Bundle args = new Bundle();
        ConfigPrinterFragment fragment = new ConfigPrinterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.config_printer_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        getComponent(ConfigPrinterComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       android.text.Spanned dest, int dstart, int dend) {
                if (end > start) {
                    String destTxt = dest.toString();
                    String resultingTxt = destTxt.substring(0, dstart)
                            + source.subSequence(start, end)
                            + destTxt.substring(dend);
                    if (!resultingTxt
                            .matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                        return "";
                    } else {
                        String[] splits = resultingTxt.split("\\.");
                        for (int i = 0; i < splits.length; i++) {
                            if (Integer.valueOf(splits[i]) > 255) {
                                return "";
                            }
                        }
                    }
                }
                return null;
            }

        };
        ipAddress.getEditText().setFilters(filters);

        if (getConfiguration() != null && getConfiguration().getPrinterHost() != null) {
            ipAddress.getEditText().setText(getConfiguration().getPrinterHost());
            ipAddress.getEditText().setSelectAllOnFocus(true);
        }
    }

    @Override
    protected void teardownView() {

    }

    @OnClick(R.id.config_printer_test)
    public void onClickTest() {
        String ip = ipAddress.getEditText().getText().toString();
        if (Patterns.IP_ADDRESS.matcher(ip).matches()) {
            hideKeyboard();
            presenter.printTest(ip);
        } else {
            showMessage("IP inválido", "Informe um ip válido");
        }
    }

    @OnClick(R.id.config_printer_finish)
    public void onClickFinish() {
        hideKeyboard();
        String ip = ipAddress.getEditText().getText().toString();
        if (Patterns.IP_ADDRESS.matcher(ip).matches()) {
            hideKeyboard();
            presenter.savePrinterSetup(ip);
        } else {
            showMessage("IP inválido", "Informe um ip válido");
        }
    }

    @Override
    public void onPrintSuccess() {
        Snackbar.make(getActivityRootView(), "Teste enviado!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onSaveSuccess() {
        Snackbar.make(getActivityRootView(), "Configurações salvas com sucesso", Snackbar.LENGTH_LONG).show();
        finish();
    }
}
