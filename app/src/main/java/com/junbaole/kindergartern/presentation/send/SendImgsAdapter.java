package com.junbaole.kindergartern.presentation.send;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ImageInfo;
import com.junbaole.kindergartern.data.model.SendMessageInfo;
import com.junbaole.kindergartern.widget.ImageSelectorView.ImageSelectorActivity;

import java.util.ArrayList;

/**
 * Created by yaohu on 16/7/22.
 */
public class SendImgsAdapter extends BaseAdapter {

    private ArrayList<ImageInfo> datas;
    public Context ctx;

    public SendImgsAdapter(Context ctx, ArrayList<ImageInfo> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        datas.add(null);
        this.datas = datas;
        this.ctx = ctx;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Uri getItem(int i) {
        if (datas.get(i) != null)
            return datas.get(i).getImgUri();
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final SendImgsViewHolder holder = onCreateViewHolder();
        holder.draweeView.setImageURI(getItem(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == getCount() - 1) {
                    Intent intent = new Intent(holder.itemView.getContext(), ImageSelectorActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }
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
            datas.remove(datas.size() - 1);
            datas.addAll(imgs);
            datas.add(null);
            notifyDataSetChanged();
        }
    }

    public void setDatas(ArrayList<ImageInfo> datas) {
        this.datas = datas;
        this.datas.add(null);
        notifyDataSetChanged();
    }

    public ArrayList<ImageInfo> getDatas() {
        return datas;
    }

    public SendImgsViewHolder onCreateViewHolder() {
        View view = LayoutInflater.from(ctx).inflate(R.layout.send_img_item, (ViewGroup) null, false);
        return new SendImgsViewHolder(view);
    }

    class SendImgsViewHolder {
        SimpleDraweeView draweeView;
        View itemView;

        public SendImgsViewHolder(final View itemView) {
            this.itemView = itemView;
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.my_image_view);

        }
    }
}
