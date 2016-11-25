package com.junbaole.kindergartern.presentation.adapter;

import java.util.ArrayList;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.NewFriendModle;
import com.junbaole.kindergartern.presentation.comment.ViewHolder;
import com.junbaole.kindergartern.presentation.contact.NewFriendsListActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewFriendsAdapter extends BaseAdapter {
    protected Context context;
    private ArrayList<NewFriendModle.NewFriendInfo> newFriendInfos;

    public NewFriendsAdapter(Context ctx) {
        context = ctx;
    }

    public void setNewFriendInfos(ArrayList<NewFriendModle.NewFriendInfo> newFriendInfos) {
        this.newFriendInfos = newFriendInfos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return newFriendInfos == null ? 0 : newFriendInfos.size();
    }

    @Override
    public NewFriendModle.NewFriendInfo getItem(int position) {
        return newFriendInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final NewFriendModle.NewFriendInfo friendInfo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.layout_item_newfriend, parent, false);
        }
        ImageView img_avar = ViewHolder.get(convertView, R.id.img_photo);
        TextView txt_name = ViewHolder.get(convertView, R.id.txt_name);
        final TextView txt_add = ViewHolder.get(convertView, R.id.txt_add);
        txt_name.setText(friendInfo.rel_name);
        if (friendInfo.as_confirm) {
            txt_add.setText("已添加");
            txt_add.setEnabled(false);
            txt_add.setClickable(false);
            txt_add.setTextColor(context.getResources().getColor(
                    R.color.s_color11));
            txt_add.setBackgroundColor(context.getResources().getColor(R.color.s_color14));
        } else {
            txt_add.setBackgroundColor(context.getResources().getColor(R.color.s_color2));
            txt_add.setText("接受");
            txt_add.setEnabled(true);
            txt_add.setClickable(true);
            txt_add.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((NewFriendsListActivity)context).actionManager.acceptShip(friendInfo.rel_id2);
                }
            });
        }
        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {}
        });
        return convertView;
    }
}
