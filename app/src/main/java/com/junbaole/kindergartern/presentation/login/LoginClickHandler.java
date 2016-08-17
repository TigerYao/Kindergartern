package com.junbaole.kindergartern.presentation.login;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.junbaole.kindergartern.data.model.UserLoginVO;
import com.junbaole.kindergartern.data.utils.StringUtils;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.BaseTitleClickHandler;
import com.junbaole.kindergartern.presentation.register.RegisterActivity;

/**
 * Created by liangrenwang on 16/5/9.
 */
public class LoginClickHandler extends BaseTitleClickHandler {

    LoginActivity mActivity;


    public LoginClickHandler(BaseActivity mActivity) {
        super(mActivity);
        this.mActivity = (LoginActivity) mActivity;
    }

    public void login(final View view) {
        if (TextUtils.isEmpty(mActivity.loginBinding.phone.getText()) || StringUtils.isBlank(mActivity.loginBinding.phone.getText().toString())) {
            Toast.makeText(mActivity, "请输入手机号码", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(mActivity.loginBinding.password.getText()) || StringUtils.isBlank(mActivity.loginBinding.password.getText().toString())) {
            Toast.makeText(mActivity, "请输入密码", Toast.LENGTH_LONG).show();
            return;
        }
        UserLoginVO loginVO = new UserLoginVO();
        loginVO.phone_num = mActivity.loginBinding.phone.getText().toString();
        loginVO.wd = mActivity.loginBinding.password.getText().toString();
        mActivity.actionManager.loginApp(loginVO);

    }

    public void onClickReturn(View view) {
        SkipActivityUtils.specialFinish(mActivity);
    }

    public void onClickForgetPsw(View view) {
        Intent intent = new Intent(mActivity, RegisterActivity.class);
        intent.putExtra("type", 1);
        SkipActivityUtils.startActivity(mActivity, view, "", intent);
    }


}
