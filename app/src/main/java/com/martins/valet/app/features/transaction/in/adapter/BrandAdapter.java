package com.martins.valet.app.features.transaction.in.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.martins.valet.R;
import com.martins.valet.Utils.Helpers.ResourceHelper;
import com.martins.valet.domain.features.model.BrandVehycle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by policante on 7/10/16.
 */
public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {

    List<BrandVehycle> list;
    private OnMenuClickListener onMenuClickListener;

    public BrandAdapter(List<BrandVehycle> list) {
        this.list = list;
    }

    @Override
    public BrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_in_brand_item, parent, false);
        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BrandViewHolder holder, int position) {
        BrandVehycle model = list.get(position);
        holder.model = model;
        holder.setTitle(null);
        int icon = ResourceHelper.loadDrawableResource(holder.itemView.getContext(), model.getIcon());
        holder.setIcon(icon);
        if (icon == 0) {
            holder.setTitle(model.getName());
        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    public interface OnMenuClickListener {
        void onClickMenu(BrandVehycle typeModel);
    }

    public class BrandViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        BrandVehycle model;

        @BindView(R.id.item_brand_menu_imageview_icon)
        ImageView icon;
        @BindView(R.id.item_brand_menu_textview_title)
        TextView title;

        public BrandViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setIcon(int resource) {
            Glide.with(itemView).clear(icon);
            if (resource == 0) {
                this.icon.setImageDrawable(null);
                return;
            }

            Glide.with(itemView)
                    .load(resource)
                    .into(this.icon);
        }

        public void setTitle(String value) {
            this.title.setText(value);
        }

        @Override
        public void onClick(View v) {
            if (onMenuClickListener != null) {
                onMenuClickListener.onClickMenu(this.model);
            }
        }

    }

}
