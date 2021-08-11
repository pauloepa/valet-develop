package com.martins.valet.app.features.transaction.in.adapter;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.martins.valet.R;
import com.martins.valet.Utils.Helpers.ResourceHelper;
import com.martins.valet.domain.features.model.TypeVehycle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by policante on 7/9/16.
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    private List<TypeVehycle> typeList;

    private OnMenuClickListener onMenuClickListener;

    public TypeAdapter(List<TypeVehycle> typeList) {
        this.typeList = typeList;
    }

    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_in_type_item, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TypeViewHolder holder, int position) {
        TypeVehycle model = typeList.get(position);
        holder.type = model;
        holder.setTitle(model.getName());

        if (!TextUtils.isEmpty(model.getIcon())) {
            holder.setIcon(ResourceHelper.loadDrawableResource(holder.itemView.getContext(), model.getIcon()));
        }
    }

    @Override
    public int getItemCount() {
        return this.typeList.size();
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    public interface OnMenuClickListener {
        void onClickMenu(TypeVehycle typeModel);
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TypeVehycle type;

        @BindView(R.id.item_type_menu_imageview_icon)
        ImageView icon;
        @BindView(R.id.item_type_menu_textview_title)
        TextView title;

        public TypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setTitle(String value){
            this.title.setText(value);
        }

        public void setIcon(@DrawableRes int resource) {
            this.icon.setImageResource(resource);
        }

        @Override
        public void onClick(View v) {
            if (onMenuClickListener != null) {
                onMenuClickListener.onClickMenu(this.type);
            }
        }
    }

}
