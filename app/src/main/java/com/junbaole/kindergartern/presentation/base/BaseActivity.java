package com.junbaole.kindergartern.presentation.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.domain.ActionManager;
import com.junbaole.kindergartern.domain.SendPhoneEvent;
import com.junbaole.kindergartern.presentation.register.RealInfoActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class BaseActivity extends AppCompatActivity {

    public ActionManager actionManager,secondActionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        actionManager = ActionManager.getInstance(this);
        secondActionManager = ActionManager.getSencondIntent(this);
        init();
    }

    public UserInfo getUserInfo() {

        return ((BaseApplication)getApplication()).getUserInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMainEvent(final SendPhoneEvent obje) {
        if (obje.type == 5) {
            finish();
        } else if (obje.type == 6) {
            new MaterialDialog.Builder(this).content("你已经与宝贝的老师成为好友了，现在让我们一起关注宝贝在幼儿园的表现吧").show();
        }
    }

    /**
     * 绑定控件id
     */
    protected  void initControl(){};

    /**
     * 初始化控件
     */
    protected  void initView(){};

    /**
     * 初始化数据
     */
    protected  void initData(){};

    /**
     * 设置监听
     */
    protected  void setListener(){};

    private void init(){
        initControl();
        initView();
        initData();
        setListener();
    }

}
