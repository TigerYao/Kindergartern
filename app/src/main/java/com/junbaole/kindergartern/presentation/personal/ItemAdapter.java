package com.junbaole.kindergartern.presentation.personal;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.utils.ScreenUtils;
import com.junbaole.kindergartern.databinding.ItemLayoutBinding;

import java.util.List;


/**
 * Created by TigerYao on 16/7/24.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<String> items;
    private OnItemClickListener itemClickListener;

    public ItemAdapter(List<String> items) {
        this.items = items;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_layout, null, false);
        ItemViewHolder viewHolder = new ItemViewHolder(itemLayoutBinding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.onBindData(getItem(position));
    }

    public String getItem(int postion) {
        return items.get(postion);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding itemLayoutBinding;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemLayoutBinding = DataBindingUtil.getBinding(itemView);
            itemView.setMinimumWidth(ScreenUtils.width);

        }

        public void onBindData(String title) {
            itemLayoutBinding.setTitle(title);
            itemLayoutBinding.setClickHandler(new ClickHandler(getLayoutPosition()));
        }
    }

    public class ClickHandler {
        int position;

        public ClickHandler(int position) {
            this.position = position;
        }

        public void onItemClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }
}
