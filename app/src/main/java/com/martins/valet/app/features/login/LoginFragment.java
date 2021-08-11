package com.martins.valet.app.features.login;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.login.LoginPresenter;
import com.martins.valet.presentation.features.login.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by policante on 7/3/16.
 */
public class LoginFragment extends BaseFragment implements LoginView {

    @BindView(R.id.login_username)
    TextInputLayout username;
    @BindView(R.id.login_password)
    TextInputLayout password;

    @Inject
    LoginPresenter presenter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.login_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        getComponent(LoginComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {

    }

    @Override
    protected void teardownView() {

    }

    @OnClick(R.id.login_signin)
    public void doLogin() {
        String usernameStr = username.getEditText().getText().toString();
        String passwordStr = password.getEditText().getText().toString();

        if (usernameStr.isEmpty() || passwordStr.isEmpty()) {
            Snackbar.make(getActivityRootView(), "Usuário/Senha inválidos", Snackbar.LENGTH_LONG).show();
            return;
        }

        hideKeyboard();

        presenter.doLogin(usernameStr, passwordStr);
    }

    @Override
    public void onLoginSucceed() {
        navigator.toHome(getContext());
    }
}
