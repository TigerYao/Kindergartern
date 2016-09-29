package com.junbaole.kindergartern.presentation.contact;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.Location;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.utils.activity.AppInfo;
import com.junbaole.kindergartern.data.utils.chatutil.ChatUtil;
import com.junbaole.kindergartern.databinding.ActivityAddFriendBinding;
import com.junbaole.kindergartern.presentation.base.BaseActivity;

public class ContactInfoActivity extends BaseActivity {

    ActivityAddFriendBinding addFriendBinding;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = getIntent().getParcelableExtra("userInfo");
        addFriendBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_friend);
        addFriendBinding.setUserInfo(userInfo);
        addFriendBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatUtil.addFriend(userInfo.phoneNum,"添加好友","new friend");
                actionManager.addFriend(getUserInfo().id,userInfo.id);
                ChatUtil.createSingleConvertsation(userInfo.token);
                ChatUtil.sendMessage("nihao");
            }
        });
    }
}
