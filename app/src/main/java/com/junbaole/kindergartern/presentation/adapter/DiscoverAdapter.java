package com.junbaole.kindergartern.presentation.adapter;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.DiscoverModel;
import com.junbaole.kindergartern.databinding.FindItemLayoutBinding;
import com.junbaole.kindergartern.databinding.ItemLayoutBinding;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yaohu on 16/9/27.
 */

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder> {

    private ArrayList<DiscoverModel> discoverModels;

    public DiscoverAdapter() {
        this.discoverModels = getDiscoverModels();
    }

    @Override
    public DiscoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiscoverViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.find_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, int position) {
        holder.bindData(discoverModels.get(position));
    }

    @Override
    public int getItemCount() {
        return discoverModels == null ? 0 : discoverModels.size();
    }

    class DiscoverViewHolder extends RecyclerView.ViewHolder {
        FindItemLayoutBinding itemLayoutBinding;
        public DiscoverViewHolder(View itemView) {
            super(itemView);
            itemLayoutBinding = DataBindingUtil.bind(itemView);
        }

        public void bindData(DiscoverModel discoverModel){
            itemLayoutBinding.setDatainfo(discoverModel);
            itemLayoutBinding.tabIcon.setImageResource(discoverModel.imgId);
        }
    }

    public ArrayList<DiscoverModel> getDiscoverModels() {
        discoverModels = new ArrayList<>();
        discoverModels.add(new DiscoverModel("签到记录","每天出勤用心记录",R.mipmap.icon_qiandao));
        discoverModels.add(new DiscoverModel("积分商城","精美礼物积分兑换",R.mipmap.icon_shangcheng));
        discoverModels.add(new DiscoverModel("3D教学 ","教学场景真实还原",R.mipmap.icon_3djiaoxue));
        discoverModels.add(new DiscoverModel("育儿知识","专业知识健康陪伴",R.mipmap.icon_mybabybig));
        discoverModels.add(new DiscoverModel("培训信息","宝贝特长着重培养",R.mipmap.icon_jiaoyuxinxi));
        discoverModels.add(new DiscoverModel("校园新闻","校园资讯及时关注",R.mipmap.icon_xiaoyuanxinwen));
        discoverModels.add(new DiscoverModel("实时监控","网络关心每时每刻",R.mipmap.icon_shishijiankong));
        discoverModels.add(new DiscoverModel("校园周边","周边校园对比查看",R.mipmap.icon_zhoubianxiao));
        return discoverModels;
    }
}
