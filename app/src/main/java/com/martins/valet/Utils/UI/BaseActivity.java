package com.martins.valet.Utils.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.martins.valet.ValetApplication;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import butterknife.ButterKnife;

/**
 * Created by policante on 7/3/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);

        injectApplicationComponent();
        initializeInjector();

        setContentView(getActivityLayout());
        injectViews();

        initializeToolbar();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    protected void injectApplicationComponent() {
        getApplicationComponent().inject(this);
    }

    protected ValetApplicationComponent getApplicationComponent() {
        return ((ValetApplication) getApplication()).getApplicationComponent();
    }

    protected ValetActivityModule getActivityModule() {
        return new ValetActivityModule(this);
    }

    protected abstract void initializeInjector();

    protected abstract int getActivityLayout();

    protected boolean displayHome() {
        return true;
    }

    protected void initializeToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(displayHome());

            if (getToolbarTitle() != null) {
                getSupportActionBar().setTitle(getToolbarTitle());
            }

        }
    }

    protected String getToolbarTitle() {
        return null;
    }

    protected void injectViews() {
        ButterKnife.bind(this);
    }

    protected Fragment replaceFragment(int container, Fragment fragment) {
        return replaceFragment(container, fragment, null);
    }

    protected Fragment replaceFragment(int container, Fragment fragment, String tagBackstack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment);
        if (tagBackstack != null) {
            fragmentTransaction.addToBackStack(tagBackstack);
        }
        fragmentTransaction.commit();
        return fragment;
    }

    protected Fragment addFragment(int container, Fragment fragment) {
        return addFragment(container, fragment, null);
    }

    protected Fragment addFragment(int container, Fragment fragment, String tagBackstack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.add(container, fragment);
        if (tagBackstack != null) {
            fragmentTransaction.addToBackStack(tagBackstack);
        }
        fragmentTransaction.commit();

        return fragment;
    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        hideKeyboard();
        super.onBackPressed();
    }
}
