package com.martins.valet.app.features.home.adapter;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.martins.valet.R;
import com.martins.valet.presentation.features.home.HomeMenu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by policante on 7/5/16.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<HomeMenu> menuList;
    private OnMenuClickListener onMenuClickListener;

    public HomeAdapter(List<HomeMenu> menus) {
        this.menuList = menus;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_menu, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        HomeMenu menu = menuList.get(position);
        holder.menu = menu;
        holder.itemView.setEnabled(menu.isEnabled());
        holder.setTitle(menu.getTitle());
        holder.setIcon(menu.getIcon());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }

    public interface OnMenuClickListener {
        void onClickMenu(HomeMenu menu);
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        HomeMenu menu;

        @BindView(R.id.item_home_menu_imageview_icon)
        ImageView icon;
        @BindView(R.id.item_home_menu_textview_title)
        TextView title;

        public HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setIcon(@DrawableRes int icon) {
            this.icon.setImageResource(icon);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        @Override
        public void onClick(View v) {
            if (onMenuClickListener != null) {
                onMenuClickListener.onClickMenu(this.menu);
            }
        }
    }

}
