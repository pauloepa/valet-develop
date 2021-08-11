package com.martins.valet.Utils.UI;

import android.app.Activity;

import com.martins.valet.Utils.Helpers.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 7/2/16.
 */
@Module
public class ValetActivityModule {
    private final Activity activity;

    public ValetActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity(){
        return this.activity;
    }
}
