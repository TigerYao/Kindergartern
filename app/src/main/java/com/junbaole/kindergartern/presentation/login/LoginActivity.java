package com.junbaole.kindergartern.presentation.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.utils.StringUtils;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.databinding.ActivityLoginBinding;
import com.junbaole.kindergartern.domain.SendPhoneEvent;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;
import com.junbaole.kindergartern.presentation.main.MainActivity;

import org.greenrobot.eventbus.Subscribe;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    public ActivityLoginBinding loginBinding;
    private LoginClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        new TitleBuilder(loginBinding.titleBar).TitleBuilderLayout(true, true).TitleBuilderLeftItem(true, false).TitleBuilderRightItem(false, true).TitleBuilderImgReasours(R.mipmap.icon_fanhui, -1).TitleBuilderLable("", "", "忘记密码?").build();
        clickHandler = new LoginClickHandler(this);
        clickHandler.mActivity = this;
        loginBinding.setClickHandler(clickHandler);
        loginBinding.displayPsw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loginBinding.password.setTransformationMethod(isChecked ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                buttonView.setText(isChecked ? "隐藏" : "显示");
            }
        });

    }

    @Subscribe
    public void loginCallBack(SendPhoneEvent sendPhoneEvent) {

        if (sendPhoneEvent.type == 4) {
            if (!StringUtils.isBlank(sendPhoneEvent.successMsg)) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("phoneNum", sendPhoneEvent.successMsg);
                SkipActivityUtils.startActivity(this, loginBinding.emailSignInButton, "main_activity", intent);
            } else {
                Toast.makeText(this, sendPhoneEvent.failMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}


