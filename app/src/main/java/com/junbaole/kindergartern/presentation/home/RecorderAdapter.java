package com.junbaole.kindergartern.presentation.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.DiaryDetailInfo;
import com.junbaole.kindergartern.databinding.AdapterTextContentHomeBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.detail.DiaryDetailActivity;
import com.junbaole.kindergartern.presentation.send.SendImgsAdapter;

import java.util.ArrayList;

/**
 * Created by liangrenwang on 16/6/21.
 */
public class RecorderAdapter extends RecyclerView.Adapter<RecorderAdapter.RecorderViewHolder> {

    private Context ctx;
    private ArrayList<DiaryDetailInfo> detailInfoArrayList = new ArrayList<>();
    private boolean mIsDiary;

    @Override
    public RecorderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_text_content_home, null);
        ctx = parent.getContext();
        return new RecorderViewHolder(view);
    }

    public void setDetailInfoArrayList(ArrayList<DiaryDetailInfo> detailInfoArrayList,boolean isDiary) {
        if (detailInfoArrayList == null) {
            return;
        }
        if (this.detailInfoArrayList.size() > 0) {
            this.detailInfoArrayList.clear();
        }
        this.mIsDiary = isDiary;
        this.detailInfoArrayList.addAll(detailInfoArrayList);
        notifyDataSetChanged();
    }

    public void addDetails(ArrayList<DiaryDetailInfo> detailInfoArrayList) {
        if (detailInfoArrayList != null && detailInfoArrayList.size() > 0) {
            this.detailInfoArrayList.addAll(detailInfoArrayList);
        }
        notifyItemRangeInserted(getItemCount(),detailInfoArrayList.size());
    }

    @Override
    public void onBindViewHolder(RecorderViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return detailInfoArrayList == null ? 0 : detailInfoArrayList.size();
    }

    public DiaryDetailInfo getItem(int position) {
        if (position >= getItemCount()) {
            return null;
        }
        return detailInfoArrayList.get(position);
    }

    class RecorderViewHolder extends RecyclerView.ViewHolder {
        AdapterTextContentHomeBinding homeBinding;

        public RecorderViewHolder(View itemView) {
            super(itemView);
            homeBinding = DataBindingUtil.bind(itemView);
            if(mIsDiary){
                homeBinding.homeContentDianzan.setVisibility(View.INVISIBLE);
            }
        }

        public void bindData(final DiaryDetailInfo diaryDetailInfo) {
            diaryDetailInfo.isDiary = mIsDiary;
            HomeClickHandler homeClickHandler = new HomeClickHandler((BaseActivity)ctx);
            homeClickHandler.setDiaryDetailInfo(diaryDetailInfo);
            homeClickHandler.initPPW(ctx);
            homeBinding.setClick(homeClickHandler);
            homeBinding.setDiaryInfo(diaryDetailInfo);
            itemView.setTag(diaryDetailInfo);
            if (diaryDetailInfo.image_list != null && diaryDetailInfo.image_list.size() > 0) {
                homeBinding.imgList.setVisibility(View.VISIBLE);
                SendImgsAdapter adapter = new SendImgsAdapter(true,ctx, diaryDetailInfo.image_list);
                homeBinding.imgList.setAdapter(adapter);
//                if (homeBinding.imgList.getAdapter() != null) {
//                    ((SendImgsAdapter)homeBinding.imgList.getAdapter()).setHomeDatas(diaryDetailInfo.image_list);
//                } else {
//
//                }
            } else {
                homeBinding.imgList.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DiaryDetailActivity.class);
                    intent.putExtra("diaryDetailInfo", diaryDetailInfo);
                    intent.putExtra("isDiary",mIsDiary);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
