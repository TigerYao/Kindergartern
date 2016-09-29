package com.junbaole.kindergartern.presentation.contact;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.utils.event.UserInfoEvent;
import com.junbaole.kindergartern.databinding.ActivitySearchGroupBinding;
import com.junbaole.kindergartern.presentation.adapter.ContactAdapter;
import com.junbaole.kindergartern.presentation.base.BaseActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

//搜索
public class SearchActivity extends BaseActivity implements OnClickListener {

    ActivitySearchGroupBinding searchGroupBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        searchGroupBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_group);
        super.onCreate(savedInstanceState);
        searchGroupBinding.search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searchGroupBinding.searchLayout.txtSearch.getText()))
                    actionManager.queryByPhone(searchGroupBinding.searchLayout.txtSearch.getText().toString());
            }
        });
    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        // TODO 根据时间排序加载 订阅号信息列表
    }

    @Override
    protected void setListener() {}

    @Override
    public void onClick(View v) {
        finish();
    }

    @Subscribe
    public void queryResult(UserInfoEvent event){
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(event.userInfo);
        ContactAdapter adapter = new ContactAdapter(this,userInfos,false);
        searchGroupBinding.list.setAdapter(adapter);
    }
}
