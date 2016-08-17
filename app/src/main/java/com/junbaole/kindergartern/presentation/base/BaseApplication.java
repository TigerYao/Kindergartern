package com.junbaole.kindergartern.presentation.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.utils.ScreenUtils;
import com.junbaole.kindergartern.data.utils.SharedPreferenceUtil;

/**
 * Created by liangrenwang on 16/4/25.
 */
public class BaseApplication extends Application{

    public UserInfo userInfo;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        ScreenUtils.initDisplay(this);
    }

    public UserInfo getUserInfo() {
        if(userInfo==null){
            userInfo = SharedPreferenceUtil.getUserInfo(this);
        }
        return userInfo;
    }
}
