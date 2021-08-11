package com.martins.valet.app.features.home;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.animation.AlphaAnimation;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.app.features.home.adapter.HomeAdapter;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.home.HomeMenu;
import com.martins.valet.presentation.features.home.HomeMenuManager;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by policante on 7/5/16.
 */
public class HomeFragment extends BaseFragment implements HomeAdapter.OnMenuClickListener {

    @BindView(R.id.home_recyclerview_list)
    RecyclerView recyclerView;
    HomeAdapter adapter;
    @Inject
    HomeMenuManager menuManager;

    @Override
    protected int getFragmentLayout() {
        return R.layout.home_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void injectDependencies() {
        getComponent(HomeComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAnimation(new AlphaAnimation(0, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new HomeAdapter(menuManager.getHomeMenuList());
        adapter.setOnMenuClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void teardownView() {
        recyclerView = null;
        adapter = null;
    }

    @Override
    public void onClickMenu(HomeMenu menu) {
        if (menu.getTitle().equals(getString(R.string.menu_car_in))) {
            navigator.toTransactionIn(getContext());
        } else if (menu.getTitle().equals(getString(R.string.menu_car_out))) {
            navigator.toTransactionOut(getContext());
        } else if (menu.getTitle().equals(getString(R.string.menu_setup))) {
            navigator.toConfiguration(getContext());
        } else {
            navigator.toReports(getContext());
        }
    }


}
