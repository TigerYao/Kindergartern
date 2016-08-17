package com.junbaole.kindergartern.presentation.register;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Toast;

import com.junbaole.kindergartern.R;

import com.junbaole.kindergartern.data.utils.StringUtils;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.databinding.ActivityRegisterStep3Binding;
import com.junbaole.kindergartern.domain.SendPhoneEvent;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;

import com.junbaole.kindergartern.presentation.login.LoginActivity;
import com.junbaole.kindergartern.presentation.main.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class Register3Activity extends BaseActivity {

    ActivityRegisterStep3Binding registerBinding;
    private RegisterClickHandler clickHandler;
    String code, phoneNum;
    int type = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_step3);
        new TitleBuilder(registerBinding.titleBar).TitleBuilderLayout(true, false).TitleBuilderLeftItem(true, false).TitleBuilderImgReasours(R.mipmap.icon_fanhui, -1).build();
        clickHandler = new RegisterClickHandler(this);
        registerBinding.setClickHandler(clickHandler);
        registerBinding.setIsRight(false);
        type = getIntent().getIntExtra("type", -1);
        code = getIntent().getStringExtra("code");
        phoneNum = getIntent().getStringExtra("phoneNum");
        registerBinding.registerPswAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(registerBinding.registerPsw.getText()) || StringUtils.isBlank(registerBinding.registerPsw.getText().toString()) || !registerBinding.registerPsw.getText().toString().matches(clickHandler.regex)) {
                    registerBinding.registerPswAgain.clearFocus();
                    registerBinding.registerPsw.requestFocus();
                    registerBinding.registerPsw.setError("请输入正确的密码");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable)) {
                    String psw = editable.toString();
                    if (registerBinding.registerPsw.getText().toString().equals(psw)) {
                        registerBinding.setIsRight(true);
                    }
                }
            }
        });
    }

    @Subscribe
    public void registerBack(SendPhoneEvent sendPhoneEvent) {
        if (sendPhoneEvent.type == 3) {
            if (!StringUtils.isBlank(sendPhoneEvent.successMsg)) {
                String msg = "恭喜你注册成功";
                if (type == 1) {
                    msg = "修改密码成功，请重新登录";
                }
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                sendPhoneEvent.type = 5;
                EventBus.getDefault().post(sendPhoneEvent);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("phoneNum", sendPhoneEvent.successMsg);
                SkipActivityUtils.startActivity(this, null, "", intent);
            } else {
                Toast.makeText(this, sendPhoneEvent.failMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
