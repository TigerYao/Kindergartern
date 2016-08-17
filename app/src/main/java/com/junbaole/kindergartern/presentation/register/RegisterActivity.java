package com.junbaole.kindergartern.presentation.register;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;


import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.utils.StringUtils;
import com.junbaole.kindergartern.data.utils.activity.SkipActivityUtils;
import com.junbaole.kindergartern.databinding.ActivityRegisterBinding;
import com.junbaole.kindergartern.domain.SendPhoneEvent;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;

import org.greenrobot.eventbus.Subscribe;

public class RegisterActivity extends BaseActivity {

    public ActivityRegisterBinding registerBinding;
    private RegisterClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        new TitleBuilder(registerBinding.titleBar).TitleBuilderLayout(true, false).TitleBuilderLeftItem(true, false).TitleBuilderImgReasours(R.mipmap.icon_fanhui, -1).build();

        clickHandler = new RegisterClickHandler(this);
        registerBinding.setClickHandler(clickHandler);
        registerBinding.setIsRight(false);
        registerBinding.phoneNum.registerTelphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 11 && s.toString().startsWith("1")) {
                    registerBinding.setIsRight(true);
                } else
                    registerBinding.setIsRight(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Subscribe
    public void onGetCode(SendPhoneEvent sendPhoneEvent) {
        if (sendPhoneEvent.type == 1) {
            if (!StringUtils.isBlank(sendPhoneEvent.successMsg)) {
                Intent intent = new Intent(this, Register2Activity.class);
                intent.putExtra("type", getIntent().getIntExtra("type", -1));
                intent.putExtra("phoneNum", sendPhoneEvent.successMsg);
                intent.putExtra("time",sendPhoneEvent.time);
                startActivity(intent);
            } else {
                Toast.makeText(this, sendPhoneEvent.failMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
