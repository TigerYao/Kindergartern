package com.junbaole.kindergartern.presentation.contact;


import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.presentation.adapter.ContactAdapter;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.widget.SideBar;

//订阅号列表
public class PublishUserListActivity extends BaseActivity implements
		OnClickListener {

	private ListView lvContact;
	private SideBar indexBar;
	private TextView mDialogText;
	private WindowManager mWindowManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
//		setContentView(R.layout.activity_publicmsglist);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initControl() {

	}

	@Override
	protected void initView() {
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		lvContact = (ListView) findViewById(R.id.lvContact);

		mDialogText = (TextView) LayoutInflater.from(this).inflate(
				R.layout.list_position, null);
		mDialogText.setVisibility(View.INVISIBLE);
		indexBar = (SideBar) findViewById(R.id.sideBar);
		indexBar.setListView(lvContact);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
						| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		mWindowManager.addView(mDialogText, lp);
		indexBar.setTextView(mDialogText);
		View layout_head = getLayoutInflater().inflate(
				R.layout.layout_head_search, null);
		lvContact.addHeaderView(layout_head);
		lvContact.setAdapter(new ContactAdapter(this, null));
	}

	@Override
	protected void initData() {
	}

	@Override
	protected void setListener() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		default:
			break;
		}
	}

}
