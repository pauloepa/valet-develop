package com.martins.valet.app.features.transaction.in;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.gridlayout.widget.GridLayout;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.Utils.UI.DialogFactory;
import com.martins.valet.app.features.transaction.TransactionActivity;
import com.martins.valet.app.features.transaction.TransactionComponent;
import com.martins.valet.domain.features.model.VehycleInfo;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.transaction.in.PlateView;
import com.martins.valet.presentation.features.transaction.in.TransactionInPlatePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by policante on 7/7/16.
 */
public class TransactionInPlateFragment extends BaseFragment implements PlateView {

    private static final int MAX_CHARACTER = 7;

    @BindView(R.id.transaction_in_plate_grid_letter)
    GridLayout gridLetters;
    @BindView(R.id.transaction_in_plate_grid_number)
    GridLayout gridNumbers;
    @BindView(R.id.transaction_in_plate_plate)
    TextView plate;
    @Inject
    TransactionInPlatePresenter presenter;
    private MenuItem changeKeyboard;
    private MenuItem noPlate;
    private String strPlate = "";

    public static TransactionInPlateFragment newInstance() {
        Bundle args = new Bundle();
        TransactionInPlateFragment fragment = new TransactionInPlateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        ((TransactionActivity) getActivity()).changedFragment(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.transaction_in_plate_fragment;
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
        setClickButtons(gridLetters);
        setClickButtons(gridNumbers);
        updateText(null);
    }

    @Override
    protected void teardownView() {

    }

    @Override
    public void vehycleInfoNotFound() {
        ((TransactionActivity) getActivity()).onNextStep(this, this.strPlate);
    }

    @Override
    public void vehycleInfoSuccess(VehycleInfo vehycleInfo) {

        if (vehycleInfo.isExistsTransactionOpen()) {
            DialogFactory.createAlertWarningDialog(getActivity(), "Foi encontrado uma transação aberta para esta placa!\nVerifique!").show();
            return;
        }

        ((TransactionActivity) getActivity()).onNextStep(this, vehycleInfo);
    }

    @OnClick(R.id.transaction_in_plate_clear)
    public void onClickClear() {
        updateText(null);
    }

    @OnLongClick(R.id.transaction_in_plate_clear)
    public boolean onLongClickClear() {
        strPlate = "";
        updateText(null);
        return true;
    }

    private void setClickButtons(GridLayout grid) {
        int childCount = grid.getChildCount();

        for (int i = 0; i < childCount; i++) {
            AppCompatButton bt = (AppCompatButton) grid.getChildAt(i);
            bt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (view instanceof AppCompatButton) {
                        String chr = ((AppCompatButton) view).getText().toString();
                        updateText(chr);
                    }
                }
            });
        }
    }

    private void updateText(String value) {
        if (value != null) {

            if (strPlate.length() >= MAX_CHARACTER) {
                return;
            }

            strPlate += value;
        } else {
            if (strPlate.length() > 0) {
                strPlate = strPlate.substring(0, strPlate.length() - 1);
            }
        }

        if (TextUtils.isEmpty(strPlate)) {
            plate.setText("Informe a placa");
        } else {
            plate.setText(strPlate);
        }


        updateKeyboard();
    }

    @OnClick(R.id.transaction_in_plate_next)
    public void onClickNext() {
        if (strPlate.length() == MAX_CHARACTER) {
            this.presenter.findVehycle(strPlate);
        } else {
            showMessage("Atenção", "Informe uma placa válida");
        }
    }

    private void updateKeyboard() {
        if (changeKeyboard != null) {
            changeKeyboard.setVisible(false);
        }
        if (noPlate != null) {
            noPlate.setVisible(strPlate.length() == 0);
        }
        if (strPlate.length() < 3) {
            gridLetters.setVisibility(View.VISIBLE);
            gridNumbers.setVisibility(View.GONE);
        } else if (strPlate.length() == 4) {
            gridLetters.setVisibility(View.GONE);
            gridNumbers.setVisibility(View.VISIBLE);
            changeKeyboard.setVisible(true);
        } else {
            gridLetters.setVisibility(View.GONE);
            gridNumbers.setVisibility(View.VISIBLE);
        }

        if (strPlate.length() == MAX_CHARACTER) {
            this.presenter.findVehycle(strPlate);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (noPlate != null) {
            noPlate.setVisible(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        changeKeyboard = menu.findItem(R.id.menu_in_change_keyboard);
        noPlate = menu.findItem(R.id.menu_in_no_plate);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_in_change_keyboard) {
            if (gridLetters.getVisibility() == View.VISIBLE) {
                gridLetters.setVisibility(View.GONE);
                gridNumbers.setVisibility(View.VISIBLE);
            } else {
                gridLetters.setVisibility(View.VISIBLE);
                gridNumbers.setVisibility(View.GONE);
            }
        } else if (item.getItemId() == R.id.menu_in_no_plate) {
            strPlate = " ";

            ((TransactionActivity) getActivity()).onNextStep(this, strPlate);
        }

        return super.onOptionsItemSelected(item);
    }
}
