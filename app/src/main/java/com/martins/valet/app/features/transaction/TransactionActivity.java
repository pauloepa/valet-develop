package com.martins.valet.app.features.transaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseActivity;
import com.martins.valet.app.features.transaction.in.TransactionInBrandFragment;
import com.martins.valet.app.features.transaction.in.TransactionInColorFragment;
import com.martins.valet.app.features.transaction.in.TransactionInPlateFragment;
import com.martins.valet.app.features.transaction.in.TransactionInPreviewFragment;
import com.martins.valet.app.features.transaction.in.TransactionInTypeFragment;
import com.martins.valet.app.features.transaction.out.TransactionOutAgreementFragment;
import com.martins.valet.app.features.transaction.out.TransactionOutPlateFragment;
import com.martins.valet.app.features.transaction.out.TransactionOutPreviewFragment;
import com.martins.valet.app.features.transaction.out.TransactionOutTicketFragment;
import com.martins.valet.domain.features.model.BrandVehycle;
import com.martins.valet.domain.features.model.Transaction;
import com.martins.valet.domain.features.model.TypeVehycle;
import com.martins.valet.domain.features.model.Vehycle;
import com.martins.valet.domain.features.model.VehycleInfo;
import com.martins.valet.presentation.Helpers.HasComponent;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

/**
 * Created by policante on 7/7/16.
 */
public class TransactionActivity extends BaseActivity implements HasComponent<TransactionComponent> {

    private static final String EXTRA_MODE = TransactionActivity.class.getSimpleName() + ".EXTRA_MODE";
    private static final String EXTRA_TICKET = TransactionActivity.class.getSimpleName() + ".EXTRA_TICKET";

    TransactionComponent component;
    Fragment currentFragment;

    private VehycleInfo vehycleInfo;
    private boolean modeIn;
    private Transaction ticket;
    private MenuItem changeKeyboard;

    public static Intent launchIntent(Context context, boolean modeIn, Transaction ticket) {
        Intent intent = new Intent(context, TransactionActivity.class);
        intent.putExtra(EXTRA_MODE, modeIn);
        intent.putExtra(EXTRA_TICKET, ticket);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.modeIn = getIntent().getBooleanExtra(EXTRA_MODE, false);
        this.ticket = getIntent().getParcelableExtra(EXTRA_TICKET);

        super.onCreate(savedInstanceState);
        if (modeIn) {
            currentFragment = addFragment(R.id.frame_layout, TransactionInPlateFragment.newInstance());
        } else {
            if (ticket != null) {
                currentFragment = addFragment(R.id.frame_layout, TransactionOutPreviewFragment.newInstance(ticket));
            } else {
                currentFragment = addFragment(R.id.frame_layout, TransactionOutTicketFragment.newInstance());
            }
        }
    }

    @Override
    protected void initializeInjector() {
        this.component = DaggerTransactionComponent.builder()
                .valetApplicationComponent(getApplicationComponent())
                .valetActivityModule(getActivityModule())
                .build();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.transaction_activity;
    }

    @Override
    public TransactionComponent getComponent() {
        return this.component;
    }

    public void onNextStep(Fragment fragment, Object value) {
        hideKeyboard();
        if (this.modeIn) {
            if (fragment instanceof TransactionInPlateFragment) {
                vehycleInfo = new VehycleInfo();
                if (value instanceof VehycleInfo) {
                    this.vehycleInfo = (VehycleInfo) value;
                    //Preview info
                    currentFragment = replaceFragment(R.id.frame_layout, TransactionInPreviewFragment.newInstance(), "TransactionInPreviewFragment");
                } else {
                    Vehycle vehycle = new Vehycle();
                    vehycle.setPlate((String) value);
                    this.vehycleInfo.setVehycle(vehycle);
                    currentFragment = replaceFragment(R.id.frame_layout, TransactionInTypeFragment.newInstance(), "TransactionInBrandFragment");
                }
            } else if (fragment instanceof TransactionInTypeFragment) {
                TypeVehycle type = (TypeVehycle) value;
                this.vehycleInfo.getVehycle().setType(type.getName());
                currentFragment = replaceFragment(R.id.frame_layout, TransactionInBrandFragment.newInstance(type.getIdentifier(), type.getName()), "TransactionInBrandFragment");
            } else if (fragment instanceof TransactionInBrandFragment) {
                this.vehycleInfo.getVehycle().setBrand(((BrandVehycle) value).getName());
                currentFragment = replaceFragment(R.id.frame_layout, TransactionInColorFragment.newInstance(), "TransactionInColorFragment");
            } else if (fragment instanceof TransactionInColorFragment) {
                this.vehycleInfo.getVehycle().setColor((String) value);
                currentFragment = replaceFragment(R.id.frame_layout, TransactionInPreviewFragment.newInstance(), "TransactionInPreviewFragment");
            }
        } else {
            Transaction transaction = (Transaction) value;

            if (fragment instanceof TransactionOutAgreementFragment) {
                currentFragment = replaceFragment(R.id.frame_layout, TransactionOutPreviewFragment.newInstance(transaction), "TransactionOutPreviewFragment");
            } else {
                currentFragment = replaceFragment(R.id.frame_layout, TransactionOutPreviewFragment.newInstance(transaction), "TransactionOutPreviewFragment");
            }
        }
        this.invalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);

        if (currentFragment instanceof TransactionOutTicketFragment) {
            currentFragment.onResume();
        }

        this.invalidateOptionsMenu();
    }

    public void changedFragment(Fragment fragment) {
        Log.d("TRANSACTION", fragment.getClass().getSimpleName());
        currentFragment = fragment;
        this.invalidateOptionsMenu();
    }

    public VehycleInfo getVehycleInfo() {
        return vehycleInfo;
    }

    @Override
    protected String getToolbarTitle() {
        if (modeIn) {
            return "ENTRADA";
        } else {
            return "SAÍDA";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (modeIn) {
            if (currentFragment instanceof TransactionInPlateFragment) {
                getMenuInflater().inflate(R.menu.menu_in, menu);

                if (currentFragment instanceof TransactionInPlateFragment) {
                    changeKeyboard = menu.findItem(R.id.menu_in_change_keyboard);
                    changeKeyboard.setIcon(
                            new IconicsDrawable(this, FontAwesome.Icon.faw_keyboard)
                                    .color(getResources().getColor(R.color.text_tertiary))
                                    .actionBar());
                    menu.findItem(R.id.menu_in_no_plate).setIcon(
                            new IconicsDrawable(this, FontAwesome.Icon.faw_minus_square1)
                                    .color(getResources().getColor(R.color.text_tertiary))
                                    .actionBar());
                }
            }
        } else {
            if (currentFragment instanceof TransactionOutPlateFragment ||
                    currentFragment instanceof TransactionOutTicketFragment) {

                getMenuInflater().inflate(R.menu.menu_out, menu);

                if (currentFragment instanceof TransactionOutPlateFragment) {
                    changeKeyboard = menu.findItem(R.id.menu_out_change_keyboard);
                    changeKeyboard.setIcon(
                            new IconicsDrawable(this, FontAwesome.Icon.faw_keyboard)
                                    .color(getResources().getColor(R.color.text_tertiary))
                                    .actionBar());

                    menu.findItem(R.id.menu_out_ticket).setVisible(true);
                    menu.findItem(R.id.menu_out_plate).setVisible(false);
                } else {
                    menu.findItem(R.id.menu_out_ticket).setVisible(false);
                    menu.findItem(R.id.menu_out_plate).setVisible(true);
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_out_plate) {
            currentFragment = replaceFragment(R.id.frame_layout, TransactionOutPlateFragment.newInstance());
        } else if (item.getItemId() == R.id.menu_out_ticket) {
            currentFragment = replaceFragment(R.id.frame_layout, TransactionOutTicketFragment.newInstance());
        }

        return super.onOptionsItemSelected(item);
    }

    public void showAgreement(Transaction transaction) {
        currentFragment = replaceFragment(R.id.frame_layout, TransactionOutAgreementFragment.newInstance(transaction.getIdentifier()));
    }
}
