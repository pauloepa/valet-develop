package com.martins.valet.app.features.transaction.in;

import android.graphics.Color;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.animation.AlphaAnimation;

import com.martins.valet.R;
import com.martins.valet.Utils.UI.BaseFragment;
import com.martins.valet.app.features.transaction.TransactionActivity;
import com.martins.valet.app.features.transaction.TransactionComponent;
import com.martins.valet.app.features.transaction.in.adapter.ColorAdapter;
import com.martins.valet.presentation.Helpers.BasePresenter;
import com.martins.valet.presentation.features.transaction.in.TransactionColorModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by policante on 7/9/16.
 */
public class TransactionInColorFragment extends BaseFragment implements ColorAdapter.OnMenuClickListener {

    @BindView(R.id.transaction_in_color_recycler)
    RecyclerView recyclerView;
    ColorAdapter adapter;
    List<TransactionColorModel> list;

    public static TransactionInColorFragment newInstance() {
        Bundle args = new Bundle();
        TransactionInColorFragment fragment = new TransactionInColorFragment();
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
        return R.layout.transaction_in_color_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void injectDependencies() {
        getComponent(TransactionComponent.class).inject(this);
    }

    @Override
    protected void initializeInjectedMembers() {
        setup();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAnimation(new AlphaAnimation(0, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ColorAdapter(this.list);
        adapter.setOnMenuClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void teardownView() {

    }

    private void setup(){
        list = new ArrayList<>();

        TransactionColorModel silver = new TransactionColorModel();
        silver.setColor(Color.parseColor("#CCCCCC"));
        silver.setTitle("Prata");
        list.add(silver);

        TransactionColorModel white = new TransactionColorModel();
        white.setColor(Color.parseColor("#FFFFFF"));
        white.setTitle("Branco");
        list.add(white);

        TransactionColorModel gray = new TransactionColorModel();
        gray.setColor(Color.parseColor("#666666"));
        gray.setTitle("Cinza");
        list.add(gray);

        TransactionColorModel black = new TransactionColorModel();
        black.setColor(Color.parseColor("#000000"));
        black.setTitle("Preto");
        list.add(black);

        TransactionColorModel red = new TransactionColorModel();
        red.setColor(Color.parseColor("#ff0000"));
        red.setTitle("Vermelho");
        list.add(red);

        TransactionColorModel green = new TransactionColorModel();
        green.setColor(Color.parseColor("#00ff00"));
        green.setTitle("Verde");
        list.add(green);

        TransactionColorModel yellow = new TransactionColorModel();
        yellow.setColor(Color.parseColor("#FFFF00"));
        yellow.setTitle("Amarelo");
        list.add(yellow);

        TransactionColorModel blue = new TransactionColorModel();
        blue.setColor(Color.parseColor("#0000ff"));
        blue.setTitle("Azul");
        list.add(blue);

        TransactionColorModel other = new TransactionColorModel();
        other.setColor(-1);
        other.setTitle("Outro");
        list.add(other);
    }

    @Override
    public void onClickMenu(TransactionColorModel typeModel) {
        if (typeModel != null){
            ((TransactionActivity)getActivity()).onNextStep(this, typeModel.getTitle());
        }
    }
}
