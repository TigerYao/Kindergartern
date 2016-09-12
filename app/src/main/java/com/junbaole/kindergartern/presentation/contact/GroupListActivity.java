package com.junbaole.kindergartern.presentation.contact;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.presentation.base.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

//群聊列表
public class GroupListActivity extends BaseActivity implements OnClickListener {
	private TextView txt_title;
	private ListView mlistview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_listview);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initControl() {
		txt_title = (TextView) findViewById(R.id.txt_title);
		txt_title.setText("群聊");
		mlistview = (ListView) findViewById(R.id.listview);
		View layout_head = getLayoutInflater().inflate(
				R.layout.layout_head_search, null);
		mlistview.addHeaderView(layout_head);
	}

	@Override
	protected void initView() {

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
