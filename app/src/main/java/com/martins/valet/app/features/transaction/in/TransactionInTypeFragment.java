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
import com.martins.valet.app.features.transaction.in.adapter.TypeAdapter;
import com.martins.valet.domain.features.model.TypeVehycle;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.transaction.in.TransactionInTypePresenter;
import com.martins.valet.presentation.features.transaction.in.TypeVehyclesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by policante on 7/9/16.
 */
public class TransactionInTypeFragment extends BaseFragment implements TypeVehyclesView, TypeAdapter.OnMenuClickListener {

    @BindView(R.id.transaction_in_type_recycler)
    RecyclerView recyclerView;

    TypeAdapter adapter;

    List<TypeVehycle> list;

    @Inject
    TransactionInTypePresenter presenter;

    public static TransactionInTypeFragment newInstance() {
        Bundle args = new Bundle();
        TransactionInTypeFragment fragment = new TransactionInTypeFragment();
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
        return R.layout.transaction_in_type_fragment;
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
        list = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAnimation(new AlphaAnimation(0, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new TypeAdapter(this.list);
        adapter.setOnMenuClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void teardownView() {
        recyclerView = null;
        adapter = null;
        list = null;
    }

    @Override
    public void onClickMenu(TypeVehycle typeModel) {
        if (typeModel != null) {
            ((TransactionActivity)getActivity()).onNextStep(this, typeModel);
        }
    }

    @Override
    public void onSuccessLoadType(List<TypeVehycle> types) {
        this.list.clear();
        this.list.addAll(types);
        this.adapter.notifyDataSetChanged();
    }
}
