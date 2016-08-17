package com.junbaole.kindergartern.presentation.launcher;

import android.content.Intent;
import android.view.View;

import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.presentation.login.LoginActivity;
import com.junbaole.kindergartern.presentation.main.MainActivity;
import com.junbaole.kindergartern.presentation.register.RegisterActivity;

/**
 * Created by liangrenwang on 16/5/31.
 */
public class LauncherClickHandler {

    protected LauncherActivity launcherActivity;

    public void onClickRegister(View view) {
        SkipActivityUtils.startActivity(launcherActivity,view,"register",new Intent(launcherActivity, RegisterActivity.class));
    }

    public void onClickLogin(View view) {
        SkipActivityUtils.startActivity(launcherActivity,view,"login",new Intent(launcherActivity, LoginActivity.class));
    }

    public void onClickVister(View view) {
        SkipActivityUtils.startActivity(launcherActivity,view,"",new Intent(launcherActivity, MainActivity.class));
    }
}
