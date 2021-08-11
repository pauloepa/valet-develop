package com.martins.valet.Utils.UI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.martins.valet.R;
import com.martins.valet.Utils.Helpers.Navigator;
import com.martins.valet.Utils.Helpers.PerActivity;
import com.martins.valet.domain.features.model.Configuration;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.Helpers.BaseView;
import com.martins.valet.presentation.Helpers.HasComponent;
import com.martins.valet.presentation.features.configuration.ConfigManager;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by policante on 7/3/16.
 */
@PerActivity
public abstract class BaseFragment extends Fragment implements BaseView {

    @Inject
    protected Navigator navigator;
    @Inject
    protected ConfigManager configManager;

    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        injectDependencies();
        getApplicationComponent().inject(this);
    }

    protected ValetApplicationComponent getApplicationComponent() {
        return ((BaseActivity) getActivity()).getApplicationComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        injectViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getPresenter() != null) {
            getPresenter().setView(this);
        }
        initializeInjectedMembers();
    }

    @Override
    public void onDestroyView() {
        teardownView();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getPresenter() != null) {
            getPresenter().pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().destroy();
        }
    }

    protected abstract int getFragmentLayout();

    protected abstract BasePresenter getPresenter();

    protected abstract void injectDependencies();

    protected abstract void initializeInjectedMembers();

    protected abstract void teardownView();

    protected void injectViews(View view) {
        ButterKnife.bind(this, view);
    }

    protected View getActivityRootView() {
        View view = getActivity().findViewById(R.id.coordinator_layout);

        if (view == null) {
            view = getActivity().findViewById(android.R.id.content);
        }

        return view;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    protected void finish() {
        getActivity().finish();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> component) {
        return component.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = DialogFactory.createProgressDialogIndeterminateModal(getActivity());
            progressDialog.setMessage("Aguarde...");
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected void showMessage(String title, String message) {
        DialogFactory.createAlertDialog(getActivity(), title, message).show();
    }

    @Override
    public void showError(Throwable throwable) {
        if (alertDialog == null) {
            alertDialog = DialogFactory.createAlertWarningDialog(getActivity(), throwable.getMessage());
        }
        alertDialog.setMessage(throwable.getMessage());
        alertDialog.show();
    }

    protected void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public Configuration getConfiguration() {
        if (this.configManager != null && this.configManager.getConfiguration() != null) {
            return this.configManager.getConfiguration();
        }

        return null;
    }

    public void setCurrentActivity(Activity activity) {

    }
}
