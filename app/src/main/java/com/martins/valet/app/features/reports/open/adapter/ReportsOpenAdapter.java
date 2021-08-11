package com.martins.valet.app.features.reports.open.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martins.valet.R;
import com.martins.valet.Utils.Helpers.MaskHelper;
import com.martins.valet.domain.features.model.Transaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by policante on 7/31/16.
 */
public class ReportsOpenAdapter extends RecyclerView.Adapter<ReportsOpenAdapter.ReportsOpenViewHolder> {

    private List<Transaction> list;
    private EmptyListener emptyListener;
    private ClickListener clickListener;

    public ReportsOpenAdapter(List<Transaction> list) {
        this.list = list;
    }

    public void setEmptyListener(EmptyListener emptyListener) {
        this.emptyListener = emptyListener;
    }

    @Override
    public ReportsOpenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reports_open_item, parent, false);
        return new ReportsOpenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportsOpenViewHolder holder, int position) {
        Transaction transaction = this.list.get(position);

        holder.transaction = transaction;
        holder.ticket.setText(transaction.getIdentifier());
        holder.plate.setText(MaskHelper.plateMask(transaction.getVehycle().getPlate()));
        holder.brand.setText(transaction.getVehycle().getBrand());
        holder.color.setText(transaction.getVehycle().getColor());
        holder.setTypeMensal(transaction.isMensal());
    }

    @Override
    public int getItemCount() {
        emptyListener.onListEmpty(this.list == null || this.list.size() <= 0);
        return this.list.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface EmptyListener {
        void onListEmpty(boolean empty);
    }

    public interface ClickListener {
        void onClickCloseTransaction(Transaction transaction);
        void onLongClickTransaction(Transaction transaction);
    }

    public class ReportsOpenViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        Transaction transaction;

        @BindView(R.id.reports_open_client_type)
        TextView type;
        @BindView(R.id.reports_open_ticket)
        TextView ticket;
        @BindView(R.id.reports_open_plate)
        TextView plate;
        @BindView(R.id.reports_open_brand)
        TextView brand;
        @BindView(R.id.reports_open_color)
        TextView color;

        public ReportsOpenViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(this);
        }

        private void setTypeMensal(boolean mensal){
            if (mensal){
                type.setText("M");
                type.setBackgroundResource(R.color.button_error);
            }else{
                type.setText("A");
                type.setBackgroundResource(R.color.button_success);
            }
        }

        @OnClick(R.id.reports_open_close)
        public void onClick() {
            clickListener.onClickCloseTransaction(this.transaction);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onLongClickTransaction(this.transaction);
            return true;
        }
    }

}
