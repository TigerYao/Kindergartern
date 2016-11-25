package com.junbaole.kindergartern.presentation.send;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.utils.ScreenUtils;
import com.junbaole.kindergartern.widget.ImageSelectorView.ImageSelectorActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by yaohu on 16/7/22.
 */
public class SendImgsAdapter extends BaseAdapter {

    private ArrayList<ImageInfo> datas;
    public Context ctx;
    private boolean isHome;

    public SendImgsAdapter( boolean isHome, Context ctx, ArrayList<ImageInfo> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        if (!isHome)
            datas.add(null);
        else {
            datas.remove(null);
        }
        this.isHome = isHome;
        this.datas = datas;
        this.ctx = ctx;
    }

    public SendImgsAdapter( Context ctx, ArrayList<ImageInfo> datas) {
        this(false, ctx, datas);
    }

    @Override
    public int getCount() {
        return isHome ? Math.min(datas.size(), 9) : datas.size();
    }

    @Override
    public Uri getItem(int i) {
        if (datas.get(i) != null) {
            return datas.get(i).getImgUri();
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final SendImgsViewHolder holder = onCreateViewHolder();
//        holder.draweeView.getLayoutParams().height = holder.draweeView.getLayoutParams().width = (ScreenUtils.width / 5);
        if ((!isHome && position == getCount() - 1)) {
            holder.draweeView.setImageResource(R.mipmap.fasong_tianjia);
        } else {
            Glide.with(ctx).load(getItem(position)).placeholder(R.mipmap.lt_icon_tupian).into(holder.draweeView);
        }
        if (position == getCount() - 1 && !isHome)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), ImageSelectorActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        return holder.itemView;
    }

    public void addImgUrl(ImageInfo imgPaths) {
        this.datas.add(0, imgPaths);
        notifyDataSetChanged();
    }

    public void addImgs(ArrayList<ImageInfo> imgs) {
        if (imgs != null && imgs.size() > 0) {
            if (!isHome)
                datas.remove(datas.size() - 1);
            datas.addAll(imgs);
            if (!isHome)
                datas.add(null);
            notifyDataSetChanged();
        }
    }

    public void setDatas(ArrayList<ImageInfo> datas) {
        this.datas = datas;
        this.datas.remove(null);
        this.datas.add(null);
        notifyDataSetChanged();
    }

    public void setHomeDatas(ArrayList<ImageInfo> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public ArrayList<ImageInfo> getDatas() {
        return datas;
    }

    public SendImgsViewHolder onCreateViewHolder() {
        View view = LayoutInflater.from(ctx).inflate(R.layout.send_img_item, (ViewGroup)null, false);
        return new SendImgsViewHolder(view);
    }

    class SendImgsViewHolder {
        ImageView draweeView;
        View itemView;

        public SendImgsViewHolder(final View itemView) {
            this.itemView = itemView;
            draweeView = (ImageView)itemView.findViewById(R.id.my_image_view);

        }
    }
}
