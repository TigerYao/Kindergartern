package com.junbaole.kindergartern.presentation.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.databinding.AdapterTextContentHomeBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;

/**
 * Created by liangrenwang on 16/6/21.
 */
public class RecorderAdapter extends RecyclerView.Adapter<RecorderAdapter.RecorderViewHolder> {

    private Context ctx;

    @Override
    public RecorderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_text_content_home,null);
        ctx = parent.getContext();
        return new RecorderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecorderViewHolder holder, int position) {
        holder.bindData();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class RecorderViewHolder extends RecyclerView.ViewHolder {
        AdapterTextContentHomeBinding homeBinding;
        public RecorderViewHolder(View itemView) {
            super(itemView);
            homeBinding = DataBindingUtil.bind(itemView);
        }

        public void bindData(){
            HomeClickHandler homeClickHandler = new HomeClickHandler((BaseActivity)ctx);
            homeClickHandler.initPPW(ctx);
            homeBinding.setClick(homeClickHandler);
        }
    }
}
