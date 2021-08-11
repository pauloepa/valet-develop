package com.martins.valet.app.features.reports.open;

import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.Utils.UI.DialogFactory;
import com.martins.valet.app.features.reports.open.adapter.ReportsOpenAdapter;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.reports.ReportsOpenPresenter;
import com.martins.valet.presentation.features.reports.ReportsOpenView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by policante on 7/30/16.
 */
public class ReportsOpenFragment extends BaseFragment implements ReportsOpenView, ReportsOpenAdapter.EmptyListener, ReportsOpenAdapter.ClickListener {

    @BindView(R.id.reports_open_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.reports_open_printer)
    FloatingActionButton btnPrinter;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.reports_open_empty_message)
    TextView messageEmpty;

    private ReportsOpenAdapter adapter;
    private List<Transaction> list;

    @Inject ReportsOpenPresenter presenter;

    public static ReportsOpenFragment newInstance() {
        Bundle args = new Bundle();
        ReportsOpenFragment fragment = new ReportsOpenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.reports_open_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        getComponent(ReportsOpenComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {
        list = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAnimation(new AlphaAnimation(0, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ReportsOpenAdapter(list);
        adapter.setEmptyListener(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadTransactionOpen();
            }
        });
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void teardownView() {

    }

    @Override
    public void onSuccessList(List<Transaction> transactions) {
        this.list.clear();
        if (transactions != null){
            this.list.addAll(transactions);
        }
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onListEmpty(boolean empty) {
        messageEmpty.setVisibility(empty ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(empty ? View.GONE : View.VISIBLE);

        if (empty){
            btnPrinter.hide();
        }else {
            btnPrinter.show();
        }
    }

    @Override
    public void onClickCloseTransaction(Transaction transaction) {
        transaction.setTransactionOut(new Date());
        navigator.toTransactionOut(getContext(), transaction);
    }

    @Override
    public void onLongClickTransaction(final Transaction transaction) {
        DialogFactory.createAlertDialogConfirm(getActivity(), "Imprimir ticket: " + transaction.getIdentifier(), "Deseja imprimir o ticket da chave?",
                "Imprimir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.printerTicket(transaction);
                    }
                }, "Cancelar", null)
                .show();
    }

    @OnClick(R.id.reports_open_printer)
    public void onClickPrinter(){
        DialogFactory.createAlertDialogConfirm(getActivity(), "Imprimir", "Deseja imprimir a lista de carros no pátio?",
                "Imprimir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.printerOpenTransactions(list);
                    }
                }, "Cancelar", null)
                .show();
    }

}
