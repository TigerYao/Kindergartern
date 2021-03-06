package com.junbaole.kindergartern.presentation.base;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.model.UserInfo;
import com.junbaole.kindergartern.data.utils.ScreenUtils;
import com.junbaole.kindergartern.data.utils.SharedPreferenceUtil;
import com.junbaole.kindergartern.data.utils.chatutil.ChatUtil;
import com.tencent.TIMGroupReceiveMessageOpt;
import com.tencent.TIMManager;
import com.tencent.TIMOfflinePushListener;
import com.tencent.TIMOfflinePushNotification;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

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
        if(MsfSdkUtils.isMainProcess(this)) {
            TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {
                @Override
                public void handleNotification(TIMOfflinePushNotification notification) {
                    if (notification.getGroupReceiveMsgOpt() == TIMGroupReceiveMessageOpt.ReceiveAndNotify){
                        //消息被设置为需要提醒
                        notification.doNotify(getApplicationContext(), R.mipmap.ic_launcher);
                    }
                }
            });
        }
        ChatUtil.initTIM(this);
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
