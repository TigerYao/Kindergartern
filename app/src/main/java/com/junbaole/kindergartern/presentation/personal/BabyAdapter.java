package com.junbaole.kindergartern.presentation.personal;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.utils.ScreenUtils;
import com.junbaole.kindergartern.data.utils.activity.AppInfo;
import com.junbaole.kindergartern.data.utils.zxing.utils.AppliationUtil;
import com.junbaole.kindergartern.databinding.BabyaddLayoutBinding;

/**
 * Created by yaohu on 16/10/13.
 */

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.DefaultViewHolder> {

    @Override
    public DefaultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 2){
            return new DefaultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.babyadd_layout,null));
        }
        return new BabyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.babyitem_layout,null));
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        if(getItemViewType(position) == 2){
            holder.bindClick();
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return 2;
        }
        return super.getItemViewType(position);
    }

    class BabyViewHolder extends DefaultViewHolder{
        public BabyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder{
        BabyaddLayoutBinding babyaddLayoutBinding;
        public DefaultViewHolder(View itemView) {
            super(itemView);
            babyaddLayoutBinding = DataBindingUtil.bind(itemView);
            itemView.setMinimumWidth(ScreenUtils.width);
        }

        public void bindClick(){
            babyaddLayoutBinding.setClickHandler(new BabyClickHandler());
        }
    }

}
