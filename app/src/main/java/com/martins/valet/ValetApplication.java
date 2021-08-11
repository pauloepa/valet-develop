package com.martins.valet;

import android.app.Application;

import com.martins.valet.Utils.UI.DaggerValetApplicationComponent;
import com.martins.valet.Utils.UI.ValetApplicationComponent;
import com.martins.valet.Utils.UI.ValetApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by policante on 7/2/16.
 */
public class ValetApplication extends Application {

    private static final int SCHEMA_VERSION = 0;
    private static final String SCHEMA_NAME = "Valet.realm";

    private ValetApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();

        getApplicationComponent().inject(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(SCHEMA_NAME)
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }

    private void initializeInjector() {
        this.component = DaggerValetApplicationComponent.builder()
                .valetApplicationModule(new ValetApplicationModule(this, this))
                .build();

    }

    public ValetApplicationComponent getApplicationComponent() {
        return this.component;
    }

}
