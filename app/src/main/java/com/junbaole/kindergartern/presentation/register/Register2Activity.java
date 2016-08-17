package com.junbaole.kindergartern.presentation.register;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.junbaole.kindergartern.R;
import com.junbaole.kindergartern.data.utils.StringUtils;
import com.junbaole.kindergartern.databinding.ActivityRegisterBinding;
import com.junbaole.kindergartern.databinding.ActivityRegisterStep2Binding;
import com.junbaole.kindergartern.domain.SendPhoneEvent;
import com.junbaole.kindergartern.presentation.base.BaseActivity;
import com.junbaole.kindergartern.presentation.base.TitleBuilder;

import org.greenrobot.eventbus.Subscribe;

public class Register2Activity extends BaseActivity {

    ActivityRegisterStep2Binding registerBinding;
    private RegisterClickHandler clickHandler;
    String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_step2);
        new TitleBuilder(registerBinding.titleBar).TitleBuilderLayout(true, false).TitleBuilderLeftItem(true, false).TitleBuilderImgReasours(R.mipmap.icon_fanhui, -1).build();
        phoneNum = getIntent().getStringExtra("phoneNum");
        clickHandler = new RegisterClickHandler(this);
        registerBinding.setClickHandler(clickHandler);
        registerBinding.setIsRight(false);
        registerBinding.setPhoneNum("我们向" +
                phoneNum +
                "发送了一个验证码，请在输入框输入");
    }

    @Subscribe
    public void onGetCode(SendPhoneEvent sendPhoneEvent) {
        if (sendPhoneEvent.type == 2) {
            if (!StringUtils.isBlank(sendPhoneEvent.successMsg)) {
                Intent intent = new Intent(this, Register3Activity.class);
                intent.putExtra("code", clickHandler.code);
                intent.putExtra("phoneNum",phoneNum);
                intent.putExtra("time",sendPhoneEvent.time);
                intent.putExtra("type",getIntent().getIntExtra("type",-1));
                startActivity(intent);
            } else {
                Toast.makeText(this, sendPhoneEvent.failMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}
