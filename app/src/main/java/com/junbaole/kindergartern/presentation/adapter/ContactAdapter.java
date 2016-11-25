package com.junbaole.kindergartern.presentation.adapter;

import java.util.Collections;
import java.util.List;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.presentation.chat.ChatActivity;
import com.junbaole.kindergartern.presentation.comment.PingYinUtil;
import com.junbaole.kindergartern.presentation.comment.PinyinComparator;
import com.junbaole.kindergartern.presentation.comment.ViewHolder;
import com.junbaole.kindergartern.presentation.contact.ContactInfoActivity;
import com.tencent.TIMConversationType;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter implements SectionIndexer {
    private Context mContext;
    private List<UserInfo> UserInfoInfos;// 好友信息
    private boolean isShowTipe = true;

    public ContactAdapter(Context mContext, List<UserInfo> UserInfoInfos, boolean showTipe) {
        this.mContext = mContext;
        this.UserInfoInfos = UserInfoInfos;
        this.isShowTipe = showTipe;
        if (isShowTipe)
            Collections.sort(UserInfoInfos, new PinyinComparator());
    }

    private void setShowTipe(boolean showTipe) {

    }

    @Override
    public int getCount() {
        return UserInfoInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return UserInfoInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final UserInfo userInfo = UserInfoInfos.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.contact_item, null);

        }
        ImageView ivAvatar = ViewHolder.get(convertView,
                R.id.contactitem_avatar_iv);
        TextView tvCatalog = ViewHolder.get(convertView,
                R.id.contactitem_catalog);
        TextView tvNick = ViewHolder.get(convertView, R.id.contactitem_nick);
        String catalog = PingYinUtil.converterToFirstSpell(userInfo.name)
                .substring(0, 1);
        if (position == 0 && isShowTipe) {
            tvCatalog.setVisibility(View.VISIBLE);
            tvCatalog.setText(catalog);
        } else if (position > 0) {
            UserInfo NextUserInfo = UserInfoInfos.get(position - 1);
            String lastCatalog = PingYinUtil.converterToFirstSpell(
                    NextUserInfo.name).substring(0, 1);
            if (catalog.equals(lastCatalog)) {
                tvCatalog.setVisibility(View.GONE);
            } else if (isShowTipe) {
                tvCatalog.setVisibility(View.VISIBLE);
                tvCatalog.setText(catalog);
            }
        }

        ivAvatar.setImageResource(R.mipmap.top_nar_touxiang);
        tvNick.setText(userInfo.name);
        convertView.findViewById(R.id.contactitem_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowTipe) {
                    Intent intent = new Intent(mContext, ContactInfoActivity.class);
                    intent.putExtra("userInfo", userInfo);
                    mContext.startActivity(intent);
                } else
                    ChatActivity.navToChat(mContext, userInfo.phoneNum, TIMConversationType.C2C);
            }
        });
        return convertView;
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < UserInfoInfos.size(); i++) {
            UserInfo UserInfo = UserInfoInfos.get(i);
            String l = PingYinUtil.converterToFirstSpell(UserInfo.name)
                    .substring(0, 1);
            char firstChar = l.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
