package com.junbaole.kindergartern.presentation.register;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.ParentAuthVO;
import com.junbaole.kindergartern.data.model.ShooleInfo;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.databinding.ActivityRealInfoBinding;
import com.junbaole.kindergartern.domain.SendPhoneEvent;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.presentation.login.LoginActivity;
import com.junbaole.kindergartern.presentation.main.MainActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class RealInfoActivity extends BaseActivity {

    ActivityRealInfoBinding realInfoBinding;
    RealInfoClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_real_info);
        ParentAuthVO parentAuthVO = new ParentAuthVO();
        parentAuthVO.phone_num = getIntent().getStringExtra("phone_num");
        clickHandler = new RealInfoClickHandler(this, parentAuthVO);
        realInfoBinding.setClickHandler(clickHandler);
        realInfoBinding.setParentInfo(parentAuthVO);
        new TitleBuilder(realInfoBinding.titleBar).TitleBuilderLayout(true, false).TitleBuilderLeftItem(true, false).TitleBuilderLable("实名认证", "", "").build();
    }


    @Subscribe
    public void onCallBackShoole(ArrayList<ShooleInfo> shooleInfos) {
        clickHandler.showShoolList(shooleInfos);
    }

    @Subscribe
    public void onCallBackSave(SendPhoneEvent sendPhoneEvent){
        if(sendPhoneEvent.type == 7){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("phoneNum", sendPhoneEvent.successMsg);
            SkipActivityUtils.startActivity(this, null, "main_activity", intent);
            finish();
        }
    }
}
