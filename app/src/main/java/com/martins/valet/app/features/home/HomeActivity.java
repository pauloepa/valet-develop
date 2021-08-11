package com.martins.valet.app.features.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.Utils.UI.DialogFactory;
import com.martins.valet.presentation.Helpers.HasComponent;

/**
 * Created by policante on 7/5/16.
 */
public class HomeActivity extends BaseActivity implements HasComponent<HomeComponent> {

    HomeComponent component;

    public static Intent launchIntent(Context context){
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void initializeInjector() {
        this.component = DaggerHomeComponent.builder()
                .valetApplicationComponent(getApplicationComponent())
                .valetActivityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        // Proceed with initialization
                    }

                    @Override
                    public void onDenied(String permission) {
                        DialogFactory.createAlertWarningDialog(HomeActivity.this,"Negando as permissões, a aplicação pode não funcionar corretamente").show();
                    }
                });
    }

    @Override
    protected boolean displayHome() {
        return false;
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.home_activity;
    }

    @Override
    public HomeComponent getComponent() {
        return this.component;
    }

}
