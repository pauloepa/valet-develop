package com.martins.valet.app.features.reports.revenuew;

import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.martins.valet.R;
import com.martins.valet.Utils.Helpers.CurrencyFormatterHelper;
import com.martins.valet.Utils.Helpers.DateHelper;
import com.martins.valet.Utils.Helpers.EditTextWatcherHelper;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.Utils.UI.DialogFactory;
import com.martins.valet.app.features.reports.revenuew.adapter.ReportsRevenueAdapter;
import com.martins.valet.domain.features.model.ReportAgreement;
import com.martins.valet.domain.features.model.ReportsRevenuew;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.reports.ReportsRevenuewPresenter;
import com.martins.valet.presentation.features.reports.ReportsRevenuewView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by policante on 8/13/16.
 */
public class ReportsRevenuewFragment extends BaseFragment implements ReportsRevenuewView {

    @Inject
    ReportsRevenuewPresenter presenter;

    @BindView(R.id.reports_revenue_open)
    TextView open;
    @BindView(R.id.reports_revenue_avulso)
    TextView avulso;
    @BindView(R.id.reports_revenue_mensal)
    TextView mensal;
    @BindView(R.id.reports_revenue_total)
    TextView total;
    @BindView(R.id.reports_revenue_ticket_begin)
    TextView ticketBegin;
    @BindView(R.id.reports_revenue_ticket_end)
    TextView ticketEnd;
    @BindView(R.id.reports_revenue_value_total)
    TextView totalValue;
    @BindView(R.id.reports_revenue_agreements)
    RecyclerView recyclerView;
    @BindView(R.id.reports_revenue_print)
    FloatingActionButton print;

    @BindView(R.id.reports_revenue_parameters)
    RelativeLayout paramView;

    @BindView(R.id.reports_revenue_date_in)
    EditText dateIn;
    @BindView(R.id.reports_revenue_time_in)
    EditText timeIn;
    @BindView(R.id.reports_revenue_date_out)
    EditText dateOut;
    @BindView(R.id.reports_revenue_time_out)
    EditText timeOut;

    private List<ReportAgreement> list;
    private ReportsRevenuew reports;
    private ReportsRevenueAdapter adapter;

    private Date dateReportIn;
    private Date dateReportOut;

    public static ReportsRevenuewFragment newInstance() {
        Bundle args = new Bundle();
        ReportsRevenuewFragment fragment = new ReportsRevenuewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.reports_revenue_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void injectDependencies() {
        getComponent(ReportsRevenuewComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {
        this.list = new ArrayList<>();

        paramView.setVisibility(View.VISIBLE);

        Calendar c = Calendar.getInstance();
        try {
            dateIn.setText(DateHelper.convertStringFromDateTime(c.getTime(), "dd/MM/yyyy"));
            dateOut.setText(DateHelper.convertStringFromDateTime(c.getTime(), "dd/MM/yyyy"));

            timeIn.setText("07:00:00");
            timeOut.setText("20:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateIn.addTextChangedListener(EditTextWatcherHelper.dateWatcher(dateIn));
        timeIn.addTextChangedListener(EditTextWatcherHelper.timeWatcher(timeIn));
        dateOut.addTextChangedListener(EditTextWatcherHelper.dateWatcher(dateOut));
        timeOut.addTextChangedListener(EditTextWatcherHelper.timeWatcher(timeOut));
        print.hide();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAnimation(new AlphaAnimation(0, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ReportsRevenueAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void teardownView() {

    }

    @Override
    public void onLoadSuccess(ReportsRevenuew reportsRevenuew) {
        open.setText(String.valueOf(reportsRevenuew.getOpen().getQuantity()));
        avulso.setText(String.valueOf(reportsRevenuew.getAvulso().getQuantity()));
        mensal.setText(String.valueOf(reportsRevenuew.getMensal().getQuantity()));
        total.setText(String.valueOf(reportsRevenuew.getGeneral().getQuantity()));

        ticketBegin.setText(reportsRevenuew.getGeneral().getValueBegin());
        ticketEnd.setText(reportsRevenuew.getGeneral().getValueFinal());

        String currency = CurrencyFormatterHelper.formatNumberToReal(Double.parseDouble(reportsRevenuew.getGeneral().getValueTotal()));
        totalValue.setText(currency);


        this.reports = reportsRevenuew;
        this.list.clear();
        this.list.addAll(reportsRevenuew.getAgreements());
        this.adapter.notifyDataSetChanged();

        paramView.setVisibility(View.GONE);
        print.show();
    }

    @OnClick(R.id.reports_revenue_search)
    public void onClickSearch() {
        String strDateIn = dateIn.getText().toString();
        String strTimeIn = timeIn.getText().toString();
        String strDateOut = dateOut.getText().toString();
        String strTimeOut = timeOut.getText().toString();

        try {
            dateReportIn = DateHelper.convertDateTimeFromString(strDateIn + " " + strTimeIn, "dd/MM/yyyy HH:mm:ss");
            dateReportOut = DateHelper.convertDateTimeFromString(strDateOut + " " + strTimeOut, "dd/MM/yyyy HH:mm:ss");
        } catch (Exception e) {
            showMessage("Atenção", "Informe os dados corretamente");
            return;
        }

        hideKeyboard();
        presenter.getReports(dateReportIn, dateReportOut);
    }

    @OnClick(R.id.reports_revenue_print)
    public void onClickPrint(){
        DialogFactory.createAlertDialogConfirm(getActivity(), "Imprimir", "Deseja imprimir o relatório?",
                "Imprimir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.printRevenue(reports, dateReportIn, dateReportOut);
                    }
                }, "Cancelar", null)
                .show();
    }
}
