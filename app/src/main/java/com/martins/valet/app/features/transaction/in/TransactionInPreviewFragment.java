package com.martins.valet.app.features.transaction.in;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.martins.valet.R;
import com.martins.valet.Utils.Helpers.MaskHelper;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.Utils.UI.DialogFactory;
import com.martins.valet.app.features.transaction.TransactionActivity;
import com.martins.valet.app.features.transaction.TransactionComponent;
import com.martins.valet.domain.features.model.VehycleInfo;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.transaction.in.TransactionInPreviewPresenter;
import com.martins.valet.presentation.features.transaction.in.TransactionView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by policante on 7/10/16.
 */
public class TransactionInPreviewFragment extends BaseFragment implements TransactionView {

    @Inject
    TransactionInPreviewPresenter presenter;

    @BindView(R.id.transaction_in_preview_plate)
    TextView plate;
    @BindView(R.id.transaction_in_preview_type)
    TextView type;
    @BindView(R.id.transaction_in_preview_brand)
    TextView brand;
    @BindView(R.id.transaction_in_preview_color)
    TextView color;
    @BindView(R.id.transaction_in_preview_client)
    LinearLayout clientView;
    @BindView(R.id.transaction_in_preview_client_document)
    TextView clientDocument;
    @BindView(R.id.transaction_in_preview_client_name)
    TextView clientName;
    @BindView(R.id.transaction_in_preview_client_observation)
    TextView clientObservation;
    @BindView(R.id.transaction_in_preview_finish)
    AppCompatButton finish;

    private VehycleInfo vehycleInfo;

    public static TransactionInPreviewFragment newInstance() {
        Bundle args = new Bundle();
        TransactionInPreviewFragment fragment = new TransactionInPreviewFragment();
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
        return R.layout.transaction_in_preview_fragment;
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
        clientView.setVisibility(View.GONE);
        this.vehycleInfo = ((TransactionActivity) getActivity()).getVehycleInfo();

        plate.setText(MaskHelper.plateMask(this.vehycleInfo.getVehycle().getPlate()));
        type.setText(this.vehycleInfo.getVehycle().getType());
        brand.setText(this.vehycleInfo.getVehycle().getBrand());
        color.setText(this.vehycleInfo.getVehycle().getColor());

        if (this.vehycleInfo.getClient() != null){
            clientView.setVisibility(View.VISIBLE);

            String document = this.vehycleInfo.getClient().getDocument();
            if (TextUtils.isEmpty(document)){
                clientDocument.setText("- - - -");
            }else{
                clientDocument.setText(document);
            }
            clientName.setText(this.vehycleInfo.getClient().getName());
            clientObservation.setText(this.vehycleInfo.getClient().getObservation());
        }

        if (!TextUtils.isEmpty(this.vehycleInfo.getObservation())) {
            DialogFactory.createAlertDialog(getActivity(), "Observação", this.vehycleInfo.getObservation()).show();
        }
    }

    @Override
    protected void teardownView() {
        plate = null;
        type = null;
        brand = null;
        color = null;
        finish = null;
    }

    @OnClick(R.id.transaction_in_preview_finish)
    public void onClickFinish() {
        presenter.createTransaction(this.vehycleInfo);
    }

    @OnClick(R.id.transaction_in_preview_finish_ticket)
    public void onClickFinishTicket(){
        this.presenter.closeTransactionWithTicket(this.vehycleInfo);
    }

    @Override
    public void onSaveSuccess() {
        Toast.makeText(getContext(), "Movimentação criada com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

}
