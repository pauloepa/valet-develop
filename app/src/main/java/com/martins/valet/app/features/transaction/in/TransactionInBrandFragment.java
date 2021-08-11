package com.martins.valet.app.features.transaction.in;

import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.animation.AlphaAnimation;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.app.features.transaction.TransactionActivity;
import com.martins.valet.app.features.transaction.TransactionComponent;
import com.martins.valet.app.features.transaction.in.adapter.BrandAdapter;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.transaction.in.BrandView;
import com.martins.valet.presentation.features.transaction.in.TransactionInBrandPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by policante on 7/7/16.
 */
public class TransactionInBrandFragment extends BaseFragment implements BrandView, BrandAdapter.OnMenuClickListener {

    private static final String ARGS_TYPE = TransactionInBrandFragment.class.getSimpleName() + ".TYPE";
    private static final String ARGS_TYPE_NAME = TransactionInBrandFragment.class.getSimpleName() + ".TYPE_NAME";

    @BindView(R.id.transaction_in_brand_recycler)
    RecyclerView recyclerView;

    BrandAdapter adapter;
    List<BrandVehycle> list;

    String typeName;

    @Inject
    TransactionInBrandPresenter presenter;

    public static TransactionInBrandFragment newInstance(int typeId, String typeName) {
        Bundle args = new Bundle();
        TransactionInBrandFragment fragment = new TransactionInBrandFragment();
        args.putInt(ARGS_TYPE, typeId);
        args.putString(ARGS_TYPE_NAME, typeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        ((TransactionActivity)getActivity()).changedFragment(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.transaction_in_brand_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        getComponent(TransactionComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {
        this.list = new ArrayList<>();

        int type = getArguments().getInt(ARGS_TYPE);
        typeName = getArguments().getString(ARGS_TYPE_NAME);
        presenter.loadBrands(type);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAnimation(new AlphaAnimation(0, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new BrandAdapter(this.list);
        adapter.setOnMenuClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void teardownView() {

    }

    @Override
    public void onClickMenu(BrandVehycle typeModel) {
        typeModel.setType(typeName);
        ((TransactionActivity) getActivity()).onNextStep(this, typeModel);
    }

    @Override
    public void onSuccessLoadBrand(List<BrandVehycle> brands) {
        this.list.clear();
        this.list.addAll(brands);
        this.adapter.notifyDataSetChanged();
    }
}
