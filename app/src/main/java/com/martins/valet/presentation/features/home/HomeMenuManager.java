package com.martins.valet.presentation.features.home;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.martins.valet.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.GenericFont;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by policante on 7/5/16.
 */
@Singleton
public class HomeMenuManager {

    private Context context;
    private List<HomeMenu> homeMenuList;

    @Inject
    public HomeMenuManager(Context context) {
        this.context = context;
        buildMenu();
    }

    private void buildMenu(){
        homeMenuList = new ArrayList<>();

        HomeMenu carIn = new HomeMenu();
        carIn.setTitle(this.context.getString(R.string.menu_car_in));
        carIn.setIcon(R.drawable.ic_up_arrow);
        homeMenuList.add(carIn);

        HomeMenu carOut = new HomeMenu();
        carOut.setTitle(this.context.getString(R.string.menu_car_out));
        carOut.setIcon(R.drawable.ic_down_arrow);
        homeMenuList.add(carOut);

        HomeMenu setup = new HomeMenu();
        setup.setTitle(this.context.getString(R.string.menu_setup));
        setup.setIcon(R.drawable.ic_settings);
        homeMenuList.add(setup);

        HomeMenu reports = new HomeMenu();
        reports.setTitle(this.context.getString(R.string.menu_reports));
        reports.setIcon(R.drawable.ic_reports);
        homeMenuList.add(reports);

    }

    public List<HomeMenu> getHomeMenuList() {
        return homeMenuList;
    }

    public void setHomeMenuList(List<HomeMenu> homeMenuList) {
        this.homeMenuList = homeMenuList;
    }
}
