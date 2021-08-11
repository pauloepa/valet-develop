package com.martins.valet.app.features.reports.revenuew.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martins.valet.R;
import com.martins.valet.domain.features.model.ReportAgreement;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by policante on 8/15/16.
 */
public class ReportsRevenueAdapter extends RecyclerView.Adapter<ReportsRevenueAdapter.ReportsRevenueViewHolder>{

    private List<ReportAgreement> list;

    public ReportsRevenueAdapter(List<ReportAgreement> list) {
        this.list = list;
    }

    @Override
    public ReportsRevenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reports_revenue_item, parent, false);
        return new ReportsRevenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportsRevenueViewHolder holder, int position) {
        ReportAgreement model = this.list.get(position);
        holder.name.setText(model.getAgreement());
        holder.quantity.setText(String.valueOf(model.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ReportsRevenueViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.reports_revenue_item_quantity)
        TextView quantity;
        @BindView(R.id.reports_revenue_item_name)
        TextView name;

        public ReportsRevenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
