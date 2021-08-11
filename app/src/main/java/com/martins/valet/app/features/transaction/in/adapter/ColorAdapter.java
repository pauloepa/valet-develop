package com.martins.valet.app.features.transaction.in.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.martins.valet.R;
import com.martins.valet.presentation.features.transaction.in.TransactionColorModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by policante on 7/10/16.
 */
public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    private List<TransactionColorModel> list;
    private OnMenuClickListener onMenuClickListener;

    public ColorAdapter(List<TransactionColorModel> typeList) {
        this.list = typeList;
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_in_color_item, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        TransactionColorModel model = list.get(position);
        holder.color = model;
        if (model.getColor() == -1) {
            holder.setTitle(model.getTitle());
        }else{
            holder.setColor(model.getColor());
        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public interface OnMenuClickListener {
        void onClickMenu(TransactionColorModel typeModel);
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_color_menu_relative)
        RelativeLayout relativeLayout;
        @BindView(R.id.item_color_menu_textview_title)
        TextView title;
        private TransactionColorModel color;

        public ColorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setTitle(String value) {
            this.title.setText(value);
        }

        public void setColor(int color) {
            relativeLayout.setBackgroundColor(color);
        }

        @Override
        public void onClick(View v) {
            if (onMenuClickListener != null) {
                onMenuClickListener.onClickMenu(this.color);
            }
        }
    }

}
