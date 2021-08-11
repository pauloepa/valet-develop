package com.martins.valet.presentation.features.configuration;

import com.martins.valet.domain.features.model.Configuration;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by policante on 7/18/16.
 */
@Singleton
public class ConfigManager {

    private Configuration configuration;

    @Inject public ConfigManager() {

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
