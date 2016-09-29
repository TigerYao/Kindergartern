package com.junbaole.kindergartern.presentation.base;

import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.utils.ScreenUtils;
import com.junbaole.kindergartern.data.utils.SharedPreferenceUtil;
import com.junbaole.kindergartern.data.utils.chatutil.ChatUtil;

import android.app.Application;
import android.content.Context;

/**
 * Created by liangrenwang on 16/4/25.
 */
public class BaseApplication extends Application{

    public UserInfo userInfo;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ScreenUtils.initDisplay(this);
        ChatUtil.initTIM(context);
        ChatUtil.addListeners();
    }

    public UserInfo getUserInfo() {
        if(userInfo==null||userInfo.id==-1){
            userInfo = SharedPreferenceUtil.getUserInfo(this);
        }
        return userInfo;
    }

    public static Context getContext() {
        return context;
    }
}
