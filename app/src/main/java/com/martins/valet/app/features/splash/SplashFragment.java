package com.martins.valet.app.features.splash;

import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.splash.SplashPresenter;
import com.martins.valet.presentation.features.splash.SplashView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by policante on 7/3/16.
 */
public class SplashFragment extends BaseFragment implements SplashView {

    @BindView(R.id.splash_pathview_svg)
    PathView pathView;

    @Inject
    SplashPresenter presenter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.splash_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        getComponent(SplashComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {

    }

    @Override
    protected void teardownView() {

    }

    @Override
    public void configComplete() {
        pathView.getPathAnimator()
                .delay(1000)
                .duration(1400)
                .interpolator(new AccelerateDecelerateInterpolator())
                .listenerEnd(() -> {
                    if (configManager.getConfiguration().userLoggedIn()) {
                        navigator.toHome(getContext());
                    } else {
                        navigator.toLogin(getContext());
                    }
                    finish();
                })
                .start();
        pathView.useNaturalColors();
        pathView.setFillAfter(true);
    }
}
