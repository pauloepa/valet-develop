package com.martins.valet.presentation.features.configuration.config;

import com.martins.valet.Utils.Helpers.Preferences;
import com.martins.valet.presentation.Helpers.BasePresenter;

import javax.inject.Inject;

/**
 * Created by policante on 7/18/16.
 */
public class ConfigConfigPresenter extends BasePresenter<ConfigConfigView> {

    private Preferences prefs;

    @Inject
    public ConfigConfigPresenter() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public Preferences getPrefs() {
        if (this.prefs == null) {
            this.prefs = new Preferences(getView().getContext());
            this.prefs.load();
        }

        return this.prefs;
    }

    public void saveConfigSetup() {
        this.prefs.save();

        getView().onSaveSuccess();
    }
}
