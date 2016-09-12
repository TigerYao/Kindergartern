package com.junbaole.kindergartern.presentation.adapter;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.presentation.comment.PingYinUtil;
import com.junbaole.kindergartern.presentation.comment.PinyinComparator;
import com.junbaole.kindergartern.presentation.comment.ViewHolder;


public class ContactAdapter extends BaseAdapter implements SectionIndexer {
	private Context mContext;
	private List<UserInfo> UserInfoInfos;// 好友信息

	public ContactAdapter(Context mContext, List<UserInfo> UserInfoInfos) {
		this.mContext = mContext;
		this.UserInfoInfos = UserInfoInfos;
		// 排序(实现了中英文混排)
		Collections.sort(UserInfoInfos, new PinyinComparator());
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
		UserInfo UserInfo = UserInfoInfos.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.contact_item, null);

		}
		ImageView ivAvatar = ViewHolder.get(convertView,
				R.id.contactitem_avatar_iv);
		TextView tvCatalog = ViewHolder.get(convertView,
				R.id.contactitem_catalog);
		TextView tvNick = ViewHolder.get(convertView, R.id.contactitem_nick);
		String catalog = PingYinUtil.converterToFirstSpell(UserInfo.name)
				.substring(0, 1);
		if (position == 0) {
			tvCatalog.setVisibility(View.VISIBLE);
			tvCatalog.setText(catalog);
		} else {
			UserInfo NextUserInfo = UserInfoInfos.get(position - 1);
			String lastCatalog = PingYinUtil.converterToFirstSpell(
					NextUserInfo.name).substring(0, 1);
			if (catalog.equals(lastCatalog)) {
				tvCatalog.setVisibility(View.GONE);
			} else {
				tvCatalog.setVisibility(View.VISIBLE);
				tvCatalog.setText(catalog);
			}
		}

		ivAvatar.setImageResource(R.mipmap.top_nar_touxiang);
		tvNick.setText(UserInfo.name);
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
